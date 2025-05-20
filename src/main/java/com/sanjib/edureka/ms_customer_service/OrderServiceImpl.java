package com.sanjib.edureka.ms_customer_service ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderReository orderReository;
	
	
	@Autowired
    TokenService tokenService;


	@Override
	public Order createOrder(String token, String usertype, Order orderInfo) {
		return orderReository.save(orderInfo);
		
	}

}
