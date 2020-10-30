package com.graphQL.example.configuration;

import com.graphQL.example.mdoel.DTO.ProductDTO;
import com.graphQL.example.mdoel.Product;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;

public class ProductMapper implements OrikaMapperFactoryConfigurer {

	@Override public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(Product.class, ProductDTO.class)
			.field("productID","productID")
			.field("productName","productName")
			.field("sku","sku")
			.field("category","category")
			.field("availableQuantity","availableQuantity")
			.field("productPrice","productPrice")
			.register();
	}
}
