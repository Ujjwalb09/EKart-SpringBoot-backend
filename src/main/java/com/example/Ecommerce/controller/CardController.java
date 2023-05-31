package com.example.Ecommerce.controller;

import com.example.Ecommerce.dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.exception.CustomerNotFoundException;
import com.example.Ecommerce.service.CardService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){

        try{
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity<>(cardResponseDto, HttpStatus.CREATED);
        } catch (CustomerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

//tell me the card type which exists max no. of time

////tell me the card type which exists min no. of time