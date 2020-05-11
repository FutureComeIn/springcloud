package com.atguigu.springcloud.entities;

import lombok.experimental.Accessors;

import java.io.Serializable;

// @NoArgsConstructor   有了该注解，下面可以不加无参构造方法
// @AllArgsConstructor  有了该注解，下面可以不加全参构造方法
// @Data  有了该注解，下面可以不加get set方法，其他类一样可以调用get set方法
// @Accessors(chain=true) 链式使用，加上后可以这样赋值 new Dept().setDeptno(1l).setDb_source('aa');
//以上注解都来源于pom文件加的org.projectlombok 依赖，叫小辣椒，作用：减轻代码负担
@Accessors(chain=true)
public class Dept implements Serializable{
    private Long 	deptno; // 主键
    private String 	dname; // 部门名称
    private String 	db_source;// 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库

    public Dept() {
        super();
    }

    public Dept(Long deptno, String dname, String db_source) {
        super();
        this.deptno = deptno;
        this.dname = dname;
        this.db_source = db_source;
    }


    public Long getDeptno() {
        return deptno;
    }
    public void setDeptno(Long deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }
    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getDb_source() {
        return db_source;
    }
    public void setDb_source(String db_source) {
        this.db_source = db_source;
    }


    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", db_source='" + db_source + '\'' +
                '}';
    }
}
