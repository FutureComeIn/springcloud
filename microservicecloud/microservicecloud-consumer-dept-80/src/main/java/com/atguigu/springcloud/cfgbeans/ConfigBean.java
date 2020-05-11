package com.atguigu.springcloud.cfgbeans;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean //boot -->spring   applicationContext.xml --- @Configuration配置   ConfigBean = applicationContext.xml
{
	@Bean
	@LoadBalanced   //客户端开启Ribbon负载均衡（Ribbon是基于Netflix Ribbon实现的一套客户端 负载均衡工具）
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	@Bean
	public IRule myRule() {
		//RetryRule() --重试，当3个provider都正常就相当于轮询，当故意关掉一个，在轮询到关掉的这一个几次后，
		// 将不在轮询它，只轮询正常的另外两个
		//RoundRobinRule() 默认的轮询
		return new RandomRule();  //随机算法
	}

}

//@Bean
//public UserServcie getUserServcie()
//{
//	return new UserServcieImpl();
//}
// applicationContext.xml == ConfigBean(@Configuration)
//<bean id="userServcie" class="com.atguigu.tmall.UserServiceImpl">