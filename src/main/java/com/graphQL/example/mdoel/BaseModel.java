package com.graphQL.example.mdoel;

import com.graphQL.example.exception.handler.BaseConversationException;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;

public abstract class BaseModel {
	public <S> S toDTO(Class<S> clazz, MapperFacade mapper) throws BaseConversationException {

		try {
			return mapper.map(this, clazz);
		} catch (SecurityException e) {
			throw new BaseConversationException(e);
		} catch (Exception e) {
			throw new BaseConversationException(e);
		}
	}

	public <S> S toDTO(Class<S> clazz) throws BaseConversationException{

		try {

			Constructor<S> constructor = clazz.getConstructor();
			Object objDTO = constructor.newInstance();

			BeanUtils.copyProperties(this, objDTO);
			return clazz.cast(objDTO);

		} catch (SecurityException e) {
			throw new BaseConversationException(e);
		} catch (Exception e) {
			throw new BaseConversationException(e);
		}
	}
}
