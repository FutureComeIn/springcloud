package com.future;

import com.future.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//加载自定义Ribbon配置类，执行自定义的Ribbon负载均衡算法
@RibbonClient(name="server-provider",configuration= MySelfRule.class)
//hystrix熔断器功能添加
@EnableCircuitBreaker
public class ClientRibbonApplication {
    public static void main(String[] args){
        SpringApplication.run(ClientRibbonApplication.class,args);
    }
}
