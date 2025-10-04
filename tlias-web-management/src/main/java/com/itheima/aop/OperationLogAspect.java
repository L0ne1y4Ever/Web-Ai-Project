package com.itheima.aop;

import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLog;
import com.itheima.utils.CurrentHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    //对Controller层中的save,update,delete,add方法进行切入
    @Around("execution(* com.itheima.controller.*.save(..)) ||" +
            "execution(* com.itheima.controller.*.delete(..)) ||" +
            "execution(* com.itheima.controller.*.update(..)) ||" +
            "execution(* com.itheima.controller.*.add(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录开始时间
        long start = System.currentTimeMillis();

        //执行目标方法
        Object result = joinPoint.proceed();

        //记录结束时间
        long end = System.currentTimeMillis();
        long costTime = end - start;

        //构建日志实体
        OperateLog operateLog = new OperateLog();
        operateLog.setOperateEmpId(getCurrentEmpId());//登录用户ID
        operateLog.setOperateTime(LocalDateTime.now());//操作时间
        operateLog.setClassName(joinPoint.getTarget().getClass().getName());//类名
        operateLog.setMethodName(joinPoint.getSignature().getName());//方法名
        operateLog.setMethodParams(Arrays.toString(joinPoint.getArgs()));//方法参数
        operateLog.setReturnValue(result.toString());//返回值
        operateLog.setCostTime(costTime);//耗时
        operateLog.setOperateEmpName(getCurrentEmpName());//操作人

        //插入操作日志
        operateLogMapper.insert(operateLog);
        return result;

    }
    private Integer getCurrentEmpId(){
        return CurrentHolder.getCurrent().getId();
    }
    private String getCurrentEmpName(){
        return CurrentHolder.getCurrent().getUsername();
    }
}
