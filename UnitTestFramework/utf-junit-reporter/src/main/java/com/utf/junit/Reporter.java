package com.utf.junit;

import java.io.FileNotFoundException;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.kingfisher.utf.exception.ApplicationConstant;
import com.kingfisher.utf.exception.LoggerConstant;
import com.utf.junit.model.Failure;
import com.utf.junit.model.TestCase;
import com.utf.junit.model.TestSuite;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Reporter {

	TestSuite testSuite;

	public Reporter(String name) throws ParserConfigurationException {
		testSuite = new TestSuite(name, 0, 0, 0d, new ArrayList<TestCase>());
	}

	public Reporter addTestCases(String name, Double time, Failure failure) {
		TestCase testCase = new TestCase(name, time, failure);
		this.getTestSuite().getTestCases().add(testCase);
		this.getTestSuite().setTests(this.getTestSuite().getTests() + 1);
		this.getTestSuite().setTime(this.getTestSuite().getTime() + time);
		this.getTestSuite().setFailures(this.getTestSuite().getFailures() + 1);
		return this;
	}

	public Reporter addTestCases(String name, Double time) {
		TestCase testCase = new TestCase(name, time, null);
		this.getTestSuite().getTestCases().add(testCase);
		this.getTestSuite().setTime(this.getTestSuite().getTime() + time);
		this.getTestSuite().setTests(this.getTestSuite().getTests() + 1);
		return this;
	}

	public String publish() throws FileNotFoundException, ParserConfigurationException, TransformerException {
		log.debug(LoggerConstant.DEBUG_LOG_ENTERED, "publish()");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		Element nodeTestSuite = doc.createElement(ApplicationConstant.JUNIT_XML_NODE_TESTSUITE);
		nodeTestSuite.setAttribute(ApplicationConstant.JUNIT_XML_ATTRIBUTE_NAME, this.getTestSuite().getName());
		nodeTestSuite.setAttribute(ApplicationConstant.JUNIT_XML_ATTRIBUTE_TESTS,
				Integer.toString(this.getTestSuite().getTests()));
		nodeTestSuite.setAttribute(ApplicationConstant.JUNIT_XML_ATTRIBUTE_FAILURES,
				Integer.toString(this.getTestSuite().getFailures()));
		nodeTestSuite.setAttribute(ApplicationConstant.JUNIT_XML_ATTRIBUTE_TIME,
				Double.toString(this.getTestSuite().getTime()));
		for (TestCase testCase : this.getTestSuite().getTestCases()) {
			Element nodeTestCase = doc.createElement(ApplicationConstant.JUNIT_XML_NODE_TESTCASE);
			nodeTestCase.setAttribute(ApplicationConstant.JUNIT_XML_ATTRIBUTE_NAME, testCase.getName());
			nodeTestCase.setAttribute(ApplicationConstant.JUNIT_XML_ATTRIBUTE_TIME,
					Double.toString(testCase.getTime()));
			if (testCase.getFailure() != null) {
				Element nodefailure = doc.createElement(ApplicationConstant.JUNIT_XML_NODE_FAILURE);
				nodefailure.setAttribute(ApplicationConstant.JUNIT_XML_ATTRIBUTE_MESSAGE,
						testCase.getFailure().getMessage());
				nodefailure.setAttribute(ApplicationConstant.JUNIT_XML_ATTRIBUTE_TEXT, testCase.getFailure().getText());
				nodefailure.setAttribute(ApplicationConstant.JUNIT_XML_ATTRIBUTE_TYPE, testCase.getFailure().getType());
				nodeTestCase.appendChild(nodefailure);
			}
			nodeTestSuite.appendChild(nodeTestCase);
		}
		doc.appendChild(nodeTestSuite);

		// Transform
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		// Beautify the xml
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		// transform document to String
		StringWriter writer = new StringWriter();
		transformer.transform(new DOMSource(doc), new StreamResult(writer));
		String xmlString = writer.getBuffer().toString();
		log.debug(LoggerConstant.DEBUG_LOG_LEAVING, "publish()");
		return xmlString;
	}

}
