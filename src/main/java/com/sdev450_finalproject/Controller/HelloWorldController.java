package com.sdev450_finalproject.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HelloWorldController {
    @GetMapping("/hello")
    public String Hello() {
        return "Hello";
    }

    @GetMapping("/load")
    public String Load() {
        return "Hello";
    }
}
