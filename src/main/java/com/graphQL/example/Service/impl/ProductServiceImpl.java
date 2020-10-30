package com.graphQL.example.Service.impl;

import com.graphQL.example.Service.BaseService;
import com.graphQL.example.Service.ProductService;
import com.graphQL.example.exception.handler.BuisnessValidationException;
import com.graphQL.example.exception.handler.DTOConversationException;
import com.graphQL.example.exception.handler.EntityNotFoundException;
import com.graphQL.example.exception.handler.ValidationException;
import com.graphQL.example.exception.response.APIResponse;
import com.graphQL.example.mdoel.DTO.ProductDTO;
import com.graphQL.example.mdoel.Product;
import com.graphQL.example.repository.ProductRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl extends BaseService<Product, ProductDTO> implements ProductService {

	private MapperFacade mapperFacade;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	MongoTemplate mongoTemplate;

	public ProductServiceImpl(MapperFacade mapperFacade) {
		super(mapperFacade);
	}

	@Override
	public APIResponse addProduct(ProductDTO productDTO) throws DTOConversationException, BuisnessValidationException{
		Product product;
		APIResponse apiResponse = new APIResponse();
		try {
			product = productDTO.toModel(Product.class, getMapper());
		}catch (DTOConversationException ex){
			throw new DTOConversationException("Conversation Exception");
		}catch (EntityNotFoundException ex){
			throw new DTOConversationException("Product Exception");
		}
		if(Objects.nonNull(product)) {
			Product existingProduct = productRepository.findByProductName(product.getProductName());
			if(existingProduct==null){
				productRepository.save(product);
				apiResponse = new APIResponse(productDTO, "Product added Successfully");
			}else{
				throw new BuisnessValidationException("Product Already Exists");
			}
		}

		return apiResponse;
	}

	@Override
	public DataFetcher findByProductName() {
		return dataFetchingEnvironment -> {
			String productName = dataFetchingEnvironment.getArgument("productName");
			ProductDTO productDTO;
			if(!productName.equals(null)) {
				Product product = mongoTemplate.findOne(Query.query(Criteria.where("productName").is(productName)), Product.class);
				productDTO = product.toDTO(ProductDTO.class, getMapper());
				if(Objects.nonNull(productDTO)) {
					return productDTO;
				}else{
					throw new EntityNotFoundException(String.format("%s not found",productName));
				}
			}else{
				throw new ValidationException("Product name is not valid");
			}
		};
	}

	@Override public DataFetcher listByProductName() {
		return dataFetchingEnvironment -> {
			String queryText = dataFetchingEnvironment.getArgument("productName");
			if(!queryText.equals(null)){
				TextCriteria textCriteria = TextCriteria.forDefaultLanguage().caseSensitive(false)
					.matching(queryText);

				Query query = TextQuery.queryText(textCriteria)
					.sortByScore();

				List<Product> productList =mongoTemplate.find(query, Product.class);
				List<ProductDTO> productDTOList = new ArrayList<>();
				productList.stream().forEach(product -> {
					productDTOList.add(product.toDTO(ProductDTO.class,getMapper()));
				});
				return productDTOList;
			}else{
				throw new ValidationException("Query is not valid");
			}
		};
	}

	@Override
	public APIResponse findByName(String name) throws EntityNotFoundException,DTOConversationException{
		APIResponse apiResponse = null;
		ProductDTO productDTO;

		Product product = mongoTemplate.findOne(Query.query(Criteria.where("productName").is(name)), Product.class);
		if(Objects.nonNull(product)) {
			productDTO = product.toDTO(ProductDTO.class, getMapper());
			apiResponse = new APIResponse(productDTO);
			return apiResponse;
		}else{
			throw new EntityNotFoundException(String.format("%s not found",name));
		}
	}

	@Override
	public APIResponse listByREST(String queryText) {
		APIResponse apiResponse;
		TextCriteria textCriteria = TextCriteria.forDefaultLanguage().caseSensitive(false)
			.matching(queryText);

		Query query = TextQuery.queryText(textCriteria)
			.sortByScore();

		List<Product> productList =mongoTemplate.find(query, Product.class);
		List<ProductDTO> productDTOList = new ArrayList<>();
		productList.stream().forEach(product -> {
			productDTOList.add(product.toDTO(ProductDTO.class,getMapper()));
		});
		if(productDTOList.size()>0){
			apiResponse = new APIResponse(productDTOList);
			return apiResponse;
		}
		throw new ValidationException("Not Found");
	}
}


