package com.ashu.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class HelloWorldControllerBase {

    @Value("${git.build.version}")
    private String gitBuildVersion;

    protected String getResponse(String version) {
        log.info("Requested for api version={} with build version={}", version, gitBuildVersion);
        return String.format("Hello from api version %s and ip address %s", version, getHostIPAddress());
    }

    private String getHostIPAddress() {
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
