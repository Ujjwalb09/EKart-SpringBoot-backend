package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.Enum.ProductStatus;
import com.example.Ecommerce.dto.RequestDto.OrderRequestDto;
import com.example.Ecommerce.dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.exception.CustomerNotFoundException;
import com.example.Ecommerce.exception.InsufficientQuantityException;
import com.example.Ecommerce.exception.InvalidCardException;
import com.example.Ecommerce.exception.ProductNotFoundException;
import com.example.Ecommerce.model.*;
import com.example.Ecommerce.repository.CardRepository;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.OrderRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.OrderService;
import com.example.Ecommerce.transfomer.ItemTransformer;
import com.example.Ecommerce.transfomer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException {

        Customer customer = customerRepository.findByEmailId(orderRequestDto.getEmailId());

        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't not exist");
        }

        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());

        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Sorry! Product doesn't exist");
        }

        Product product = optionalProduct.get();

        //check quantity

        if(product.getQuantity() < orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Required quantity not available");
        }

        //check card
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        Date date = new Date();
        if(card==null || card.getCvv()!= orderRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Sorry! You cant use this card");
        }

        //decrease quantity
        int newQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        product.setQuantity(newQuantity);
        if(newQuantity==0){
           product.setProductStatus(ProductStatus.OUT_OF_STOCK);
        }
        //create item
        Item item  = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);

        //create order
        OrderEntity orderEntity = OrderTransformer.OrderRequestDtoToOrder(orderRequestDto,item, customer);
        String maskedCard = generateMaskedCardNo(card);
        orderEntity.setCardUsed(maskedCard);

        orderEntity.getItems().add(item);
        item.setOrderEntity(orderEntity);

        OrderEntity savedOrder = orderRepository.save(orderEntity); //saves both order and item

        customer.getOrders().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));

        //prepare response dto

        return OrderTransformer.orderToOrderResponseDto(savedOrder);
    }

    public OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(generateMaskedCardNo(card));

        int totalValue = 0;
        for(Item item: cart.getItems()){
            Product product = item.getProduct();
            if(item.getRequiredQuantity() > product.getQuantity()){
                throw new InsufficientQuantityException("Required quantity not present");
            }

            totalValue += item.getRequiredQuantity()* product.getPrice();
            int newQuantity = product.getQuantity() - item.getRequiredQuantity();
            product.setQuantity(newQuantity);

            if(newQuantity==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            }

            item.setOrderEntity(orderEntity);
        }

        orderEntity.setTotalValue(totalValue);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());

        return orderEntity;
    }

    private String generateMaskedCardNo(Card card){
          String cardNo = "";
          String originalCardNo = card.getCardNo();

          for(int i = 0; i < originalCardNo.length()-4; i++){
              cardNo += "X";
          }

          cardNo += originalCardNo.substring(originalCardNo.length()-4);
          return cardNo;
    }
}
