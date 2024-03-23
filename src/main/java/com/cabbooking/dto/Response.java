package com.cabbooking.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    T data;
    String message;
    HttpStatus status;
    int statusCode;
}

