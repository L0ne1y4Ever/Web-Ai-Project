package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

//    private static final Logger log= LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

//    查询部门列表
    @GetMapping
    public Result list(){
        log.info("查询所有的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
//删除部门
    @DeleteMapping
   /* public Result delete(HttpServletRequest request){
        String idStr = request.getParameter("id");
        int id = Integer.parseInt(idStr);
        System.out.println("根据ID删除指定"+id+"部门");
        return Result.success();
    }
    */

    /*
    public Result delete(@RequestParam(value = "id",required = false) Integer deptId ){
        System.out.println("根据ID删除指定"+deptId+"部门");
        return Result.success();
    }
     */
    public Result delete(Integer id){
        log.info("根据ID删除部门： {}",id);
        deptService.deleteByID(id);
        return Result.success();
    }


//    新增部门
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("新增部门: {}",dept);
        deptService.add(dept);
        return Result.success();
    }

//    修改部门
    @GetMapping("/{id}")
//    public Result getInfo(@PathVariable Integer id)
    public Result getInfo(@PathVariable("id") Integer deptId){
        log.info("根据ID查询部门：{}",deptId);
        Dept dept=deptService.getInfoByID(deptId);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public Result update(@RequestBody Dept dept){
        log.info("修改部门: {}",dept);
        deptService.update(dept);
        return Result.success();
    }
}
