package com.itheima.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmpQueryParam {
    private Integer page=1;//页码
    private Integer pageSize=10;//展示条数
    private String name;//员工姓名
    private Integer gender;//性别
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;//入职时间-开始
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end;//入职时间-结束

}
