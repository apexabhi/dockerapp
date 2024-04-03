//Mappings for seller module
package com.nagarro.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.dto.DeliveryResponseDto;
import com.nagarro.dto.HotcResponseDto;
import com.nagarro.dto.PackResponseDto;
import com.nagarro.dto.PickResponseDto;
import com.nagarro.entity.Orders;
import com.nagarro.service.OrderService;
import com.nagarro.service.OrderStatusService;

@RestController
public class OrderController {
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	@Autowired
	OrderService orderService;
	@Autowired
	OrderStatusService statusService; 
	
	@PostMapping("order")
	public ResponseEntity<String> placeOrder(@RequestBody Orders order){
		logger.info("Order placement step initiated");
		return ResponseEntity.ok().body(orderService.orderPlaced(order));
	}
	
	@PatchMapping("order/allocation/{orderId}")
	public ResponseEntity<String> allocateOrder(@PathVariable ("orderId") Long orderId){
		logger.info("Order allocation step initiated");
		return ResponseEntity.ok().body(orderService.orderAllocation(orderId));
		//orderService.orderAllocation(orderId);
	}
	@GetMapping("order/updates/{orderId}")
	public ResponseEntity<String> orderUpdates(@PathVariable ("orderId") Long orderId){
		logger.info("Order status check step initiated");
		return ResponseEntity.ok().body(statusService.getAllStatus(orderId));
		//orderService.orderAllocation(orderId);
	}
	@PostMapping("order/pick/{orderId}")
	public ResponseEntity<String> orderPickConfirmation(@RequestBody PickResponseDto response ,@PathVariable ("orderId") Long orderId){
		logger.info("Order pickup step initiated");
		return ResponseEntity.ok().body(orderService.orderPick(orderId, response.getRes()));
	
	}
	@PatchMapping("order/pack/{orderId}")
	public ResponseEntity<String> packOrder(@RequestBody PackResponseDto response, @PathVariable ("orderId") Long orderId){
		logger.info("Order packing step initiated");
		return ResponseEntity.ok().body(orderService.orderScan(orderId,response.getRes()));
		//orderService.orderAllocation(orderId);
	}
	@PostMapping("order/hotc/{orderId}")
	public ResponseEntity<String> hotcInfo(@RequestBody HotcResponseDto response,@PathVariable ("orderId") Long orderId){
		logger.info("Order hotc step initiated");
		return ResponseEntity.ok().body(orderService.hotc(orderId,response.getRes()));
		//orderService.orderAllocation(orderId);
	}
	@PostMapping("order/delivery/{orderId}")
	public ResponseEntity<String> deliveryInfo(@RequestBody DeliveryResponseDto response,@PathVariable ("orderId") Long orderId){
		logger.info("Order delivery step initiated");
		return ResponseEntity.ok().body(orderService.orderDelivery(orderId,response.getRes()));
		//orderService.orderAllocation(orderId);
	}
}
