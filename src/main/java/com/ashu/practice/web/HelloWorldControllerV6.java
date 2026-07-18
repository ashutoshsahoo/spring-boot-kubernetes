package com.ashu.practice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/{version}", version = "v6")
public class HelloWorldControllerV6 extends HelloWorldControllerBase {

    @GetMapping(path = "/hello")
    public String hello() {
        return getResponse("v6");
    }

}
