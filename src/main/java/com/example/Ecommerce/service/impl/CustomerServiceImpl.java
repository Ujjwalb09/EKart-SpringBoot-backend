package com.example.Ecommerce.service.impl;


import com.example.Ecommerce.dto.RequestDto.CustomerRequestDto;
import com.example.Ecommerce.dto.ResponseDto.CustomerResponseDto;
import com.example.Ecommerce.model.Cart;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.service.CustomerService;
import com.example.Ecommerce.transfomer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {

        //dto to entity
        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);

        Cart cart = new Cart(); //allocating customer a cart
        cart.setCartTotal(0);
        cart.setCustomer(customer);

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer); //will save both customer and cart

        //prepare response dto
        return CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
    }
}
