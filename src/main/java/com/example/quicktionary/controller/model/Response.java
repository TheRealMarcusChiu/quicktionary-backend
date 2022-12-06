package com.example.quicktionary.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Response {
    private Boolean success = true;
    private String errorMessage = "";

    private static Response.ResponseBuilder builder() {
        return new Response().toBuilder();
    }

    public static Response success() {
        return Response.builder().success(true).build();
    }

    public static Response fail(final String errorMessage) {
        return Response.builder().success(false).errorMessage(errorMessage).build();
    }
}
