package com.itheima.interceptor;

import com.itheima.pojo.UserContext;
import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //1.获取到请求路径
//        String requestURI = request.getRequestURI();
//
//        //2.判断请求路径是否是/login,是则放行
//        if(requestURI.equals("/login")){
//            log.info("登录操作,放行");
//            return true;
//        }

        //3.获取请求头中的token
        String token = request.getHeader("token");

        //4.判断token是否存在，不存在则返回401
        if(token == null || token.isEmpty()){
            log.info("请求头token为空");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
            return false;
        }

        //5.如果token存在，校验 token，验证通过则放行，验证不通过则返回401
        try {
            Claims claims = JwtUtils.parseToken(token);
            //获取当前登录员工ID
            Integer empId = Integer.valueOf(claims.get("id").toString());
            //获取当前登录员工姓名
            String empName = claims.get("username").toString();
            //存入ThreadLocal
            CurrentHolder.setCurrent(new UserContext(empId,empName));
            log.info("当前登录员工ID：" + empId+ "，当前登录员工姓名：" + empName);
            log.info("令牌校验通过");
        } catch (Exception e){
            log.info("令牌校验不通过");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
            return false;
        }

        //6.令牌合法，放行
        log.info("令牌合法,放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //删除ThreadLocal中的数据
        CurrentHolder.remove();
    }
}
