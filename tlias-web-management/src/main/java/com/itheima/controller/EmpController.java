package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;


@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    //条件分页查询
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("条件分页查询：{}",empQueryParam);
        PageResult<Emp> pageResult=  empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    //保存员工基本信息
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("新增员工：{}",emp );
        empService.save(emp);
        return Result.success();
    }

    //删除员工信息
    @DeleteMapping()
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工ID：{}", ids);
        empService.delete(ids);
        return Result.success();
    }
    //查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据ID查询员工信息:{}",id);
        Emp emp =empService.getInfo(id);
        return Result.success(emp);
    }

    //更新员工信息
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工：{}",emp);
        empService.update(emp);
        return Result.success();
    }

    //查询所有员工信息
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有员工信息");
        List<Emp> empList = empService.list();
        return Result.success(empList);
    }

}
