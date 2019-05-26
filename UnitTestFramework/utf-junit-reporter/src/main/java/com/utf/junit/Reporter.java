package com.utf.junit;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

import com.kingfisher.utf.exception.LoggerConstant;
import com.utf.junit.model.Failure;
import com.utf.junit.model.TestCase;
import com.utf.junit.model.TestSuite;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class Reporter {

	Document document;
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

	public void publish(File XMLFile) throws FileNotFoundException, ParserConfigurationException, TransformerException {
		log.debug(LoggerConstant.DEBUG_LOG_ENTERED, "publish()");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		Element nodeTestSuite = doc.createElement("testsuite");
		nodeTestSuite.setAttribute("name", this.getTestSuite().getName());
		nodeTestSuite.setAttribute("tests", Integer.toString(this.getTestSuite().getTests()));
		nodeTestSuite.setAttribute("failures", Integer.toString(this.getTestSuite().getFailures()));
		nodeTestSuite.setAttribute("time", Double.toString(this.getTestSuite().getTime()));
		for (TestCase testCase : this.getTestSuite().getTestCases()) {
			Element nodeTestCase = doc.createElement("testcase");
			nodeTestCase.setAttribute("name", testCase.getName());
			nodeTestCase.setAttribute("time", Double.toString(testCase.getTime()));
			if (testCase.getFailure() != null) {
				Element nodefailure = doc.createElement("failure");
				nodefailure.setAttribute("message", testCase.getFailure().getMessage());
				nodefailure.setAttribute("text", testCase.getFailure().getText());
				nodefailure.setAttribute("type", testCase.getFailure().getType());
				nodeTestCase.appendChild(nodefailure);
			}
			nodeTestSuite.appendChild(nodeTestCase);
		}
		doc.appendChild(nodeTestSuite);

		// Transforn
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();

		// Beautify the xml
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

		// Save into file
		Source s = new DOMSource(doc);
		Result res = new StreamResult(new FileOutputStream(XMLFile));
		transformer.transform(s, res);
		log.debug("XML File Created Succesfully");
		log.debug(LoggerConstant.DEBUG_LOG_LEAVING, "publish()");
	}

}
