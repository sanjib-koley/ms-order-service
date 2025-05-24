package com.sanjib.edureka.ms_customer_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderCancelConsumer {
	
	@Autowired
	OrderRepository orderRepository;
	
	@KafkaListener(topics = "order_cancel")
	public void cancelOrder(String orderId) {
		Order order = orderRepository.findOrderByOrderId(orderId);
		order.setOrderStatus("Cancelled");
		orderRepository.save(order);
	}

}
