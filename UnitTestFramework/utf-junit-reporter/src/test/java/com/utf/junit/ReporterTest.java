package com.utf.junit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.kingfisher.utf.junit.JUnitReporter;
import com.kingfisher.utf.junit.model.Failure;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ReporterTest {

	@Test
	public void testPublish() throws ParserConfigurationException, TransformerException, IOException, JAXBException {
		String actualXML = new JUnitReporter("MyTestSuite").addTestCase("MyTestCase1", 1.0)
				.addTestCase("MyTestCase2", 1.5)
				.addTestCase("MyTestCase3", 0.4, new Failure("Sorry My fault", "WARN", "I will be careful next time"))
				.publishXml();
		String expectedXML = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("output.xml"),
				StandardCharsets.UTF_8.name());
		assertEquals(expectedXML, actualXML);
	}

}
