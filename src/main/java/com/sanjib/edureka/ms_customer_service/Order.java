package com.sanjib.edureka.ms_customer_service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

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
    
    @MongoId(value=FieldType.OBJECT_ID)
    private String id;
    
	private String orderId;
	
	private Integer cartId;
	
	private Long customerId;
	
	private List<Item> items = new LinkedList<>();
	
	private Double orderValue=0.0;
	
	private String address;
	
	private String paymentInfo;
	
	private String orderStatus;

}
