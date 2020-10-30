package com.graphQL.example.graphQLResolvers;

import com.graphQL.example.Service.ProductService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.springframework.core.io.Resource;

@Component
public class GraphQLProvider {
	private GraphQL graphQL;

	@Value("classpath:product.graphqls")
	Resource resource;

	@Bean
	public GraphQL graphQL(){
		return graphQL;
	}

	@Autowired ProductService productService;

	@PostConstruct
	public void init() throws IOException{
		File schemaFile = resource.getFile();
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring runtimeWiring = buildRuntimeWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
		graphQL = GraphQL.newGraphQL(schema).build();
	}

	private RuntimeWiring buildRuntimeWiring(){
		return RuntimeWiring.newRuntimeWiring()
			.type("Query", typeWiring -> typeWiring.dataFetcher("productByProductName",productService.findByProductName())
																.dataFetcher("listByProductName",productService.listByProductName()))
			.build();
	}
}
