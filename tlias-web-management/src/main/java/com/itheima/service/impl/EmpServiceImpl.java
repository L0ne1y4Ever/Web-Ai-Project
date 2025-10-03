package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpExprMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.EmpLogService;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //设置分页参数
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
        //执行查询
        List<Emp> empList = empMapper.list(empQueryParam);
        //解析封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class}) //事务控制
    @Override
    public void save(Emp emp) {
        try {
            //保存员工基本信息
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);

            //保存工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                //遍历集合为empID赋值
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }


//    原始分页查询
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        //调用Mapper接口，查询总记录数
        Long total = empMapper.count();
        //查询结果列表
        Integer start=(page-1)*pageSize;
        List<Emp> rows = empMapper.list(start, pageSize);
        //封装结果
        return new PageResult<Emp>(total,rows);
    }*/
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delete(List<Integer> ids) {
        //批量删除员工基本信息
        empMapper.deleteByIds(ids);
        //批量删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);

    }

//    根据ID查询员工信息
    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Emp emp) {
        //根据ID修改员工基本信息
        emp.setUpdateTime(LocalDateTime.now());//更新属性
        empMapper.updateById(emp);
        //根据ID修改员工工作经历信息（先删除再添加）
        //先根据ID删除原有工作经历
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //再添加
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr->empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
        }

    }

    //查询所有员工信息
    @Override
    public List<Emp> list() {
        return empMapper.list(null);
    }

    //登录
    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper,根据用户名查询员工信息
           Emp e= empMapper.selectByUsernameAndPassword(emp);
        //2.判断员工是否存在,不存在则返回null
            if(e!=null){
                log.info("登陆成功,员工信息：{}",e);
                return new LoginInfo(e.getId(),e.getUsername(),e.getName(),"");
            }
            return null;
    }
}
