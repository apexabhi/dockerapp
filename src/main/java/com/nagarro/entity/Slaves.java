package com.nagarro.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Slaves {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long slaveId;
	private String slaveName;
	private String location;
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private Seller seller;
	@OneToMany(mappedBy = "slave", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Orders> orderList = new ArrayList<>();
	
	public Slaves() {
		
	}

	public Long getSlaveId() {
		return slaveId;
	}

	public void setSlaveId(Long slaveId) {
		this.slaveId = slaveId;
	}

	public String getSlaveName() {
		return slaveName;
	}

	public void setSlaveName(String slaveName) {
		this.slaveName = slaveName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public List<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}
	
	
	
	
}
