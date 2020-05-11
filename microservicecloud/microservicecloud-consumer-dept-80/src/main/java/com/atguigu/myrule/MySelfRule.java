package com.atguigu.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class MySelfRule
{
	@Bean
	public IRule myRule()
	{
		//return new RoundRobinRule();
		return new RandomRule_ZY(); // 我自定义为每台机器5次 的算法
	}
}
