package com.example.demo.Response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmptyResponse implements IEmpty {
    private String message;
}
