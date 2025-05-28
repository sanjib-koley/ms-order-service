package com.sanjib.edureka.ms_customer_service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	@PostMapping("/order/create/{customerId}/{cartId}")
	public ResponseEntity<?> addProductToCart(@PathVariable("customerId") Long customerId,
			@PathVariable("cartId") Integer cartId, @RequestHeader("Authorization") String token,
			@RequestHeader("Usertype") String usertype, @RequestBody Order orderView) {

		if (tokenService.validateToken(token) && "customer".equalsIgnoreCase(usertype)) {
			
			
			
			Order orderCreated = new Order();
			if (orderView.getItems() != null) {

				orderView.getItems().stream()
						.filter(item -> tokenService.getProductFromId(token, usertype, item.getProductId()).getStatus()
								.equals(ProductStatus.AVAILABLE))
						.forEach(item -> orderCreated.getItems().add(item));
			}
			
			if (!orderCreated.getItems().isEmpty()) {
				orderCreated.getItems().stream().forEach(item -> item.setItemId(UUID.randomUUID().toString()));

				orderCreated.setOrderValue(
						orderCreated.getItems().stream().map(item -> (item.getPrice()*item.getQuantity())).reduce(0.0, Double::sum));
				orderCreated.setPaymentInfo(orderView.getPaymentInfo());
				orderCreated.setAddress(orderView.getAddress());
				orderCreated.setCartId(cartId);
				orderCreated.setCustomerId(customerId);
				orderCreated.setOrderId(UUID.randomUUID().toString());
			}
			
			Order order = orderService.createOrder(token, usertype, orderCreated);
			
			kafkaTemplate.send("order_create", order);
			return new ResponseEntity<Order>(order, HttpStatus.CREATED);

		} else {
			return ResponseEntity.status(401).body("Invalid Details");
		}

	}
	
	@GetMapping("/order/retrieve/{id}")
	public ResponseEntity<Order> getOrder(@PathVariable("id") String id) {
		return new ResponseEntity<Order>(orderService.findOrder(id), HttpStatus.OK);
	}
}
