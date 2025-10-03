package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteByID(Integer id);

    void add(Dept dept);

    Dept getInfoByID(Integer deptId);

    void update(Dept dept);
}
