package com.nagarro.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity
public class Orders {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	@NotBlank(message = "Name is required")
	private String orderName;
	@NotNull(message = "Price is required")
	private Double price;
	@NotBlank(message = "Address is required")
	private String address;
	@NotBlank(message = "Placed by is required")
	private String placedBy;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderStatus> orderStatusList = new ArrayList<>();
	@ManyToOne
	@JoinColumn(name = "slave_id")
	private Slaves slave;	
	@ManyToOne
	@JoinColumn(name = "lp_id")
	private LogisticPartners logistic;
	private String productName;
	private String productCategory;
	private int quantity;
	
	private Orders() {
		
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<OrderStatus> getOrderStatusList() {
		return orderStatusList;
	}
	public void setOrderStatusList(List<OrderStatus> orderStatusList) {
		this.orderStatusList = orderStatusList;
	}
	public Slaves getSlave() {
		return slave;
	}
	public void setSlave(Slaves slave) {
		this.slave = slave;
	}
	public LogisticPartners getLogistic() {
		return logistic;
	}
	public void setLogistic(LogisticPartners logistic) {
		this.logistic = logistic;
	}
	public String getPlacedBy() {
		return placedBy;
	}
	public void setPlacedBy(String placedBy) {
		this.placedBy = placedBy;
	}
	
	// Builder pattern
    public static class Builder {
        private String orderName;
        private Double price;
        private String address;
        private String placedBy;
        private List<OrderStatus> orderStatusList = new ArrayList<>();
        private Slaves slave;
        private LogisticPartners logistic;
        private String productName;
        private String productCategory;
        private int quantity;

        public Builder(String orderName, Double price, String address, String placedBy) {
            this.orderName = orderName;
            this.price = price;
            this.address = address;
            this.placedBy = placedBy;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder productCategory(String productCategory) {
            this.productCategory = productCategory;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }


        public Builder orderStatusList(List<OrderStatus> orderStatusList) {
            this.orderStatusList=orderStatusList;
            return this;
        }

        public Builder slave(Slaves slave) {
            this.slave = slave;
            return this;
        }

        public Builder logistic(LogisticPartners logistic) {
            this.logistic = logistic;
            return this;
        }

        public Orders build() {
            Orders orders = new Orders();
            orders.orderName = this.orderName;
            orders.price = this.price;
            orders.address = this.address;
            orders.placedBy = this.placedBy;
            orders.productName = this.productName;
            orders.productCategory = this.productCategory;
            orders.quantity = this.quantity;
            orders.orderStatusList.addAll(this.orderStatusList);
            orders.slave = this.slave;
            orders.logistic = this.logistic;
            return orders;
        }
    }

	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

    

}
