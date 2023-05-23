package com.example.Ecommerce.controller;


import com.example.Ecommerce.Enum.Category;
import com.example.Ecommerce.dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.exception.SellerNotFoundException;
import com.example.Ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto){

        try{
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/category/{category}/price/{price}")
    public ResponseEntity getAllProductsBytCategoryAndPrice(@PathVariable("category") Category category,
                                                            @PathVariable("price") Integer price){

        List<ProductResponseDto> productResponseDtos = productService.getAllProductsByCategoryAndPrice(category, price);

        return new ResponseEntity<>(productResponseDtos, HttpStatus.FOUND);
    }

    //get all the product of a category

    //get all the product in a category who have price greater than 500

    //get the top5 cheapest product in the category

    //get top 5 costliest products in a category

    //get all the products of seller based on emailId

    //get all the out of stock products for a particular category

    //send an email to the seller of the product if the quantity is out of stock.
}
