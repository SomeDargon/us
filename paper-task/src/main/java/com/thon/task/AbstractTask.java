package com.thon.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Map;

/**
 * @author 57
 * @date 2017-03-27 上午09:40:30
 */
public abstract class AbstractTask implements Task {

	@Autowired
	protected PlatformTransactionManager transactionManager;

	public AbstractTask() {
	}

	@Async
	public void doAsyncTask(final Map<Object, Object> context) {
		new TransactionTemplate(transactionManager)
		.execute(new TransactionCallbackWithoutResult(){
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
					try {
						doTask(context);
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
	}

	public void doSyncTask(final Map<Object, Object> context) {
		try {
			doTask(context);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected abstract void doTask(final Map<Object, Object> context) throws Exception;

}
