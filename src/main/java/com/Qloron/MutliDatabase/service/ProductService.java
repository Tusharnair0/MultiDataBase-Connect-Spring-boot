package com.Qloron.MutliDatabase.service;

import com.Qloron.MutliDatabase.model.Product;
import com.Qloron.MutliDatabase.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product){
        try {
            return productRepository.save(product);
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Validation failed: " + e.getMessage());
        }

    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
        return product;


    }

    public void deleteProduct(String id) {
        try {
            productRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
    }
}
