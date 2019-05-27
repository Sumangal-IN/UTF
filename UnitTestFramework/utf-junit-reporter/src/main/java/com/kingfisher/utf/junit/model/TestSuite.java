package com.kingfisher.utf.junit.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = { "name", "tests", "failures", "time", "testCase" })
public class TestSuite {

	private String name;
	private int tests;
	private int failures;
	private Double time;
	private List<TestCase> testCase;

	public TestSuite() {
		super();
	}

	public TestSuite(String name, int tests, int failures, Double time, List<TestCase> testCase) {
		super();
		this.name = name;
		this.tests = tests;
		this.failures = failures;
		this.time = time;
		this.testCase = testCase;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "tests")
	public int getTests() {
		return tests;
	}

	public void setTests(int tests) {
		this.tests = tests;
	}

	@XmlAttribute(name = "failures")
	public int getFailures() {
		return failures;
	}

	public void setFailures(int failures) {
		this.failures = failures;
	}

	@XmlAttribute(name = "time")
	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	@XmlElement(name = "testcase")
	public List<TestCase> getTestCase() {
		return testCase;
	}

	public void setTestCase(List<TestCase> testCase) {
		this.testCase = testCase;
	}

	@Override
	public String toString() {
		return "TestSuite [name=" + name + ", tests=" + tests + ", failures=" + failures + ", time=" + time
				+ ", testCase=" + testCase + "]";
	}

}
