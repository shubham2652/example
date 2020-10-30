package com.graphQL.example.Service;

import com.graphQL.example.exception.handler.BuisnessValidationException;
import com.graphQL.example.exception.handler.DTOConversationException;

import com.graphQL.example.exception.handler.EntityNotFoundException;
import com.graphQL.example.exception.response.APIResponse;
import com.graphQL.example.mdoel.DTO.ProductDTO;
import com.graphQL.example.mdoel.Product;
import graphql.schema.DataFetcher;

public interface ProductService {
	APIResponse addProduct(ProductDTO productDTO)throws DTOConversationException, BuisnessValidationException;
	DataFetcher findByProductName();
	DataFetcher listByProductName();
	APIResponse findByName(String name) throws EntityNotFoundException, DTOConversationException;
	APIResponse listByREST(String name);
}
