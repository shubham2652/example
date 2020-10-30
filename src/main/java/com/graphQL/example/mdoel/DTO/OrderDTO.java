package com.graphQL.example.mdoel.DTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.validation.Valid;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class OrderDTO extends BaseDTO<String>{

	private static final long serialVersionUID = 4633406599937845202L;

	@JsonProperty("orderID")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String orderID;

	@NotEmpty
	@JsonProperty("customerName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String customerName;

	private @Valid List<OrderedProductDTO> product;


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

	public @Valid List<OrderedProductDTO> getProduct() {
		return product;
	}

	public void setProduct(List<OrderedProductDTO> product) {
		this.product = product;
	}
}
