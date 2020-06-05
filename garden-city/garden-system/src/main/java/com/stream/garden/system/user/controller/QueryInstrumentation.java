package com.stream.garden.system.user.controller;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.execution.instrumentation.*;
import graphql.execution.instrumentation.parameters.*;
import graphql.language.Document;
import graphql.validation.ValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author garden
 * @date 2020-05-25 16:18
 */
@Component
public class QueryInstrumentation extends SimpleInstrumentation {
    private Logger logger = LoggerFactory.getLogger(QueryInstrumentation.class);

    @Override
    public ExecutionInput instrumentExecutionInput(ExecutionInput executionInput, InstrumentationExecutionParameters parameters) {
        logger.error("执行方法");
        return executionInput;
    }
}
