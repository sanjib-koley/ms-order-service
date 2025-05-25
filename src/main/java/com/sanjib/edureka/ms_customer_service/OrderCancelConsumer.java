package com.sanjib.edureka.ms_customer_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCancelConsumer {
	
	//@Autowired
	//OrderRepository orderRepository;
	
	
	@Autowired
    OrderService orderService;
	
	@KafkaListener(topics = "order_cancel")
	public void cancelOrder(String id) {
		orderService.cancelOrder(id);
	}
	
	@KafkaListener(topics = "order_complete")
	public void completeOrder(String id) {
		orderService.fullFillOrder(id);
	}

}
