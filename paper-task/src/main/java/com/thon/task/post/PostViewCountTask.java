package com.thon.task.post;

import com.thon.task.AbstractTask;
import com.thon.task.annotation.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * @author 57
 * @date 2017-03-27 上午09:40:30
 */
@Task
public class PostViewCountTask extends AbstractTask {
	private static final Log log = LogFactory.getLog(PostViewCountTask.class);
	@Override
	protected void doTask(Map<Object, Object> context) throws Exception {

	}
}
