package com.example.Ecommerce.service.impl;

import com.example.Ecommerce.dto.RequestDto.ItemRequestDto;
import com.example.Ecommerce.exception.CustomerNotFoundException;
import com.example.Ecommerce.exception.InsufficientQuantityException;
import com.example.Ecommerce.exception.OutOfStockException;
import com.example.Ecommerce.exception.ProductNotFoundException;
import com.example.Ecommerce.model.Customer;
import com.example.Ecommerce.model.Item;
import com.example.Ecommerce.model.Product;
import com.example.Ecommerce.repository.CustomerRepository;
import com.example.Ecommerce.repository.ProductRepository;
import com.example.Ecommerce.service.ItemService;
import com.example.Ecommerce.transfomer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException,
            CustomerNotFoundException, InsufficientQuantityException, OutOfStockException {

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());

        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());

        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        Product product = productOptional.get();

        if(product.getQuantity()==0){
            throw new OutOfStockException("Product is out of stock");
        }

        if(product.getQuantity() < itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not available");
        }

        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}
