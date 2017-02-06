/**
 * Author: proshank
 * Date: Oct 18, 2016
 * File Name: ExecutorService.java
 */
package com.proshank.serviceanalytics.concurrent;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is wrapper around java.util.concurrent.ExecutorService, use it to
 * spawn threads for any Callable implementation and get response collectively
 * in Result object
 * 
 * 
 * @author proshank
 * @version 1.0
 * 
 */
public class ConcurrentProccessHandler {

	private final static Logger LOGGER = LoggerFactory.getLogger(ConcurrentProccessHandler.class);

	/**
	 * This method invoke tasks in concurrent environment using
	 * java.util.concurrent API, and returns the Result object representing
	 * result of tasks submitted. <br>
	 * User needs to implement java.util.concurrent.Callable interface and pass
	 * collection of implemented work to process in concurrent environment, <br>
	 * Parameters pollTimeout and pollTimeoutUnit are used to determine polling
	 * cycle to check if all threads are executed.
	 * 
	 * @param tasks
	 *            The collection of tasks, task is instance of
	 *            java.util.concurrent.Callable implementation which needs to be
	 *            executed in parallel
	 * @param threadPoolCount
	 *            constant for declaring pool with fix no of threads
	 * @param pollTimeout
	 *            maximum time to wait before polling if thread pool is
	 *            terminated
	 * @param pollTimeoutUnit
	 *            time unit of the timeout argument
	 * @return Result object representing results of tasks submitted.<br>
	 *         List of results in Result object is in the same sequential order
	 *         as produced by the iterator for the given task list, each of
	 *         which has completed. Same is for exception list if any.
	 * @throws InterruptedException
	 *             If interrupted while waiting, in which case unfinished tasks
	 *             are cancelled.
	 */
	public <T> Result<T> invokeThreads(Collection<? extends Callable<T>> tasks, int threadPoolCount, long pollTimeout,
			TimeUnit pollTimeoutUnit) throws InterruptedException {
		LOGGER.info("Start of executorService.invokeThreads()");
		Result<T> result = null;
		if (tasks != null) {
			LOGGER.info("Creating tasks in parallel");
			ExecutorService pool = Executors.newFixedThreadPool(threadPoolCount);

			LOGGER.info("Paralel execution started. Thread pool size is: {}, No of total threads are: {}",
					threadPoolCount, tasks.size());
			List<Future<T>> futures = pool.invokeAll(tasks);

			LOGGER.info("Shutting down pool and waiting for all threads to finish their work.");
			pool.shutdown();

			while (!pool.isTerminated()) {
				pool.awaitTermination(pollTimeout, pollTimeoutUnit);
			}
			LOGGER.info("Paralel execution finished.");

			result = new Result<T>();
			for (Future<T> future : futures) {
				try {
					result.addResponse(future.get());
				} catch (ExecutionException e) {
					LOGGER.warn("Exception occured while invoking one of the thred, exception is: {}", e);
					result.addExceptions(e);
				}
			}
		}
		return result;
	}
}
