package com.thon.task;

import java.util.Map;

/**
 * @author 57
 * @date 2017-03-27 上午09:40:30
 */
public interface Task {

	void doSyncTask(Map<Object, Object> context);
	
	void doAsyncTask(Map<Object, Object> context);
	
}
