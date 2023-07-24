package com.example.demo.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse implements IErr{
    private String err;
}
