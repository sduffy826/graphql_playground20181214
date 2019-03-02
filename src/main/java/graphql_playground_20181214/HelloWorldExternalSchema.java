package graphql_playground_20181214;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.StaticDataFetcher;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
//import graphql.*;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

import java.io.File;

public class HelloWorldExternalSchema {

  public static void main(String[] args) {
    // File schemaFile = loadSchema("helloSchema.graphqls");
    File schemaFile = new File("./src/main/resources/helloSchema.graphqls");
    System.out.println("Path: " + schemaFile.getPath());
    System.out.println("Absolute Path: " + schemaFile.getAbsolutePath());
    
    // String schema = "type Query{hello: String}";
    SchemaParser schemaParser = new SchemaParser();
       
    TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schemaFile);
    
    RuntimeWiring runtimeWiring = newRuntimeWiring()
        .type("Query", builder -> builder
            .dataFetcher("hello",  new StaticDataFetcher("world"))
            .dataFetcher("sean", new StaticDataFetcher("Duffy")) )
        .build();
   
    SchemaGenerator schemaGenerator = new SchemaGenerator();
    GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    
    GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
    ExecutionResult executionResult = build.execute("{hello}");
    System.out.println(executionResult.getData().toString());
    executionResult = build.execute("{sean}");
    System.out.println(executionResult.getData().toString());
  }
}
