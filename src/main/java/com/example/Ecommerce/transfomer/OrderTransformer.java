package com.example.Ecommerce.transfomer;

import com.example.Ecommerce.dto.RequestDto.OrderRequestDto;
import com.example.Ecommerce.dto.ResponseDto.ItemResponseDto;
import com.example.Ecommerce.dto.ResponseDto.OrderResponseDto;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.model.Item;
import com.example.Ecommerce.model.OrderEntity;
import jakarta.persistence.criteria.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {

    public static OrderEntity OrderRequestDtoToOrder(OrderRequestDto orderRequestDto, Item item, Customer customer){
        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(customer)
                .items(new ArrayList<>())
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }

    public static OrderResponseDto orderToOrderResponseDto(OrderEntity orderEntity){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : orderEntity.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemtoItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderDate(orderEntity.getOrderDate())
                .cardUsed(orderEntity.getCardUsed())
                .customerName(orderEntity.getCustomer().getName())
                .totalValue(orderEntity.getTotalValue())
                .orderNo(orderEntity.getOrderNo())
                .items(itemResponseDtoList)
                .build();
    }
}
