package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.RequestDto.CheckoutCartRequestDto;
import com.example.Ecommerce.dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.exception.CustomerNotFoundException;
import com.example.Ecommerce.exception.EmptyCartException;
import com.example.Ecommerce.exception.InsufficientQuantityException;
import com.example.Ecommerce.exception.InvalidCardException;
import com.example.Ecommerce.model.Item;

public interface CartService {
    CartResponseDto addTocart(Item item, ItemRequestDto itemRequestDto);

    public OrderResponseDto checkoutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException;

}
