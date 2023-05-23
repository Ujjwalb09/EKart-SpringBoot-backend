package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    SellerService sellerService;
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);

        return new ResponseEntity<>(sellerResponseDto, HttpStatus.CREATED);
    }

    //update the seller info based on email
    // get all the sellers who sell product of particular category
    //get all the products sold by a seller in a category
    // seller with highest no. of products
    //seller with min no. of products
    //seller(s) selling the costliest product
    //seller(s) selling the cheapest product

}
