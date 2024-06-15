package com.systems.finance.controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.kickstart.execution.GraphQLObjectMapper;
import graphql.kickstart.execution.GraphQLRequest;
import graphql.kickstart.tools.SchemaParser;
import graphql.kickstart.tools.SchemaParserBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class GraphQLController {

    private final GraphQL graphQL;

    @Autowired
    public GraphQLController(SchemaParser schemaParser) {
        this.graphQL = GraphQL.newGraphQL(schemaParser.makeExecutableSchema()).build();
    }

    @PostMapping(value = "/graphql", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> graphql(@RequestBody GraphQLRequest request) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(request.getQuery())
                .operationName(request.getOperationName())
                .context(request.getContext())
                .build();

        ExecutionResult executionResult = this.graphQL.execute(executionInput);
        Map<String, Object> result = new LinkedHashMap<>(executionResult.toSpecification());

        if (executionResult.getErrors().isEmpty()) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }
}

