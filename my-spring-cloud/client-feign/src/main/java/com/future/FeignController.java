package com.future;

import com.future.entity.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignController {

    @Autowired
    FeignService feignService;

    @RequestMapping(value="/feign/getDept",method = RequestMethod.GET)
    public List<Dept> getDept(){
        return feignService.getDept();
    }

    @RequestMapping(value="/feign/getByNo/{deptno}",method=RequestMethod.GET)
    public Dept getByNo(@PathVariable("deptno") Long deptno){
        return feignService.getByNo(deptno);
    }

    @RequestMapping(value="/feign/insert",method=RequestMethod.GET)
    public Integer insert(Dept dept){
        return feignService.insert(dept);
    }
}
