package com.example.Ecommerce.service;

import com.example.Ecommerce.Enum.Category;
import com.example.Ecommerce.dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.model.Product;

import java.util.List;

public interface SellerService {

    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);


}
