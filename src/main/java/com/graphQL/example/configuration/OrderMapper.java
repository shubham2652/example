package com.graphQL.example.configuration;

import com.graphQL.example.mdoel.DTO.OrderDTO;
import com.graphQL.example.mdoel.Order;
import ma.glasnost.orika.MapperFactory;
import net.rakugakibox.spring.boot.orika.OrikaMapperFactoryConfigurer;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper implements OrikaMapperFactoryConfigurer {

	@Override
	public void configure(MapperFactory orikaMapperFactory) {
		orikaMapperFactory.classMap(Order.class, OrderDTO.class)
			.byDefault()
			.field("orderID","orderID")
			.field("customerName","customerName")
			.field("product","product")
			.register();
	}
}
