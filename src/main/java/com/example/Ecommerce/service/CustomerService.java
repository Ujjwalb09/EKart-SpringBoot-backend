package com.example.Ecommerce.service;

import com.example.Ecommerce.dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CustomerResponseDto;

public interface CustomerService {

    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);
}
