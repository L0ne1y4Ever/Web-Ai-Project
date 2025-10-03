package com.itheima.mapper;

import com.itheima.pojo.StuQueryParam;
import com.itheima.pojo.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface StuMapper {

    //条件分页查询
    List<Student> list(StuQueryParam stuQueryParam);

    //删除学员
    void deleteByIds(List<Integer> ids);

    //增加学员
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time)" +
            " values (#{name}, #{no}, #{gender}, #{phone}, #{idCard}, #{isCollege}, #{address}, #{degree}, #{graduationDate}, #{clazzId}, #{violationCount}, #{violationScore}, #{createTime}, #{updateTime})")
    void save(Student student);


    //根据ID查询学员信息
    @Select("select id, name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time from student where id = #{id}")
    Student getById(Integer id);

    //修改学员
    void updateById(Student student);

    //违纪处理
    @Update("update student set violation_count = violation_count + 1, violation_score = violation_score + #{score} where id = #{id}")
    void updateViolation(Integer id, Integer score);

    //统计学员学历数据
    List<Map<String, Object>> countStudentDegreeData();

    //统计班级人数
    List<Map<String, Object>> countStudentCountData();



}
