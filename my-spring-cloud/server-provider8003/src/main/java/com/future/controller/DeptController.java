package com.future.controller;

import com.future.entity.Dept;
import com.future.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用
// (不返回 界面，需要在方法上加@ResponseBody)
@RestController
public class DeptController {

    @Autowired
    DeptService deptService;

    @RequestMapping(value="/getDept",method= RequestMethod.GET)
    public List<Dept> getDept(){
        return deptService.selectDept();
    }

    @RequestMapping(value="/getByNo",method=RequestMethod.GET)
    public Dept getDeptByNo(Long deptno){
        return deptService.selectByNo(deptno);
    }

    @RequestMapping(value = "/get/{deptno}", method = RequestMethod.GET)
    public Dept get(@PathVariable("deptno") Long deptno) {
        return deptService.selectByNo(deptno);
        /**
         *  @PathVariabl 接收参数：前端访问写法--http://localhost:8001/get/2
         */
    }

    @RequestMapping(value="/insert",method=RequestMethod.POST)
    public Integer insert(Dept dept){
        return deptService.insert(dept);   //不加 @RequestBody，参数传不过来，都为null
    }

    @RequestMapping(value = "insert2", method = RequestMethod.POST)
    public Integer add(@RequestBody Dept dept) {
        return deptService.insert(dept);
    }


}
