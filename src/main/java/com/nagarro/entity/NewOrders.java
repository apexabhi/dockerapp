//package com.nagarro.entity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.OneToOne;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.NotNull;
//
//@Entity
//public class Orders {
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long orderId;
//	@NotBlank(message = "Name is required")
//	private String orderName;
//	@NotNull(message = "Price is required")
//	private Double price;
//	@NotBlank(message = "Address is required")
//	private String address;
//	@NotBlank(message = "Placed by is required")
//	private String placedBy;
//	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
//    private List<OrderStatus> orderStatusList = new ArrayList<>();
//	@ManyToOne
//	@JoinColumn(name = "slave_id")
//	private Slaves slave;
//	
//	@ManyToOne
//	@JoinColumn(name = "lp_id")
//	private LogisticPartners logistic;
//	
//	public Orders() {
//		
//	}
//	public Long getOrderId() {
//		return orderId;
//	}
//	public void setOrderId(Long orderId) {
//		this.orderId = orderId;
//	}
//	public String getOrderName() {
//		return orderName;
//	}
//	public void setOrderName(String orderName) {
//		this.orderName = orderName;
//	}
//	public Double getPrice() {
//		return price;
//	}
//	public void setPrice(Double price) {
//		this.price = price;
//	}
//	public String getAddress() {
//		return address;
//	}
//	public void setAddress(String address) {
//		this.address = address;
//	}
//	public List<OrderStatus> getOrderStatusList() {
//		return orderStatusList;
//	}
//	public void setOrderStatusList(List<OrderStatus> orderStatusList) {
//		this.orderStatusList = orderStatusList;
//	}
//	public Slaves getSlave() {
//		return slave;
//	}
//	public void setSlave(Slaves slave) {
//		this.slave = slave;
//	}
//	public LogisticPartners getLogistic() {
//		return logistic;
//	}
//	public void setLogistic(LogisticPartners logistic) {
//		this.logistic = logistic;
//	}
//	public String getPlacedBy() {
//		return placedBy;
//	}
//	public void setPlacedBy(String placedBy) {
//		this.placedBy = placedBy;
//	}
//	
//	
//	
//}
