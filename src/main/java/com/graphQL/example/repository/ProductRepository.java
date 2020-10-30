package com.graphQL.example.repository;

import com.graphQL.example.mdoel.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
	Product findByProductName(String productName);
}
