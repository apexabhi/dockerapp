package com.nagarro.service;

import com.nagarro.entity.Orders;
import com.nagarro.entity.Slaves;

public interface OrderStatusService {
	public String updatePlacedStatus(Long orderId);
	public String updateAllocationStatus(Orders order, Slaves slave);
	public String getAllStatus(Long orderId);
	public String updatePickConfirmation(Long orderId);
	public String updateScanStatus(Orders order, String awd, Double invoice);
	public String updateHotcStatus(Long orderId, Double manifest);
	public String updateDeliveryStatus(Long orderId);
}
