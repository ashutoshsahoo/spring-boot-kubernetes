package com.ashu.demo;

import com.ashu.demo.web.HelloWorldControllerV6;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootKubernetesApplicationTests {

    @Autowired
    private HelloWorldControllerV6 helloWorldControllerV6;

    @Test
    void contextLoads() {
        Assertions.assertNotNull(helloWorldControllerV6);
    }

}
