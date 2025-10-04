package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1.获取到请求路径
        String requestURI = request.getRequestURI();

        //2.判断请求路径是否是/login,是则放行
        if(requestURI.equals("/login")){
            log.info("登录操作,放行");
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，不存在则返回401
        if(token == null || token.isEmpty()){
            log.info("请求头token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
            return;
        }

        //5.如果token存在，校验 token，验证通过则放行，验证不通过则返回401
        try {
            JwtUtils.parseToken(token);
            log.info("令牌校验通过");
        } catch (Exception e){
            log.info("令牌校验不通过");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
            return;
        }

        log.info("令牌合法,放行");
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
