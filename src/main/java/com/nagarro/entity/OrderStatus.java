package com.nagarro.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderStatus {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderStatusId;
	private String status;
	private String detail;
	private String ERPcode;
	private Date timeCreated;
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Orders order;
	public OrderStatus() {
		
	}
	public Long getOrderStatusId() {
		return orderStatusId;
	}
	public void setOrderStatusId(Long orderStatusId) {
		this.orderStatusId = orderStatusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getERPcode() {
		return ERPcode;
	}
	public void setERPcode(String eRPcode) {
		ERPcode = eRPcode;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Date getTimeCreated() {
		return timeCreated;
	}
	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}
	
	
	

}
