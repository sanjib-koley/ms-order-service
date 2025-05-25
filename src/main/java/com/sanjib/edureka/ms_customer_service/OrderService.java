package com.sanjib.edureka.ms_customer_service;

public interface OrderService {
	
	public Order createOrder(String token, String usertype, Order orderInfo);
	
	public Order findOrder(String id);
	
	public void cancelOrder(String id);
	
	public void fullFillOrder(String id);
}
