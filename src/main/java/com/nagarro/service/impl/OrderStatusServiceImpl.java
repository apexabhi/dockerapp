package com.nagarro.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.entity.OrderStatus;
import com.nagarro.entity.Orders;
import com.nagarro.entity.Slaves;
import com.nagarro.enums.LogLevel;
import com.nagarro.logger.Logger;
import com.nagarro.repository.OrderStatusRepository;
import com.nagarro.repository.OrdersRepository;
import com.nagarro.repository.SlaveRepository;
import com.nagarro.service.OrderStatusService;

@Service
public class OrderStatusServiceImpl implements OrderStatusService{

	@Autowired
	OrdersRepository orderRepository;
	@Autowired
	OrderStatusRepository statusRepository;
	@Autowired
	SlaveRepository slaveRepository;
	//to verify only single instance is created
	 Logger logger1 = Logger.getInstance();
	 Logger logger2 = Logger.getInstance();
	@Override
	public String updatePlacedStatus(Long orderId) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		OrderStatus os=new OrderStatus();
		os.setOrder(order);
		os.setStatus("Order Placed by customer");
		os.setTimeCreated(new Date());
		statusRepository.save(os);
		 logger1.setLogLevel(LogLevel.TRACE);
		 logger2.setLogLevel(LogLevel.TRACE);
		 //testing singleton instance
	     logger1.log(LogLevel.INFO, "Order place loggeg through singleton logger"+logger1.hashCode());
	     logger2.log(LogLevel.INFO, "Order place loggeg through singleton logger"+logger2.hashCode());

		return os.getStatus();
		
	}
	@Override
	public String updateAllocationStatus(Orders order, Slaves slave) {
		// TODO Auto-generated method stub
		//Seller seller=slave.getSeller();
		//System.out.println(seller.getSellerName());
		String erp=slave.getSeller().getErpno()+"-"+slave.getSlaveName();
		OrderStatus os=new OrderStatus();
		os.setERPcode(erp);
		os.setOrder(order);
		os.setTimeCreated(new Date());
		os.setStatus("Order allocated");
		statusRepository.save(os);
		String str=os.getStatus()+" to-> "+erp;
		return str;
	}
	@Override
	public String getAllStatus(Long orderId) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		List<OrderStatus> allStatus=statusRepository.findByOrder_OrderId(orderId);
		String allUpdates="Time Created	|	OrderStatus	|	Detail	|	SiteERP Code \n";
		for(OrderStatus obj:allStatus) {
			String str=obj.getTimeCreated()+"	|	"+obj.getStatus()+"	|	"+obj.getDetail()+"	|	"+obj.getERPcode()+"\n";
			allUpdates+=str;
		}
		return allUpdates;
	}
	@Override
	public String updatePickConfirmation(Long orderId) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		List<OrderStatus> allStatus=statusRepository.findByOrder_OrderId(orderId);
		OrderStatus oldStatus=allStatus.get(allStatus.size()-1);
		OrderStatus os=new OrderStatus();
		os.setTimeCreated(new Date());
		os.setERPcode(oldStatus.getERPcode());
		os.setOrder(order);
		os.setStatus("Order Pick Confirmed");
		statusRepository.save(os);
		String str=os.getStatus()+"by-> "+os.getERPcode();
		return str;
	}
	@Override
	public String updateScanStatus(Orders order, String awd, Double invoice) {
		// TODO Auto-generated method stub
		List<OrderStatus> allStatus=statusRepository.findByOrder_OrderId(order.getOrderId());
		OrderStatus oldStatus=allStatus.get(allStatus.size()-1);
		OrderStatus os=new OrderStatus();
		String detail="LP: "+order.getLogistic().getPartnername()+"	";
		String awb="AWB No: "+awd+"	";
		detail+=awb;
		String inv="Invoice No: "+invoice+"	";
		detail+=inv;
		os.setDetail(detail);
		os.setTimeCreated(new Date());
		os.setERPcode(oldStatus.getERPcode());
		os.setOrder(order);
		os.setStatus("Packed(Scanned)");
		statusRepository.save(os);
		return "Order Packed \n"+detail;
	}
	@Override
	public String updateHotcStatus(Long orderId, Double manifest) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		List<OrderStatus> allStatus=statusRepository.findByOrder_OrderId(orderId);
		OrderStatus oldStatus=allStatus.get(allStatus.size()-1);
		OrderStatus os=new OrderStatus();
		os.setERPcode(oldStatus.getERPcode());
		os.setTimeCreated(new Date());
		os.setOrder(order);
		os.setStatus("Handed over to the courier");
		String manifestId="Manifest Id: "+manifest;
		os.setDetail(manifestId);
		statusRepository.save(os);
		return "Order is handed over to the courier with manifestID- "+manifestId;
	}
	@Override
	public String updateDeliveryStatus(Long orderId) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		List<OrderStatus> allStatus=statusRepository.findByOrder_OrderId(orderId);
		OrderStatus oldStatus=allStatus.get(allStatus.size()-1);
		OrderStatus os=new OrderStatus();
		os.setERPcode(oldStatus.getERPcode());
		os.setTimeCreated(new Date());
		os.setOrder(order);
		os.setStatus("Delivered");
		statusRepository.save(os);
		return "Order Delivered to the customer";
	}
	
	
	
	
	
	
	
	
}
