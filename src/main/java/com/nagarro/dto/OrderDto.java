//OrderDto using builder pattern
//testing of builder pattern is done in orderserviceimpl class
package com.nagarro.dto;

import java.util.ArrayList;
import java.util.List;
import com.nagarro.entity.LogisticPartners;
import com.nagarro.entity.OrderStatus;
import com.nagarro.entity.Slaves;

public class OrderDto {

	private Long orderId;
	private String orderName;
	private Double price;
	private String address;
	private String placedBy;
    private List<OrderStatus> orderStatusList = new ArrayList<>(); //optional
	private Slaves slave;	//optional
	private LogisticPartners logistic; //optional 
	private String productName; //optional
	private String productCategory; //optional
	private int quantity; //optional
	
	private OrderDto() {
		
	}
	public Long getOrderId() {
		return orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public Double getPrice() {
		return price;
	}
	public String getAddress() {
		return address;
	}
	public List<OrderStatus> getOrderStatusList() {
		return orderStatusList;
	}
	public Slaves getSlave() {
		return slave;
	}
	public LogisticPartners getLogistic() {
		return logistic;
	}
	public String getPlacedBy() {
		return placedBy;
	}
	public String getProductName() {
		return productName;
	}
	public String getProductCategory() {
		return productCategory;
	}
	public int getQuantity() {
		return quantity;
	}


	// Inner Builder class
    public static class Builder {
    	private Long orderId;
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

        public Builder(Long orderId,String orderName, Double price, String address, String placedBy) {
            this.orderId=orderId;
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

        public OrderDto build() {
            OrderDto orders = new OrderDto();
            orders.orderId=this.orderId;
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


	@Override
	public String toString() {
		return "OrderDto [orderId=" + orderId + ", orderName=" + orderName + ", price=" + price + ", address=" + address
				+ ", placedBy=" + placedBy + ", orderStatusList=" + orderStatusList + ", slave=" + slave + ", logistic="
				+ logistic + ", productName=" + productName + ", productCategory=" + productCategory + ", quantity="
				+ quantity + "]";
	}
    

}
