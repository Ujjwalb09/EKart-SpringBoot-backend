package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.model.Item;

public interface CartService {
    CartResponseDto addTocart(Item item, ItemRequestDto itemRequestDto);

}
