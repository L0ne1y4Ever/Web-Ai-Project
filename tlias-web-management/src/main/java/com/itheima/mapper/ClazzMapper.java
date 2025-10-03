package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ClazzMapper {

    //条件分页查询
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    //删除班级
    @Delete("delete from clazz where id = #{id}")
    void deleteById(Integer id);

    //新增班级
    @Select("insert into clazz(name,room,begin_date,end_date,master_id,subject,create_time,update_time) " +
    "values (#{name}, #{room}, #{beginDate}, #{endDate}, #{masterId}, #{subject}, #{createTime}, #{updateTime})")
    void insert(Clazz clazz);
    //根据ID查询班级信息
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    Clazz getById(Integer id);
    //修改班级

    void updateById(Clazz clazz);

    //查询所有班级
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz order by update_time desc ")
    List<Clazz> listAll();
}
