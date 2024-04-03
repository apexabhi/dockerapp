package com.nagarro.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Seller {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sellerId;
	private String sellerName;
	private String erpno;
	private int slaveCount;
	@OneToMany(mappedBy = "seller", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Slaves> slaveList = new ArrayList<>();
	
	public Seller() {
		
	}

	public Long getSellerId() {
		return sellerId;
	}

	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getErpno() {
		return erpno;
	}

	public void setErpno(String erpno) {
		this.erpno = erpno;
	}

	public int getSlaveCount() {
		return slaveCount;
	}

	public void setSlaveCount(int slaveCount) {
		this.slaveCount = slaveCount;
	}

	public List<Slaves> getSlaveList() {
		return slaveList;
	}

	public void setSlaveList(List<Slaves> slaveList) {
		this.slaveList = slaveList;
	}
	

}
