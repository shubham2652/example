package com.graphQL.example.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphQL.example.Service.ProductService;
import com.graphQL.example.Service.impl.ProductServiceImpl;
import com.graphQL.example.Validation.AddNewValidation;
import com.graphQL.example.exception.handler.BuisnessValidationException;
import com.graphQL.example.exception.handler.DTOConversationException;
import com.graphQL.example.exception.handler.EntityNotFoundException;
import com.graphQL.example.exception.handler.ValidationException;
import com.graphQL.example.exception.response.APIResponse;
import com.graphQL.example.graphQLResolvers.GraphQLProvider;
import com.graphQL.example.mdoel.DTO.ProductDTO;
import graphql.ExecutionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.LinkedHashMap;
import java.util.Objects;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired GraphQLProvider graphQLProvider;

	@PostMapping("/add")
	public APIResponse addProduct(@Valid @RequestBody(required = true)ProductDTO productDTO) throws BuisnessValidationException, EntityNotFoundException {
		return productService.addProduct(productDTO);
	}

	@GetMapping("/fn")
	public ResponseEntity<Object> findByName(@NotNull @RequestBody String query) throws JsonProcessingException {
		APIResponse apiResponse;
		ExecutionResult result = graphQLProvider.graphQL().execute(query);
		if(Objects.nonNull(result.getData())) {
			LinkedHashMap<String, Object> res = result.getData();
			apiResponse = new APIResponse(res.get("productByProductName"));
			return ResponseEntity.ok(apiResponse);
		}
		throw new ValidationException("Something Went Wrong");
	}

	@GetMapping("/fnt")
	public ResponseEntity<Object> findByProductName(@NotNull @RequestBody String name) {
		return ResponseEntity.ok(productService.findByName(name));
	}

	@GetMapping("/searchByQL")
	public ResponseEntity<Object> listByProductName(@NotNull @RequestBody String name){
		APIResponse apiResponse;
		ExecutionResult result = graphQLProvider.graphQL().execute(name);
		if(Objects.nonNull(result.getData())) {
			LinkedHashMap<String, Object> res = result.getData();
			apiResponse = new APIResponse(res.get("listByProductName"));
			return ResponseEntity.ok(apiResponse);
		}
		throw new ValidationException("Something Went Wrong");
	}

	@GetMapping("/searchByRest")
	public ResponseEntity<Object> listByRest(@NotNull @RequestBody String name){
		return ResponseEntity.ok(productService.listByREST(name));
	}
}
