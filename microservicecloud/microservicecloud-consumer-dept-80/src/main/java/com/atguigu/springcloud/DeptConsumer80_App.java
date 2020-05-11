package com.atguigu.springcloud;

import com.atguigu.myrule.MySelfRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient    //Ribbon负载均衡需要跟eureka整合，故加上该注解，碰巧pom文件也加了eureka的依赖

//启动该微服务的时候，去加载自定义Ribbon配置类，使配置生效，执行配置类里面自定义的Ribbon负载均衡算法
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
public class DeptConsumer80_App {
	public static void main(String[] args)
	{
		SpringApplication.run(DeptConsumer80_App.class, args);
	}
}
