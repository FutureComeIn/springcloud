package com.future;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServerProvider8002Application {
    public static void main(String[] args){
        SpringApplication.run(ServerProvider8002Application.class,args);
    }
}
