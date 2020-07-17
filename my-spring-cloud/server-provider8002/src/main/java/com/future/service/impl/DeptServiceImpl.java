package com.future.service.impl;

import com.future.dao.DeptDao;
import com.future.entity.Dept;
import com.future.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    DeptDao deptDao;

    public List<Dept> selectDept() {
        return deptDao.selectDept();
    }

    public Dept selectByNo(Long deptno) {
        return deptDao.selectByNo(deptno);
    }

    public Integer insert(Dept dept) {
        return deptDao.insert(dept);
    }
}
