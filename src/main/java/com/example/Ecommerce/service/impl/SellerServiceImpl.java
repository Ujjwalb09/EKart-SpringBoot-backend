package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.model.Seller;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.service.SellerService;
import com.example.Ecommerce.transfomer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {

        //dto to entity
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        Seller savedSeller = sellerRepository.save(seller);

        //prepare response dto
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }
}
