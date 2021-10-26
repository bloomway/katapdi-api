package com.kleematik.katapdi.infra.endpoints.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@ToString
@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {

    private final Long timestamp;
    private final int statusCode;
    private final String status;
    private final String error;
    private final Map<String, ?> data;
}
