package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.Enum.Category;
import com.example.Ecommerce.dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.dto.RequestDto.SellerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.dto.ResponseDto.SellerResponseDto;
import com.example.Ecommerce.exception.SellerNotFoundException;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.model.Seller;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.repository.SellerRepository;
import com.example.Ecommerce.service.ProductService;
import com.example.Ecommerce.transfomer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {

        //check if seller exist or not
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());

        if(seller==null){
            throw new SellerNotFoundException("EmailId is not registered");
        }

        //dto to entity
        Product product = ProductTransformer.productRequestDtoToEntity(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        //save product
        Seller savedSeller = sellerRepository.save(seller); //will save both seller and product as
                                                            // seller is the parent

        //entity to Response dto
        return ProductTransformer.entityToProductResponseDto(savedSeller.getProducts().get(savedSeller.getProducts().size()-1));
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, Integer price) {

        List<Product> products = productRepository.findByCategoryAndPrice(category, price);

        //prepare list of dto

        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product: products){
            productResponseDtos.add(ProductTransformer.entityToProductResponseDto(product));
        }

        return productResponseDtos;
    }
}
