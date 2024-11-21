package com.example.GrapheQL.exception;

import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.execution.DataFetcherExceptionHandler;
import graphql.schema.DataFetchingEnvironment;
import graphql.ErrorType;  // Ajoutez cet import
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class GraphQLExceptionHandler implements DataFetcherExceptionHandler {

    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        return GraphqlErrorBuilder.newError()
                .message(ex.getMessage())
                .locations((List<graphql.language.SourceLocation>) null)  // Vous pouvez fournir des positions spécifiques si nécessaire
                .errorType(ErrorType.DataFetchingException)  // Correction ici : utiliser ErrorType directement
                .build();
    }
}
