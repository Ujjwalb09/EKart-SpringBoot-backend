package com.example.Ecommerce.repository;

import com.example.Ecommerce.Enum.Category;
import com.example.Ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategoryAndPrice(Category category, Integer price);
}
