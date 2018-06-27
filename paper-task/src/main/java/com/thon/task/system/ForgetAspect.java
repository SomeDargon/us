package com.thon.task.system;

import com.thon.task.Task;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 57
 * @date 2017-03-27 上午09:40:30
 */
@Aspect
@Component
public class ForgetAspect {

    @Autowired
    private Task forgetEmailTask;


    /**
     * 忘记密码
     * @param jp
     */
    @AfterReturning("execution(*  com.thon.service.system.SystemService.forgetPassword(..))")
    public void doForgetPassword(JoinPoint jp) throws Exception {
        Map<Object, Object> context =new HashMap<Object, Object>();
        context.put("user", jp.getArgs()[0]);

        forgetEmailTask.doAsyncTask(context);
    }
}
