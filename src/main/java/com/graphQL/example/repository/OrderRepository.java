package com.graphQL.example.repository;

import com.graphQL.example.mdoel.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order,String> {
	Optional<Order> findById(String id);
}
