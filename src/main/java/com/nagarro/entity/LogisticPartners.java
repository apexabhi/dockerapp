package com.nagarro.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class LogisticPartners {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lpId;
	 
	private String partnername;
	 
	private String partnerinitials;
	
	@OneToMany(mappedBy = "logistic", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Orders> orderList = new ArrayList<>();
	
	public LogisticPartners() {};

	public Long getLpid() {
		return lpId;
	}

	public void setLpid(Long lpId) {
		this.lpId = lpId;
	}

	public String getPartnername() {
		return partnername;
	}

	public void setPartnername(String partnername) {
		this.partnername = partnername;
	}

	public String getPartnerinitials() {
		return partnerinitials;
	}

	public void setPartnerinitials(String partnerinitials) {
		this.partnerinitials = partnerinitials;
	}
	
	

}

