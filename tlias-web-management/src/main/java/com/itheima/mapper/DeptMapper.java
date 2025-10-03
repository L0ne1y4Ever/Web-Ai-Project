package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {

//    @Results({
//            @Result(column = "create_time" ,property = "createTime"),
//            @Result(column = "update_time" ,property = "updateTime")
//    })
//    查询所有部门
    @Select("select id, name, create_time, update_time from dept order by update_time desc")
    List<Dept> findAll();

//    删除指定部门
    @Delete("delete from dept where id = #{id}")
    void deleteByID(Integer id);

//    添加部门
    @Insert("insert into dept (name, create_time, update_time) VALUES (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

//    查询指定部门
    @Select("select id, name, create_time, update_time from dept where id =#{deptId}")
    Dept getInfoByID(Integer deptId);

//    修改部门数据
    @Update("update dept set name = #{name} ,update_time =#{updateTime} where id =#{id}")
    void update(Dept dept);
}
