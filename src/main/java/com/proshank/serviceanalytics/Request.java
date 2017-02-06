/**
 * @author proshank
 */
package com.proshank.serviceanalytics;

import java.util.concurrent.Callable;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class implements Callable<Long> interface and instance of this class can
 * be used to create threads by ExecutorService.<br>
 * call method is invoking HTTP.GET call and returning time taken by service to
 * respond in milliseconds.
 * 
 * @author proshank
 * @version 1.0
 * 
 **/
public class Request implements Callable<Long> {
	private String url;
	private Header[] headers;
	private int requestSerialNo;
	private final static Logger LOGGER = LoggerFactory.getLogger(Request.class);

	/**
	 * Constructor to create instance of this class with requestSerialNo, url as
	 * input
	 * 
	 * @param requestSerialNo
	 * @param url
	 */
	public Request(int requestSerialNo, String url) {
		this.url = url;
		this.requestSerialNo = requestSerialNo;
	}

	/**
	 * Constructor to create instance of this class with requestSerialNo, url
	 * and headers as input
	 * 
	 * @param requestSerialNo
	 * @param url
	 * @param headers
	 */
	public Request(int requestSerialNo, String url, Header[] headers) {
		this.url = url;
		this.headers = headers;
		this.requestSerialNo = requestSerialNo;
	}

	/**
	 * This method should never be called directly from code, its
	 * ExecutorService's responsibility to call this method using available
	 * thread in pool.
	 * 
	 * @return response time of service call
	 * @see com.proshank.serviceanalytics.concurrent.ConcurrentProccessHandler
	 */
	public Long call() throws Exception {
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		if (headers != null) {
			request.setHeaders(headers);
		}
		long startTime = System.currentTimeMillis();
		client.execute(request);
		long responseTime = System.currentTimeMillis() - startTime;
		LOGGER.debug("Request no: {}, returned response in:{} miliseconds", this.requestSerialNo, responseTime);
		return responseTime;
	}

}
