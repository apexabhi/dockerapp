package com.nagarro.service.impl;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.dto.OrderDto;
import com.nagarro.entity.LogisticPartners;
import com.nagarro.entity.OrderStatus;
import com.nagarro.entity.Orders;
import com.nagarro.entity.Slaves;
import com.nagarro.repository.LogisticRepository;
import com.nagarro.repository.OrderStatusRepository;
import com.nagarro.repository.OrdersRepository;
import com.nagarro.repository.SlaveRepository;
import com.nagarro.service.OrderService;
import com.nagarro.service.OrderStatusService;


@Service
public class OrderServiceImpl implements OrderService{
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	OrdersRepository orderRepository;
	@Autowired
	OrderStatusRepository statusRepository;
	@Autowired
	OrderStatusService statusService;
	@Autowired
	SlaveRepository slaveRepository;
	@Autowired
	LogisticRepository logisticPartnerRepository;
	
	@Override
	public String orderPlaced(Orders order) {
		// TODO Auto-generated method stub

		logger.info("Order placed by customer: {}", order);
		orderRepository.save(order);
		String str = statusService.updatePlacedStatus(order.getOrderId());
		//testing builder pattern
		OrderDto dto = new OrderDto.Builder(order.getOrderId(), order.getOrderName(), order.getPrice(),
				order.getAddress(), order.getPlacedBy()).productName(order.getProductName())
				.productCategory(order.getProductCategory()).slave(order.getSlave()).build();
		System.out.println("OrderDto using builder pattern->" + dto);
		return str;

	}

	@Override
	public String orderAllocation(Long orderId) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		if(order.getSlave()!=null) {
			logger.info("Slave has been already allocated! Tried allocation step more than once");
			return "Slave has been already allocated";
		}
			Slaves slaveAllocated=allocateSlave();
			//System.out.println(slaveAllocated.getSlaveName());
			order.setSlave(slaveAllocated);
			orderRepository.save(order);
		return statusService.updateAllocationStatus(order, slaveAllocated);
		
		
//		System.out.println(order);
//		return null;
		
		
	}
	@Override
	public String orderReAllocation(Long orderId) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		
			Slaves slaveAllocated=allocateSlave();
			//System.out.println(slaveAllocated.getSlaveName());
			order.setSlave(slaveAllocated);
			orderRepository.save(order);
		return statusService.updateAllocationStatus(order, slaveAllocated);
	}
	
	
	@Override
	public String orderPick(Long orderId, String res) {
		// TODO Auto-generated method stub
		List<OrderStatus> allStatus=statusRepository.findByOrder_OrderId(orderId);
		
		for(OrderStatus obj:allStatus) {
			if(obj.getStatus().equalsIgnoreCase("Order Pick Confirmed")) {
				logger.info("Pickup status already initiated");
				return "Order is already been picked";
			}
		}
		if(res.equalsIgnoreCase("yes")) {
			logger.info("Pickup confirmed");
			return statusService.updatePickConfirmation(orderId);
		}
		logger.info("The allocated slave rejected pickup allocating another slave");
		String str="The slave did not accepted order. Assigning to another slave\n";
		
		return str+orderReAllocation(orderId);
	}
	
	@Override
	public String orderScan(Long orderId, String res) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		LogisticPartners logisticPartner = chooseLogisticPartner();
		if(res.equalsIgnoreCase("yes")) {
		String awbNumber = generateAwbNumber(logisticPartner.getPartnerinitials());
		Double invoiceNo=(Math.random() * 90000) + 10000;
		if(order.getLogistic()!=null) {
			logger.info("packing already done");
			return "Order already packed";
		}
		order.setLogistic(logisticPartner);
		orderRepository.save(order);
		logger.info("Order scanned");
		return statusService.updateScanStatus(order, awbNumber, invoiceNo);
		}
		logger.info("LP didn't accepted order. Try packing step again");
		String str=logisticPartner.getPartnername()+" didn't accepted order. Do order packing again\n";
		return str;
	}
	@Override
	public String hotc(Long orderId, String res) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		List<OrderStatus> allStatus=statusRepository.findByOrder_OrderId(orderId);
		for(OrderStatus obj:allStatus) {
			if(obj.getStatus().equalsIgnoreCase("Handed over to the courier")) {
				logger.info("Tried HOTC more than once");
				return "Order is already been in hotc";
			}
		}
		if(res.equalsIgnoreCase("yes")) {
			Double manifest=(Math.random() * 90000) + 10000;
			logger.info("HOTC done");
			return statusService.updateHotcStatus(orderId, manifest);
			
		}
		logger.info("HOTC not done yet");
		return "Order is not handed over yet";
		
	}
	
	@Override
	public String orderDelivery(Long orderId, String res) {
		// TODO Auto-generated method stub
		Orders order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
		List<OrderStatus> allStatus=statusRepository.findByOrder_OrderId(orderId);
		for(OrderStatus obj:allStatus) {
			if(obj.getStatus().equalsIgnoreCase("Delivered")) {
				logger.info("Order is already been delivered");
				return "Order is already been delivered";
			}
		}
		if(res.equalsIgnoreCase("yes")) {
			logger.info("Order delivered");
			return statusService.updateDeliveryStatus(orderId);
			
		}
		logger.info("Order not delivered yet");
		//testing order dto builder pattern
		OrderDto dto=new OrderDto.Builder(order.getOrderId(), order.getOrderName(), order.getPrice(), order.getAddress(), order.getPlacedBy()).
								productName(order.getProductName()).productCategory(order.getProductCategory()).slave(order.getSlave()).build();
		System.out.println("OrderDto using builder pattern->"+dto);
		return "Order not delivered yet";
	}
		
	private Slaves allocateSlave() {
		List<Slaves> allSlaves = slaveRepository.findAll();
		if (allSlaves.isEmpty()) {
			     throw new RuntimeException("No sellers available");
		}
		Random random = new Random();
		int randomIndex = random.nextInt(allSlaves.size());
		return allSlaves.get(randomIndex);

	}
	private LogisticPartners chooseLogisticPartner() {
		List<LogisticPartners> allPartners = logisticPartnerRepository.findAll();
		if (allPartners.isEmpty()) {
			throw new RuntimeException("No logistic partners available");
		}
		Random random = new Random();
		int randomIndex = random.nextInt(allPartners.size());
		return allPartners.get(randomIndex);
	}
	
	private String generateAwbNumber(String str) {
		String res=str;
		Random random = new Random();
        int min = 1000000;
        int max = 9999999; 
        int genNum = random.nextInt(max - min + 1) + min;
        res+=genNum;
        int lastDigit=genNum%7;
        res+=lastDigit;
		return res;
		
	}

	

	
	

}
