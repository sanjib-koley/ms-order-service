package com.sanjib.edureka.ms_customer_service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
	
	public Optional<Order> findById(ObjectId id);
	
}
