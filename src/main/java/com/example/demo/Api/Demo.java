package com.example.demo.Api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/demo")
@RequiredArgsConstructor
public class Demo {
    @GetMapping(value = "/test")
    public String test(){
        return "Success";
    }
}
