package com.sharding.demo.serviceImpl;

import com.sharding.demo.bean.Order;
import com.sharding.demo.dao.OrderRepository;
import com.sharding.demo.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrderRepository orderRepository;

	@Override
	public long insert(Order order) {
		// TODO Auto-generated method stub
		return orderRepository.insert(order);
	}

	@Override
	public List<Order> get(Integer userId){
		return orderRepository.selectByUserId(userId);
	}

}
