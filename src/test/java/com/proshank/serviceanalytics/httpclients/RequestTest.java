package com.proshank.serviceanalytics.httpclients;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;

import com.proshank.serviceanalytics.httpclients.Request;

public class RequestTest {
	private static final Logger LOGGER = Logger.getLogger(RequestTest.class.getName());
	
	@Test
	public void testCall() throws Exception {
		Request request = new Request(0,"http://en.wikipedia.org/wiki/Main_Page");
		LOGGER.info("Response Time:"+request.call());
		Assert.assertTrue(true);
	}
	
	@Test(expected=Exception.class)
	public void testCallFail() throws Exception {
		Request request = new Request(0,"void url");
		LOGGER.info("Response Time:"+request.call());
		Assert.assertTrue(true);
	}
}
