package com.example.Ecommerce.service;

import com.example.Ecommerce.Enum.Category;
import com.example.Ecommerce.dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {

    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, Integer price);
}
