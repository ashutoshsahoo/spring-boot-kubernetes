package com.ashu.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Adds sample logs into logstash.
 */
@RestController
@RequestMapping(value = "/elk")
@Slf4j
public class ELKController {


    @GetMapping
    public String helloWorld() {
        String response = "Welcome to spring-boot-elk demo," + new Date();
        log.info(response);
        return response;
    }

    @GetMapping(value = "/exception")
    public void exception() {
        try {
            throw new RuntimeException("Exception has occurred....");
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
        }
    }
}
