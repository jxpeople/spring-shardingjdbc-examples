package com.sharding.demo.controller;

import com.alibaba.fastjson.JSON;
import com.sharding.demo.bean.Order;
import com.sharding.demo.bean.OrderItem;
import com.sharding.demo.service.OrderItemService;
import com.sharding.demo.service.OrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/demo")
public class ShardingDemoController {

	private static Logger logger = LogManager.getLogger();

	@Resource(name = "orderService")
	private OrderService orderService;

	@Resource(name = "orderItemService")
	private OrderItemService orderItemService;

	//注意这块没有事务控制，如果有报错，是没控制事务的
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add() {
		for(int i = 1;i<10;i++) {
//			DefaultKeyGenerator key = new DefaultKeyGenerator();
			int userId = i;
//			Number orderIdKey = key.generateKey();
//			Long orderId = orderIdKey.longValue();
			Long orderId = System.currentTimeMillis();
			logger.info("分布式主键orderId:" + orderId);

			Order order = new Order();
			order.setUserId(userId);
			order.setStatus("1");
			order.setOrderId(orderId);

			Long returnOrderId = orderService.insert(order);
			logger.info("插入成功后的returnOrderId:" + returnOrderId);

			OrderItem item = new OrderItem();
			item.setOrderId(orderId);
			item.setUserId(userId);
			item.setStatus("1");
			Long returnOrderItemId = orderItemService.insert(item);
			logger.info("插入成功后的returnOrderItemId:" + returnOrderItemId);
		}
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(Integer userId) {

		return JSON.toJSONString( orderService.get(userId));
	}

}
