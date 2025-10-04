package com.itheima.mapper;

import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.LogQueryParam;
import com.itheima.pojo.OperateLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OperateLogMapper {
    //插入操作日志
    @Insert("insert into operate_log(operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time,operate_emp_name) values " +
            "(#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime},#{operateEmpName})")
    void insert(OperateLog operateLog);

    //查询操作日志
    @Select("select id, operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time,operate_emp_name from operate_log")
    List<OperateLog> list(LogQueryParam param);
}
