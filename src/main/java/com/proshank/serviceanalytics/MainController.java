package com.proshank.serviceanalytics;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.proshank.serviceanalytics.concurrent.ConcurrentProccessHandler;
import com.proshank.serviceanalytics.concurrent.Result;

/**
 * Class with main method.<br>
 * We can update URL, TOTAL_NO_OF_REQUESTS, CONCURRENT_THREAD_POOL_COUNT,
 * POLL_TIMEOUT, & POLL_TIMEOUT_UNIT to get statistics of response time with the
 * respective input.
 * 
 * @author proshank
 *
 */
public class MainController {
	private final static Logger LOGGER = LoggerFactory.getLogger(MainController.class);

	private static final String URL = "http://en.wikipedia.org/wiki/Main_Page";
	private static final int TOTAL_NO_OF_REQUESTS = 100;
	private static final int CONCURRENT_THREAD_POOL_COUNT = 10;
	private static final int POLL_TIMEOUT = 2;
	private static final TimeUnit POLL_TIMEOUT_UNIT = TimeUnit.SECONDS;

	public static void main(String[] args) {
		LOGGER.info("Start of program to get statistics of service");
		makeCalls(URL, TOTAL_NO_OF_REQUESTS, CONCURRENT_THREAD_POOL_COUNT, POLL_TIMEOUT, POLL_TIMEOUT_UNIT);
	}

	/**
	 * Creates n no of thread requests to service URL, where n = noOfTimes.
	 * 
	 * @param url
	 *            URL to be called
	 * @param noOfTimes
	 *            no of requests to be made
	 * @param threadPoolCount
	 *            constant for declaring pool with fix no of threads
	 * @param pollTimeout
	 *            maximum time to wait before polling if thread pool is
	 *            terminated
	 * @param pollTimeoutUnit
	 *           time unit of the timeout argument
	 */
	public static void makeCalls(String url, int noOfTimes, int threadPoolCount, int pollTimeout,
			TimeUnit pollTimeoutUnit) {
		List<Request> requests = new ArrayList<>(noOfTimes);
		for (int i = 0; i < noOfTimes; i++) {
			requests.add(new Request(i, url));
		}

		try {
			LOGGER.info("Calling url:{} in mulithreaded environment to get response time.", url);
			Result<Long> result = new ConcurrentProccessHandler().invokeThreads(requests, threadPoolCount, pollTimeout,
					pollTimeoutUnit);
			Statistics.printResult(result.getResults());
		} catch (InterruptedException e) {
			LOGGER.error("Threads to call get service interrupted, exception:{}", e);
		}
	}
}
