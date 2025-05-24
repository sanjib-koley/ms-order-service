package com.sanjib.edureka.ms_customer_service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection="order")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Order {

	private String orderId;
	
	private Integer cartId;
	
	private Long customerId;
	
	private List<Item> items = new ArrayList<>();
	
	private Double orderValue=0.0;
	
	private String customerAddress;
	
	private String paymentInfo;
	
	private String orderStatus;

}
