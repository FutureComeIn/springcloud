package com.future;

import com.future.entity.Dept;
import com.future.hystrix.FeignFallbackFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

//@FeignClient(value = "server-provider")
@FeignClient(value="server-provider",fallbackFactory = FeignFallbackFactory.class)
public interface FeignService {

	@RequestMapping(value="/getDept",method= RequestMethod.GET)
	public List<Dept> getDept();

	@RequestMapping(value = "/get/{deptno}", method = RequestMethod.GET)
	public Dept getByNo(@PathVariable("deptno") Long deptno);

	@RequestMapping(value = "/insert2", method = RequestMethod.POST)
	public Integer insert(Dept dept);
}
