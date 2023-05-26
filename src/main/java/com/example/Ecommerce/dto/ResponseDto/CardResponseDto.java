package com.example.Ecommerce.dto.ResponseDto;

import com.example.Ecommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardResponseDto {

    String customerName;

    String cardNo;

    CardType cardType;

}
