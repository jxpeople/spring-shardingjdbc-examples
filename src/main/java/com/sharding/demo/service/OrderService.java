package com.sharding.demo.service;

import com.sharding.demo.bean.Order;

import java.util.List;

public interface OrderService {

	 long insert(Order order);


	List<Order> get(Integer userId);
	
}
