package com.sanjib.edureka.ms_customer_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
    TokenService tokenService;
	
	@Autowired
	KafkaTemplate<String, Order> kafkaTemplate;

	@PostMapping("/order/create")
	public ResponseEntity<?> addProductToCart(@RequestHeader("Authorization") String token,
			@RequestHeader("Usertype") String usertype, @RequestBody Order orderView) {

		if (tokenService.validateToken(token) && "customer".equalsIgnoreCase(usertype)) {

			Order ordercreated = orderService.createOrder(token, usertype, orderView);
			kafkaTemplate.send("order_created",ordercreated);
			return new ResponseEntity<Order>(ordercreated, HttpStatus.CREATED);

		} else {
			return ResponseEntity.status(401).body("Invalid Details");
		}

	}
}
