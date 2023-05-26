package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.exception.CustomerNotFoundException;

public interface CardService {

    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;
}
