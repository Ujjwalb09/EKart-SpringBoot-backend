package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.exception.CustomerNotFoundException;
import com.example.Ecommerce.exception.InsufficientQuantityException;
import com.example.Ecommerce.exception.OutOfStockException;
import com.example.Ecommerce.exception.ProductNotFoundException;
import com.example.Ecommerce.model.Item;

public interface ItemService {
    Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException;
}
