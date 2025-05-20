package com.sanjib.edureka.ms_customer_service;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface OrderReository extends MongoRepository<Order, Integer> {
	
}
