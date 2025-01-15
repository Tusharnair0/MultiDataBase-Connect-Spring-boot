package com.Qloron.MutliDatabase.controller;

import com.Qloron.MutliDatabase.model.Order;
import com.Qloron.MutliDatabase.model.Product;
import com.Qloron.MutliDatabase.repository.OrderRepository;
import com.Qloron.MutliDatabase.repository.ProductRepository;
import com.Qloron.MutliDatabase.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;


    public OrderController(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        Order savedOrder = orderRepository.save(order);
        if (savedOrder.getProductIds() != null && !savedOrder.getProductIds().isEmpty()) {
            List<Product> products = productRepository.findByIdIn(savedOrder.getProductIds());
            savedOrder.setProducts(products);  // Set the products in the order
        }
        return savedOrder;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        List<Order> orders = orderRepository.findAll();

        for (Order order : orders) {
            if (order.getProductIds() != null && !order.getProductIds().isEmpty()) {
                List<Product> products = productRepository.findByIdIn(order.getProductIds());
                order.setProducts(products);  // Set the products for the order
            }
        }

        return orders;
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable String id) {
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            if (order.getProductIds() != null && !order.getProductIds().isEmpty()) {
                List<Product> products = productRepository.findByIdIn(order.getProductIds());
                order.setProducts(products);
            }
            return order;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id) {
        orderRepository.deleteById(id);
    }
}
