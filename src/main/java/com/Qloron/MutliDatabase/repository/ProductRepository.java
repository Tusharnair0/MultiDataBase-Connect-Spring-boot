package com.Qloron.MutliDatabase.repository;

import com.Qloron.MutliDatabase.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {
    List<Product> findByIdIn(List<String> productIds);
}
