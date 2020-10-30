package com.graphQL.example.mdoel.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderedProductDTO extends BaseDTO<String>{

	private static final long serialVersionUID = -1288038847433061411L;

	@NotNull
	@JsonProperty("productID")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String productID;

	@Min(1)
	@JsonProperty("orderedQuantity")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private int orderedQuantity;

	@JsonProperty("orderedPrice")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private float orderedPrice;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}

	public float getOrderedPrice() {
		return orderedPrice;
	}

	public void setOrderedPrice(float orderedPrice) {
		this.orderedPrice = orderedPrice;
	}
}
