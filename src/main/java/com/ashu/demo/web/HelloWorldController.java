package com.ashu.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
@RestController
public class HelloWorldController {

    @GetMapping
    public String hi() {
        return "Hello from ip address - " + getIpAddress();
    }

    private String getIpAddress() {
        String ipAddress = "Not found";
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            ipAddress = ip.getHostAddress();
            log.info("Current IP address : " + ipAddress);
        } catch (UnknownHostException e) {
            log.error(e.getMessage());
        }
        return ipAddress;
    }
}
