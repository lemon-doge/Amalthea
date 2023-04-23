package com.tinkoff.sirius.amalthea.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.HttpStatus;


@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDetailsDTO {
    @JsonProperty
    private final String message;
    @JsonProperty
    private final HttpStatus status;
    @JsonProperty
    private final Long timestamp;
    @JsonProperty
    private final String path;
}

