package com.future.dao;

import com.future.entity.Dept;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Service与@Controller属于Spring框架的注解，注解在类上，需要被扫描才能创建实例化对象，并将对象放到Spring容器中
 * @Mapper注解是Mybatis框架的注解，使用这个注解我们的接口就可以被Mybatis框架加载，然后动态代理生成实体类，然后就将实体类放到了spring容器中
 */
@Mapper
public interface DeptDao {

    @Select("select *from dept")
    public List<Dept> selectDept();

    @Select("select *from dept where deptno=#{deptno}")
    public Dept selectByNo(Long deptno);

    @Insert("insert into dept values(0,#{dname},Database())")
    public Integer insert(Dept dept);

}
