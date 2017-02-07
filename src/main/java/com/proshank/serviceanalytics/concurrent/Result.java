/**
 * Author: proshank
 * Date: Oct 19, 2016
 * File Name: Result.java
 */
package com.proshank.serviceanalytics.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * pojo to return result of ConcurrentProccessHandler 
 * @see com.proshank.serviceanalytics.concurrent.ConcurrentProccessHandler
 * @author proshank
 */
public class Result<T> {
	private List<T> results;
	private List<Exception> exceptions;

	public List<T> getResults() {
		return results;
	}

	public boolean isExceptionOccurred() {
		return exceptions != null && exceptions.size() > 0 ? true : false;
	}

	public List<Exception> getExceptions() {
		return exceptions;
	}

	void addResponse(T response) {
		if (this.results == null) {
			results = new ArrayList<T>();
		}
		this.results.add(response);
	}

	void addExceptions(Exception e) {
		if (this.exceptions == null) {
			exceptions = new ArrayList<Exception>();
		}
		this.exceptions.add(e);
	}

}
