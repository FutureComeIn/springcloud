package com.future;

import com.future.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients()  //启动feign服务
//Feign集成了Ribbon：利用Ribbon维护server-provider服务列表信息,调用自定义负载均衡算法（不调用，就使用默认随机算法）
@RibbonClient(name="server-provider",configuration= MySelfRule.class)
public class ClientFeignApplication {
    public static void main(String[] args){
        SpringApplication.run(ClientFeignApplication.class,args);
    }
}
