package com.proshank.serviceanalytics;

import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class around DescriptiveStatistics to get/print statistics of values
 * 
 * @author proshank
 * @version 1.0
 */
public class Statistics {
	private final static Logger LOGGER = LoggerFactory.getLogger(Statistics.class);

	/**
	 * Prints the statistics of input values in console.
	 * 
	 * @param values
	 *            List of values
	 */
	public static void printResult(List<Long> values) {

		/*
		 * Used apache DescriptiveStatistics for getting statistics from
		 * response data. DescriptiveStatistics dosen't take collection as input
		 * so need to iterate through all values and add them one by one.
		 */
		DescriptiveStatistics stats = new DescriptiveStatistics();
		for (long responseTime : values) {
			stats.addValue(responseTime);
		}

		LOGGER.info("10%: {}", getPercentiles(10, stats));
		LOGGER.info("50%: {}", getPercentiles(50, stats));
		LOGGER.info("90%: {}", getPercentiles(90, stats));
		LOGGER.info("95%: {}", getPercentiles(95, stats));
		LOGGER.info("99%: {}", getPercentiles(99, stats));
		LOGGER.info("Mean Value: {}", getMean(stats));
		LOGGER.info("Standard daviation: {}", getStandardDeviation(stats));
	}

	public static double getPercentiles(double percentile, DescriptiveStatistics stats) {
		return stats.getPercentile(percentile);
	}

	public static double getMean(DescriptiveStatistics stats) {
		return stats.getMean();
	}

	public static double getStandardDeviation(DescriptiveStatistics stats) {
		return stats.getStandardDeviation();
	}
}
