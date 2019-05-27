package com.kingfisher.utf.junit.util;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.kingfisher.utf.constant.LoggerConstant;
import com.kingfisher.utf.junit.model.TestSuite;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class XmlPublisher {

	public String publishXml(TestSuite testSuite) throws JAXBException {
		log.debug(LoggerConstant.DEBUG_LOG_ENTERED, "publishXml()");
		String xmlReport = null;
		JAXBContext contextObj = JAXBContext.newInstance(TestSuite.class);
		Marshaller marshallerObj = contextObj.createMarshaller();
		marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		StringWriter stringWriter = new StringWriter();
		marshallerObj.marshal(testSuite, stringWriter);
		xmlReport = stringWriter.toString();
		xmlReport=xmlReport.replace("\n", "\r\n");
		log.debug(LoggerConstant.DEBUG_LOG_LEAVING, "publishXml()");
		return xmlReport;
	}

}
