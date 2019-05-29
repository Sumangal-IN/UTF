package com.kingfisher.utf.step;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

public class StepProviderTest {
	@Test
	public void testGetSetupSteps() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException, ClassNotFoundException {
		System.out.println(StepProvider.getStepInfos("Setup"));
	}

}
