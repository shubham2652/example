package com.graphQL.example.mdoel.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO extends BaseDTO<String>{

	private static final long		   serialVersionUID	= -3799798707498800055L;

	@JsonProperty("productID")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String productID;

	@NotNull
	@JsonProperty("productName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String productName;


	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonProperty("sku")
	@NotBlank(message = "SKU can't be null")
	private String sku;

	@JsonProperty("category")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String category;

	@NotNull
	@JsonProperty("availableQuantity")
	private int availableQuantity;

	@NotNull
	@JsonProperty("productPrice")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String productPrice;

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}
}
