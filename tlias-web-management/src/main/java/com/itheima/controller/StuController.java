package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.StuQueryParam;
import com.itheima.pojo.Student;
import com.itheima.service.StuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StuController {

    @Autowired
    private StuService stuService;

    //条件分页查询
    @GetMapping
    public Result page(StuQueryParam stuQueryParam){
        log.info("条件分页查询：{}",stuQueryParam);
        PageResult<Student> pageResult=  stuService.page(stuQueryParam);
        return Result.success(pageResult);
    }

    //删除学员
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable String ids){
        log.info("批量删除学员: {}", ids);
        String[] idArray = ids.split(",");
        List<Integer> idList = Arrays.stream(idArray).map(Integer::parseInt).toList();
        stuService.deleteByIds(idList);
        return Result.success();
    }

    //添加学员
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学员: {}", student);
        stuService.save(student);
        return Result.success();
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询: {}", id);
        Student student = stuService.getById(id);
        return Result.success(student);
    }

    //修改学员信息
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员: {}", student);
        stuService.update(student);
        return Result.success();
    }

    //违纪处理
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("学员违纪处理: id={}, score={}", id, score);
        stuService.violation(id, score);
        return Result.success();
    }
}
