package com.proshank.serviceanalytics.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.proshank.serviceanalytics.Request;

public class ConcurrentProccessHandlerTest {
	private final static Logger LOGGER = LoggerFactory.getLogger(ConcurrentProccessHandlerTest.class);
	private static final int CONCURRENT_THREAD_POOL_COUNT = 10;
	private static final int POLL_TIMEOUT = 2;
	private static final TimeUnit POLL_TIMEOUT_UNIT = TimeUnit.SECONDS;
	
	@Test
	public void test1_testInvokeThreads() throws InterruptedException {
		String url = "http://en.wikipedia.org/wiki/Main_Page";
		List<Request> getCalls = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			getCalls.add(new Request(i, url));
		}
		Result<Long> result = new ConcurrentProccessHandler().invokeThreads(getCalls, CONCURRENT_THREAD_POOL_COUNT, POLL_TIMEOUT, POLL_TIMEOUT_UNIT);
		int resultsCount = (result.getExceptions() != null ? result.getExceptions().size() : 0)
				+ (result.getResults() != null ? result.getResults().size() : 0);
		LOGGER.info("Total results returned by ConcurrentProccessHandler:" + resultsCount);
		Assert.assertTrue(resultsCount == 100);
	}

	@Test
	public void test2_testExceptionCounts() throws InterruptedException {
		String url = "VOID URL";
		List<Request> getCalls = new ArrayList<>(100);
		for (int i = 0; i < 100; i++) {
			getCalls.add(new Request(i, url));
		}
		Result<Long> result = new ConcurrentProccessHandler().invokeThreads(getCalls, CONCURRENT_THREAD_POOL_COUNT, POLL_TIMEOUT, POLL_TIMEOUT_UNIT);
		int resultsCount = (result.getExceptions() != null ? result.getExceptions().size() : 0);
		LOGGER.info("Total excepotions returned by ConcurrentProccessHandler:" + resultsCount);
		Assert.assertTrue(resultsCount == 100);
	}
}
