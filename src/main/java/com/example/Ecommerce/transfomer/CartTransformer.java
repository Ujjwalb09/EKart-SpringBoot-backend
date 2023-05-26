package com.example.Ecommerce.transfomer;

import com.example.Ecommerce.dto.ResponseDto.CartResponseDto;
import com.example.Ecommerce.dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.model.Cart;
import com.example.Ecommerce.model.Item;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CartTransformer {

    public static CartResponseDto cartToCartResponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDto = new ArrayList<>();
        for(Item item: cart.getItems()){
            itemResponseDto.add(ItemTransformer.ItemtoItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDto)
                .build();
    }
}
