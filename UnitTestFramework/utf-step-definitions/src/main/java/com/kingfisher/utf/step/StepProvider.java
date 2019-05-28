package com.kingfisher.utf.step;

import java.util.ArrayList;
import java.util.List;

import com.kingfisher.utf.step.annotation.Setup;

import java.lang.reflect.Method;

public class StepProvider {

	public static List<String> getSetupSteps() {
		List<String> steps = new ArrayList<>();
		for (Method method : Setup.class.getDeclaredMethods()) {
			if (method.isAnnotationPresent(Setup.class))
				steps.add(((Setup) method.getAnnotation(Setup.class)).value());
		}
		return steps;
	}
}
