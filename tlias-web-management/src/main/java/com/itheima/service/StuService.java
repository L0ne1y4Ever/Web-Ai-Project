package com.itheima.service;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.StuQueryParam;
import com.itheima.pojo.Student;

import java.util.List;

public interface StuService {
    //条件分页查询
    PageResult<Student> page(StuQueryParam stuQueryParam);

    //删除学员
    void deleteByIds(List<Integer> ids);

    //增加学员
    void save(Student student);

    //根据ID查询学员信息
    Student getById(Integer id);


    //修改学员
    void update(Student student);

    //学员扣分违纪处理
    void violation(Integer id, Integer score);
}
