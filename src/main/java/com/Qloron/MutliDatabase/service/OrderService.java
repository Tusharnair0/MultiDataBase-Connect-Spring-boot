package com.Qloron.MutliDatabase.service;

import com.Qloron.MutliDatabase.model.Order;
import com.Qloron.MutliDatabase.model.Product;
import com.Qloron.MutliDatabase.repository.OrderRepository;
import com.Qloron.MutliDatabase.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Order createOrder(Order order) {
        try {
            Order savedOrder = orderRepository.save(order);
            List<Product> products = productRepository.findAllById(order.getProductIds());
            savedOrder.setProducts(products);
            return savedOrder;
        } catch (ConstraintViolationException e) {
            throw new IllegalArgumentException("Validation failed: " + e.getMessage());
        }
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }
        return order;
    }

    public void deleteOrder(String id) {
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException("Order not found with id: " + id);
        }    }

    public List<Order> getOrdersWithProductDetails() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> {
            List<Product> products = productRepository.findAllById(order.getProductIds());
            order.setProducts(products);  // Assuming you want to set products to the order
            return order;
        }).collect(Collectors.toList());
    }
}
