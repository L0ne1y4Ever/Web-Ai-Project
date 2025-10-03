package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StuMapper;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceimpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StuMapper stuMapper;

    //统计员工职位人数
    @Override
    public JobOption getEmpJobData() {
        //调用接口获取统计数据
        List<Map<String, Object>> maps = empMapper.countEmpJobData();//map:pos=字段名，num=职位代数
        //组装结果返回
        List<Object> jobList = maps.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = maps.stream().map(dataMap -> dataMap.get("num")).toList();

        return new JobOption(jobList,dataList);
    }

    //统计员工性别人数
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    //统计员工学历人数
    @Override
    public List<Map<String, Object>> getStudentDegreeData() {
        return stuMapper.countStudentDegreeData();
    }

    //统计班级人数
    @Override
    public Map<String, Object> getStudentCountData() {
        List<Map<String, Object>> rawData = stuMapper.countStudentCountData();
        List<String> clazzList = new ArrayList<>();
        List<Number> dataList = new ArrayList<>();
        for (Map<String, Object> item : rawData) {
            clazzList.add((String) item.get("name"));
            dataList.add((Number) item.get("value"));
        }
        Map<String, Object> result = new HashMap<>();
        result.put("clazzList", clazzList);
        result.put("dataList", dataList);
        return result;
    }
}
