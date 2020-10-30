package com.graphQL.example.mdoel.DTO;

import com.graphQL.example.exception.handler.DTOConversationException;
import ma.glasnost.orika.MapperFacade;

import java.io.Serializable;

public abstract class BaseDTO<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	public <T> T toModel(Class<T> clazz, MapperFacade mapper) throws DTOConversationException {
		try {
			return mapper.map(this, clazz);
		} catch (Exception e) {
			throw new DTOConversationException(
				String
					.format(
						"Error converting to class %s, message %s",
						clazz.getTypeName(),
						e.getLocalizedMessage()));
		}
	}

	public <T> T toModel(Class<T> type, Object model, MapperFacade mapper) throws DTOConversationException {
		try {
			mapper.map(this, model);
			return type.cast(model);
		} catch (Exception e) {
			throw new DTOConversationException(String.format("Error converting to class %s", type.getTypeName()));
		}
	}

}
