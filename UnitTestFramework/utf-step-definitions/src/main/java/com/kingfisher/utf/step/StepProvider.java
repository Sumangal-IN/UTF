package com.kingfisher.utf.step;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.kingfisher.utf.step.model.StepInfo;

public class StepProvider {
	public static List<StepInfo> getStepInfos(String state) throws IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		List<StepInfo> stepInfos = new ArrayList<>();
		for (Method method : Class.forName("com.kingfisher.utf.step.definitions." + state + "Steps")
				.getDeclaredMethods()) {
			Class<? extends Annotation> annotationClass = (Class<? extends Annotation>) Class
					.forName("com.kingfisher.utf.step.annotation." + state);
			if (method.isAnnotationPresent(annotationClass)) {
				StepInfo stepInfo = new StepInfo(annotationClass.getDeclaredMethod("value")
						.invoke(method.getAnnotation(annotationClass)).toString(), method);
				stepInfos.add(stepInfo);
			}
		}
		return stepInfos;
	}
}
