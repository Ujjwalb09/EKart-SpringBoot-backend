package com.example.Ecommerce.transfomer;

import com.example.Ecommerce.dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardTransformer {

    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){

        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .validTill(cardRequestDto.getValidTill())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .build();
    }

    public static CardResponseDto CardToCardResponseDto(Card card){

        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .customerName(card.getCustomer().getName())
                .build();
    }
}
