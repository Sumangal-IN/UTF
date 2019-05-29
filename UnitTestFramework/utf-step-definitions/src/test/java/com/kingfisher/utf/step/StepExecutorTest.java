package com.kingfisher.utf.step;

import java.lang.reflect.InvocationTargetException;

import org.junit.Test;

import com.kingfisher.utf.exception.NoSuchStepException;
import com.kingfisher.utf.exception.ParameterMismatchException;

public class StepExecutorTest {

	@Test
	public void testExecuteWithSingleParameters() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, NoSuchStepException, ParameterMismatchException {
		StepExecutor.execute("Setup", "Create queue A");
	}
	
	@Test
	public void testExecuteWithMultiParameters()
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, NoSuchStepException, ParameterMismatchException {
		StepExecutor.execute("Setup", "Create queue 12 with capacity 10");
	}

}
