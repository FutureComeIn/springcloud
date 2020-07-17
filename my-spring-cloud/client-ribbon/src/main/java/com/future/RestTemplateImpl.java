package com.future;

import com.future.entity.Dept;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

//测试：参数传不传token（http://localhost:6001/myzuul/user/getByNo?deptno=1&token=2）

@RestController
public class RestTemplateImpl {

    @Autowired
    RestTemplate restTemplate;

    private static final String SERVER_PROVIDER_NAME="http://SERVER-PROVIDER/";
    //也可以不用 服务提供者注册到eureka定义的服务名：	http://localhost:8001;

    @RequestMapping(value="/consumer/getDept",method= RequestMethod.GET)
    public List<Dept> getDept(){
        return restTemplate.getForObject(SERVER_PROVIDER_NAME+"getDept",List.class);
    }

    @RequestMapping(value="/consumer/get/{deptno}",method= RequestMethod.GET)
    public Dept getByNo(@PathVariable("deptno") Long deptno){
        return restTemplate.getForObject(SERVER_PROVIDER_NAME+"get/"+deptno,Dept.class);
    }

    @RequestMapping(value="/consumer/insert",method=RequestMethod.GET)
    public Integer insert(Dept dept){
        return restTemplate.postForObject(SERVER_PROVIDER_NAME+"insert",dept,Integer.class);
    }

    @RequestMapping(value="/consumer/insert2",method=RequestMethod.GET)
    public Integer insert2(Dept dept){
        return restTemplate.postForObject(SERVER_PROVIDER_NAME+"insert2",dept,Integer.class);
    }

    //****************服务熔断方法
    //一旦调用服务方法失败并抛出了错误信息后，会自动调用@HystrixCommand标注好的fallbackMethod调用类中的指定方法
    @RequestMapping(value="/consumer/getHystrix/{deptno}",method= RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrixGet",
            // Hystrix性能配置
            commandProperties = {
                    // 执行超时时间|default:1000
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"),
                    // 下面的为度量策略，即触发熔断的配置，这里为了测试均采用很极端的配置，实际开发中，这部分参数调优是很麻烦的...
                    // 触发断路最低请求数|default:20
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "5"),
                    // 统计周期内度量桶的数量，窗口拆分数|默认10
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "1"),
                    // 5s为一次统计周期，专业术语为：窗口维持时间|默认10000
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000"),
                    // 触发断路的时间值，即当触发熔断后的10s内均会快速失败，不会发起请求，当10s后才会关闭断路器，默认为5s
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000")
            },
            // 线程池参数
            threadPoolProperties = {
                    // 线程池核心数|default:10
                    @HystrixProperty(name = "coreSize", value = "10"),
                    // 队列长度|default:-1(SynchronousQueue)，值为正数时使用LinkedBlockQueue
                    @HystrixProperty(name = "maxQueueSize", value = "20"),
                    // 队满拒绝服务阈值|default:5|此值生效优先于队满
                    @HystrixProperty(name = "queueSizeRejectionThreshold", value = "15"),
            })
    public Dept getByNoHystrix(@PathVariable("deptno") Long deptno) {
        Dept dept=restTemplate.getForObject(SERVER_PROVIDER_NAME+"get/"+deptno,Dept.class);

        if (null == dept) {
            throw new RuntimeException("该ID：" + deptno + "没有没有对应的信息");
        }

        return dept;
    }

    public Dept processHystrixGet(@PathVariable("deptno") Long deptno) {
        return new Dept(deptno,"该ID：" + deptno + "没有没有对应的信息,null--@HystrixCommand","no this database in MySQL");
    }

}
