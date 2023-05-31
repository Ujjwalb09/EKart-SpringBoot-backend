package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.RequestDto.OrderRequestDto;
import com.example.Ecommerce.dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.exception.CustomerNotFoundException;
import com.example.Ecommerce.exception.InsufficientQuantityException;
import com.example.Ecommerce.exception.InvalidCardException;
import com.example.Ecommerce.exception.ProductNotFoundException;
import com.example.Ecommerce.model.Card;
import com.example.Ecommerce.model.Cart;
import com.example.Ecommerce.model.OrderEntity;

public interface OrderService {

    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException, InvalidCardException;

    public OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException;
}
