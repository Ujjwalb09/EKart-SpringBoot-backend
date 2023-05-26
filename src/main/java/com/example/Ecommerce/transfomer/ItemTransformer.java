package com.example.Ecommerce.transfomer;


import com.example.Ecommerce.dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.model.Item;
import com.example.Ecommerce.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemTransformer {

    public static Item ItemRequestDtoToItem(Product product, Customer customer, int quantity){

        return Item.builder()
                .requiredQuantity(quantity)
                .build();
    }

    public static ItemResponseDto ItemtoItemResponseDto(Item item){

        return ItemResponseDto.builder()
                .quantityAdded(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();
    }
}
