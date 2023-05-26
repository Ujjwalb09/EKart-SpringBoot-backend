package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.dto.RequestDto.CardRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CardResponseDto;
import com.example.Ecommerce.exception.CustomerNotFoundException;
import com.example.Ecommerce.model.Card;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.service.CardService;
import com.example.Ecommerce.transfomer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CardResponseDto addCard(CardRequestDto cardRequestDto) throws CustomerNotFoundException {

        //find if customer exists or not

        Customer customer = customerRepository.findByEmailId(cardRequestDto.getEmailId());

        if(customer==null){
            throw new CustomerNotFoundException("Invalid email Id!");
        }

        //dto to entity

        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        customerRepository.save(customer); //saving customer and card

        //responseDto
        return CardTransformer.CardToCardResponseDto(card);


    }
}
