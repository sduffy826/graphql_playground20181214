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
import java.io.IOException;

public class HelloWorldDataFetcher {
  
  public void runIt() {
    ClassLoader classLoader = getClass().getClassLoader();
    File schemaFile = new File(classLoader.getResource("helloSchema.graphqls").getFile());

    System.out.println("Path: " + schemaFile.getPath());
    System.out.println("Absolute Path: " + schemaFile.getAbsolutePath());
    
    // String schema = "type Query{hello: String}";
    SchemaParser schemaParser = new SchemaParser();
       
    TypeDefinitionRegistry typeDefinitionRegistry = schemaParser.parse(schemaFile);
    
    RuntimeWiring runtimeWiring = newRuntimeWiring()
        .type("Query", builder -> builder
            .dataFetcher("hello",  new StaticDataFetcher("world"))            
            .dataFetcher("cat",  DataRepository.getDataForCat())
            .dataFetcher("sean", new StaticDataFetcher("Duffy")) )
        .build();
   
    SchemaGenerator schemaGenerator = new SchemaGenerator();
    GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);
    
    GraphQL build = GraphQL.newGraphQL(graphQLSchema).build();
    ExecutionResult executionResult = build.execute("{hello}");
    System.out.println(executionResult.getData().toString());
    
    executionResult = build.execute("{sean}");
    System.out.println(executionResult.getData().toString());
    
    System.out.println("Hit enter key to continue");
    try {
      System.in.read();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    
    executionResult = build.execute("query { cat(name: \"foo\") { color name } }");
    System.out.println(executionResult.getData().toString());
  }
    
  public static void main(String[] args) {
    HelloWorldDataFetcher helloWorldDataFetcher = new HelloWorldDataFetcher();
    helloWorldDataFetcher.runIt();
  }     
    
}
