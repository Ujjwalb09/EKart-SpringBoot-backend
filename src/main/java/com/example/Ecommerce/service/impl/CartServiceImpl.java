package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.model.Cart;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.model.Item;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.CartRepository;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.CartService;
import com.example.Ecommerce.transfomer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
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
}
