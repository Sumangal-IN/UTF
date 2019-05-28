package com.kingfisher.utf.junit;

import java.util.ArrayList;

import javax.xml.bind.JAXBException;

import com.kingfisher.utf.junit.model.Failure;
import com.kingfisher.utf.junit.model.TestCase;
import com.kingfisher.utf.junit.model.TestSuite;
import com.kingfisher.utf.junit.util.DataProvider;
import com.kingfisher.utf.junit.util.XmlPublisher;

import lombok.Data;

@Data
public class JUnitReporter {

	private TestSuite testSuite;

	public JUnitReporter(String name) {
		testSuite = new TestSuite(name, 0, 0, 0d, new ArrayList<TestCase>());
	}

	public JUnitReporter addTestCase(String testCaseName, double time) {
		DataProvider.addSuccessfulTestCase(this.getTestSuite(), testCaseName, time);
		return this;
	}

	public JUnitReporter addTestCase(String testCaseName, double time, Failure failure) {
		DataProvider.addFailedTestCase(this.getTestSuite(), testCaseName, time, failure);
		return this;
	}

	public String publishXml() throws JAXBException {
		String xmlTestReport = new XmlPublisher().publishXml(this.getTestSuite());
		return xmlTestReport;
	}

}
