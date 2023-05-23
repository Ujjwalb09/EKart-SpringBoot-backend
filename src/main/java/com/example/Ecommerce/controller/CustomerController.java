package com.example.Ecommerce.controller;


import com.example.Ecommerce.dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.service.CustomerService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){

        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);

        return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
    }


    //get all female customer between age 20 to 30
    //get all male customers less than 45
    //customers who have ordered atleast k products
}
