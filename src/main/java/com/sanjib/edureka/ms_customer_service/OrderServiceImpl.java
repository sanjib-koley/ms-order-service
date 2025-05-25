package com.sanjib.edureka.ms_customer_service ;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderReository;
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	
	@Autowired
    TokenService tokenService;


	@Override
	public Order createOrder(String token, String usertype, Order orderInfo) {
		return mongoTemplate.save(orderInfo);
		
	}


	@Override
	public Order findOrder(String id) {
		
		return orderReository.findById(new ObjectId(id)).get();
		//BasicQuery query = new BasicQuery("{ _id: ObjectId(\""+id+"\") }");
	    //return (Order) mongoTemplate.find(query, Order.class).get(0);
	}
	@Override
	public void cancelOrder(String id) {
		Order order =findOrder(id);
		order.setOrderStatus("Cancelled");
		mongoTemplate.save(order);
	}
	@Override
	public void fullFillOrder(String id) {
		Order order =findOrder(id);
		order.setOrderStatus("FullFilled");
		mongoTemplate.save(order);
	}

}
