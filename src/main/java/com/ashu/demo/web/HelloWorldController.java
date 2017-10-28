package com.ashu.demo.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping
	public String hi() {
		return "Hi Ashutosh : ip address - " + getIpAddress();
	}

	private String getIpAddress() {
		String ipAddress = "Not found";
		InetAddress ip;
		try {

			ip = InetAddress.getLocalHost();
			ipAddress = ip.getHostAddress();
			System.out.println("Current IP address : " + ipAddress);
			
		} catch (UnknownHostException e) {
			System.err.println(e.getMessage());
		}
		return ipAddress;
	}
}
