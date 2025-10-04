package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")//拦截所有请求
@Slf4j
public class DemoFilter implements Filter {


    //初始化，仅执行一次，web服务器启动时执行
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("初始化...");
    }
    //拦截请求之后，执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截请求...");
        //获取请求路径

        //放行
        filterChain.doFilter(servletRequest,servletResponse);

    }
    //销毁，仅执行一次，web服务器关闭时执行
    @Override
    public void destroy() {
        log.info("销毁...");
    }
}
