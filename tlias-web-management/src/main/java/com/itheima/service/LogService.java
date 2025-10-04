package com.itheima.service;

import com.itheima.pojo.LogQueryParam;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface LogService {
    //查询日志列表
    PageResult<OperateLog> list(LogQueryParam param);
}
