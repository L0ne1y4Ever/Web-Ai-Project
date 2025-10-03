package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {
    private Integer id;//ID,主键
    private String username;//用户名
    private String name;// 姓名
    private String token;//令牌

}
