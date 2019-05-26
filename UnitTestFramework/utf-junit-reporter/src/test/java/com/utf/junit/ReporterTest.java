package com.utf.junit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import com.utf.junit.model.Failure;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class ReporterTest {

	@Test
	public void testPublish() throws ParserConfigurationException, TransformerException, IOException {
		String actualXML = new Reporter("MyTestSuite")
				.addTestCases("MyTestCase1", 1.0)
				.addTestCases("MyTestCase2", 1.5)
				.addTestCases("MyTestCase3", 0.4, new Failure("Sorry My fault", "WARN", "I will be careful next time"))
				.publish();
		String expectedXML = IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("output.xml"),
				StandardCharsets.UTF_8.name());
		assertEquals(expectedXML, actualXML);
	}

}
