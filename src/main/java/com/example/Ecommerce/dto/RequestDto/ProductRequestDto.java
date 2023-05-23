package com.example.Ecommerce.dto.RequestDto;

import com.example.Ecommerce.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {

    String sellerEmailId;

    String name;

    Integer price;

    Category category;

    Integer quantity;
}
