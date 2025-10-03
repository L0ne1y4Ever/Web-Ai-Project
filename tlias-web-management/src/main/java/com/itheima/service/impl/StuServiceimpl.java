package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.StuMapper;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.StuQueryParam;
import com.itheima.pojo.Student;
import com.itheima.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StuServiceimpl implements StuService {
    @Autowired
    private StuMapper stuMapper;


    //条件分页查询
    @Override
    public PageResult<Student> page(StuQueryParam stuQueryParam) {
        //设置分页参数
        PageHelper.startPage(stuQueryParam.getPage(), stuQueryParam.getPageSize());

        //执行查询
        List<Student> studentList = stuMapper.list(stuQueryParam);

        //解析封装
        Page<Student> p = (Page<Student>) studentList;
        return new PageResult<>(p.getTotal(), p.getResult());

    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        stuMapper.deleteByIds(ids);
    }

    @Override
    public void save(Student student) {
        //补全属性
        student.setViolationCount((short) 0);
        student.setViolationScore((short) 0);
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.save(student);
    }

    //根据ID查询学员信息
    @Override
    public Student getById(Integer id) {
        return stuMapper.getById(id);
    }

    //修改学员信息
    @Override
    public void update(Student student) {
        //补全属性
        student.setUpdateTime(LocalDateTime.now());
        //执行修改
        stuMapper.updateById(student);
    }

    //违纪处理
    @Override
    public void violation(Integer id, Integer score) {
        stuMapper.updateViolation(id,score);
    }


}
