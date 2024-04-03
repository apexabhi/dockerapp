package com.nagarro.service;

import com.nagarro.entity.Orders;

public interface OrderService {
	public String orderPlaced(Orders order);
	public String orderAllocation(Long orderId);
	public String orderReAllocation(Long orderId);
	public String orderScan(Long orderId, String res);
	public String hotc(Long orderId, String res);
	public String orderDelivery(Long orderId, String res);
	public String orderPick(Long orderId, String res);

}
