package com.ashu.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v6/hello")
public class HelloWorldControllerV6 extends HelloWorldControllerBase {

    @GetMapping
    public String hello() {
        return getResponse("v6");
    }

}
