package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.springcloud.entities.Dept;


/**
 * 
 * @Description: 修改microservicecloud-api工程，根据已经有的DeptClientService接口
   新建一个实现了FallbackFactory接口的类DeptClientServiceFallbackFactory
 * @author zzyy
 * @date 2018年4月21日
 */
//加了服务熔断hystrix的feigin功能注解写法
@FeignClient(value = "MICROSERVICECLOUD-DEPT",fallbackFactory=DeptClientServiceFallbackFactory.class)
//@FeignClient(value = "MICROSERVICECLOUD-DEPT")  没加服务熔断hystrix的feigin功能注解写法
public interface DeptClientService
{
	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") long id);

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list();

	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(Dept dept);
}
