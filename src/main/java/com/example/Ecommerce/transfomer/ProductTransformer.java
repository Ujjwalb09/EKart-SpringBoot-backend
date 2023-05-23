package com.example.Ecommerce.transfomer;

import com.example.Ecommerce.Enum.ProductStatus;
import com.example.Ecommerce.dto.RequestDto.ProductRequestDto;
import com.example.Ecommerce.dto.ResponseDto.ProductResponseDto;
import com.example.Ecommerce.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformer {

    public static Product productRequestDtoToEntity(ProductRequestDto productRequestDto){

        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategory())
                .quantity(productRequestDto.getQuantity())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto entityToProductResponseDto(Product product){

        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .build();
    }
}
