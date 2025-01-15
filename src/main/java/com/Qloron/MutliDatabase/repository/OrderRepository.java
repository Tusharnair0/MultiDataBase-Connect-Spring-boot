package com.Qloron.MutliDatabase.repository;

import com.Qloron.MutliDatabase.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}
