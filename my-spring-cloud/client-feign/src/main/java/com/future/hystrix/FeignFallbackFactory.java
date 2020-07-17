package com.future.hystrix;

import com.future.FeignService;
import com.future.entity.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

//注入：@Component是一个通用的Spring容器管理的单例bean组件（当类被@Component所注解，那意味着同样可以用@Repository,
// @Service, @Controller来替代它）
@Component
public class FeignFallbackFactory implements FallbackFactory<FeignService> {

    @Override
    public FeignService create(Throwable throwable){
        return new FeignService() {
            @Override
            public List<Dept> getDept() {
                return null;
            }
            @Override
            public Dept getByNo(Long deptno) {
                return new Dept(deptno,"该ID：" + deptno + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭","no this database in MySQL");
            }
            @Override
            public Integer insert(Dept dept) {
                return null;
            }
        };
    }


}
