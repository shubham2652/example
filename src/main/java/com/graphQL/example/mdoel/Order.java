package com.graphQL.example.mdoel;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "orders")
public class Order extends BaseModel{
	@Id
	private String orderID;

	private String customerName;

	private List<OrderedProduct> product;

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public List<OrderedProduct> getProduct() {
		return product;
	}

	public void setProduct(List<OrderedProduct> product) {
		this.product = product;
	}
}
