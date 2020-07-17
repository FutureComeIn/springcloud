package com.future.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)   //链式使用，加上后可以这样赋值 new Dept().setDeptno(1l).setDb_source('aa');
public class Dept implements Serializable {

    private Long deptno;
    private String dname;
    private String db_source;


}
