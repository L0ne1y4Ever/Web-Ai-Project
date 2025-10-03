package com.itheima.service;

import com.itheima.pojo.LogList;
import com.itheima.pojo.LogQueryParam;
import com.itheima.pojo.PageResult;

public interface LogService {
    PageResult<LogList> page(LogQueryParam logQueryParam);
}
