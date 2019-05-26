package com.kingfisher.utf.exception;

public class ApplicationConstant {

	// Restrict initialisation using private constructor. To be used in static way.
	private ApplicationConstant() {
		// Restrict initialisation using private constructor. To be used in static way.
	}

	public static final String JUNIT_XML_ATTRIBUTE_NAME = "name";
	public static final String JUNIT_XML_ATTRIBUTE_TESTS = "tests";
	public static final String JUNIT_XML_ATTRIBUTE_FAILURES = "failures";
	public static final String JUNIT_XML_ATTRIBUTE_TIME = "time";
	public static final String JUNIT_XML_ATTRIBUTE_MESSAGE = "message";
	public static final String JUNIT_XML_ATTRIBUTE_TEXT = "text";
	public static final String JUNIT_XML_ATTRIBUTE_TYPE = "type";
	public static final String JUNIT_XML_NODE_TESTSUITE = "testsuite";
	public static final String JUNIT_XML_NODE_TESTCASE = "testcase";
	public static final String JUNIT_XML_NODE_FAILURE = "failure";
}
