package com.kingfisher.utf.junit.util;

import com.kingfisher.utf.constant.LoggerConstant;
import com.kingfisher.utf.junit.model.Failure;
import com.kingfisher.utf.junit.model.TestCase;
import com.kingfisher.utf.junit.model.TestSuite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataProvider {

	public static void addSuccessfulTestCase(TestSuite testSuite, String testCaseName, Double time) {
		log.debug(LoggerConstant.DEBUG_LOG_ENTERED, "addSuccessfulTestCase()");
		TestCase testCase = new TestCase(testCaseName, time, null);
		testSuite.getTestCase().add(testCase);
		testSuite.setTime(testSuite.getTime() + time);
		testSuite.setTests(testSuite.getTests() + 1);
		log.debug(LoggerConstant.DEBUG_LOG_LEAVING, "addSuccessfulTestCase()");
	}

	public static void addFailedTestCase(TestSuite testSuite, String testCaseName, Double time, Failure failure) {
		log.debug(LoggerConstant.DEBUG_LOG_ENTERED, "addFailedTestCase()");
		TestCase testCase = new TestCase(testCaseName, time, failure);
		testSuite.getTestCase().add(testCase);
		testSuite.setTests(testSuite.getTests() + 1);
		testSuite.setTime(testSuite.getTime() + time);
		testSuite.setFailures(testSuite.getFailures() + 1);
		log.debug(LoggerConstant.DEBUG_LOG_LEAVING, "addFailedTestCase()");
	}
}
