package com.proshank.serviceanalytics;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class MainControllerTest {

	@Test
	public void test1_make_100_calls_with_10_concurrent_threads() {
		MainController.makeCalls("http://en.wikipedia.org/wiki/Main_Page", 100, 10, 2, TimeUnit.SECONDS);
	}

	@Test
	public void test2_make_100_calls_with_50_concurrent_threads() {
		MainController.makeCalls("http://en.wikipedia.org/wiki/Main_Page", 100, 50, 2, TimeUnit.SECONDS);
	}

	@Test
	public void test3_make_100_calls_with_100_concurrent_threads() {
		MainController.makeCalls("http://en.wikipedia.org/wiki/Main_Page", 100, 100, 2, TimeUnit.SECONDS);
	}

	@Test
	public void test4_make_200_calls_with_10_concurrent_threads() {
		MainController.makeCalls("http://en.wikipedia.org/wiki/Main_Page", 200, 10, 2, TimeUnit.SECONDS);
	}

	@Test
	public void test5_make_200_calls_with_50_concurrent_threads() {
		MainController.makeCalls("http://en.wikipedia.org/wiki/Main_Page", 200, 50, 2, TimeUnit.SECONDS);
	}

	@Test
	public void test6_make_200_calls_with_100_concurrent_threads() {
		MainController.makeCalls("http://en.wikipedia.org/wiki/Main_Page", 200, 100, 2, TimeUnit.SECONDS);
	}

}
