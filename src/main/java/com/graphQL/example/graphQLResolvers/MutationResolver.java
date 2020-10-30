package com.graphQL.example.graphQLResolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.graphQL.example.Service.ProductService;
import com.graphQL.example.mdoel.Product;
import org.springframework.beans.factory.annotation.Autowired;

public class MutationResolver implements GraphQLMutationResolver {

	@Autowired
	ProductService productService;



}
