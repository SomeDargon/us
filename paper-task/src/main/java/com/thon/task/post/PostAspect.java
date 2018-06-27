/**
 * 
 */
package com.thon.task.post;

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
public class PostAspect {
	
	@Autowired
	private Task postViewCountTask;



}
