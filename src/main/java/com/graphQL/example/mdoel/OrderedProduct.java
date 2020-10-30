package com.graphQL.example.mdoel;

public class OrderedProduct extends BaseModel{

	private String productID;

	private int orderedQuantity;

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
