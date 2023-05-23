package com.example.Ecommerce.transfomer;


import com.example.Ecommerce.dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {

    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){

        return Customer.builder()
                .name(customerRequestDto.getName())
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .gender(customerRequestDto.getGender())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){

        return CustomerResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .mobNo(customer.getMobNo())
                .build();
    }
}
