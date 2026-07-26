package com.clx.dto;

import java.util.List;
import java.util.Map;
import io.javalin.validation.ValidationError;

public record ErrorResponse(String error, Map<String, List<ValidationError<Object>>> details) {
}
