package com.example.Ecommerce.repository;

import com.example.Ecommerce.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmailId(String emailId);
}
