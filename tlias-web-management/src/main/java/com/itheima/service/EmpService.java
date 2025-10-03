package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.PageResult;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {

    /*
    * 分页查询
    * page:页码
    * pageSize:每页记录数
    * */
    PageResult<Emp> page(EmpQueryParam empQueryParam);
    //保存员工信息
    void save(Emp emp);

    //批量删除员工
    void delete(List<Integer> ids);

//    根据ID查询员工信息
    Emp getInfo(Integer id);
//修改员工
    void update(Emp emp);
    //查询所有员工
    List<Emp> list();

    //登录
    LoginInfo login(Emp emp);
}
