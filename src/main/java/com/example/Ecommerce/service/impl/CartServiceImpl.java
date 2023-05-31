package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.dto.RequestDto.CheckoutCartRequestDto;
import com.example.Ecommerce.dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.exception.CustomerNotFoundException;
import com.example.Ecommerce.exception.EmptyCartException;
import com.example.Ecommerce.exception.InsufficientQuantityException;
import com.example.Ecommerce.exception.InvalidCardException;
import com.example.Ecommerce.model.*;
import com.example.Ecommerce.repository.*;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.service.OrderService;
import com.example.Ecommerce.transfomer.CartTransformer;
import com.example.Ecommerce.transfomer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Override
    public CartResponseDto addTocart(Item item, ItemRequestDto itemRequestDto) {

         Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
         Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart  = customer.getCart(); //getting the cart in which we have to add the item
        cart.setCartTotal(cart.getCartTotal() + item.getRequiredQuantity()*product.getPrice());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);

        Cart savedCart = cartRepository.save(cart); //saves both cart and item
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);

        product.getItems().add(savedItem);
        //prepare response dto

        CartResponseDto cartResponseDto = CartTransformer.cartToCartResponseDto(savedCart);

        return cartResponseDto;
    }

    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException {

        //check customer
        Customer customer = customerRepository.findByEmailId(checkoutCartRequestDto.getEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer does not exist!");
        }

        //check card
        Card card = cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        Date date = new Date();
        if(card==null || card.getCvv()!= checkoutCartRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Sorry! You cant use this card");
        }

        Cart cart = customer.getCart();
        if(cart.getItems().size()==0){
            throw new EmptyCartException("Cart is Empty!");
        }

        try{
            OrderEntity order = orderService.placeOrder(cart, card);
            resetCart(cart);

            OrderEntity savedOrder = orderRepository.save(order);
            customer.getOrders().add(savedOrder);
            return OrderTransformer.orderToOrderResponseDto(savedOrder);
        } catch(InsufficientQuantityException e){
            throw e;
        }

    }

    private void resetCart(Cart cart){
        cart.setCartTotal(0);

        for(Item item: cart.getItems())
            item.setCart(null);

        cart.setItems(new ArrayList<>());
    }
}
