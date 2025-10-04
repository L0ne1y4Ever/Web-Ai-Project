package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.LogQueryParam;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceimpl implements LogService {

    @Autowired
    private OperateLogMapper operateLogMapper;
    @Override
    public PageResult<OperateLog> list(LogQueryParam param) {
        //用PaperHelper 进行分页
        PageHelper.startPage(param.getPage(), param.getPageSize());
        //查询所有数据
        List<OperateLog> logList = operateLogMapper.list(param);
        //使用PageInfo获取分页信息
        PageInfo<OperateLog> pageInfo = new PageInfo<>(logList);
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());

    }
}
