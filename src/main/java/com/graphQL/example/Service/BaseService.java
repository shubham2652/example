package com.graphQL.example.Service;

import com.graphQL.example.mdoel.BaseModel;
import com.graphQL.example.mdoel.DTO.BaseDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseService<T extends BaseModel, S extends BaseDTO<String>> {
	private MapperFacade mapper;

	public BaseService(MapperFacade mapperFacade) {
		this.mapper = mapperFacade;
	}

	@Autowired
	public void setMapperFactory(MapperFactory mapperFactory) {
		this.mapper = mapperFactory.getMapperFacade();
	}

	public MapperFacade getMapper() {
		return mapper;
	}
}
