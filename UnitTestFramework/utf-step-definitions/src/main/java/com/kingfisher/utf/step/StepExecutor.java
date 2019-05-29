package com.kingfisher.utf.step;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import com.kingfisher.utf.exception.NoSuchStepException;
import com.kingfisher.utf.exception.ParameterMismatchException;
import com.kingfisher.utf.step.definitions.SetupSteps;
import com.kingfisher.utf.step.model.Step;

public class StepExecutor {

	public static void execute(String state, String statement)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, NoSuchStepException, ParameterMismatchException {
		Step step = new Step(state, statement);

		List<String> parameters = getParameterFromText(step.getStepInfo().getExpression(), statement);
		if (step.getStepInfo().getMethod().getParameterCount() != parameters.size()) {
			throw new ParameterMismatchException("Please provide correct parameters.");
		} else {
			Object[] objects = getFormatedParameter(step.getStepInfo().getMethod(), parameters);
			step.getStepInfo().getMethod().invoke(new SetupSteps(), objects);
		}
	}

	private static List<String> getParameterFromText(String regexValue, String stepValue) {
		String[] words = stepValue.split("\\s+");
		List<String> parameters = new ArrayList<>();

		for (int i = 0; i < words.length; i++) {
			if (!(regexValue.contains(words[i])))
				parameters.add(words[i]);
		}

		return parameters;
	}

	private static Object[] getFormatedParameter(Method method, List<String> parameters) {
		List<Object> objectList = new ArrayList<>();
		Parameter[] parammeterArray = method.getParameters();
		for (int i = 0; i < parameters.size(); i++) {
			objectList.add(toObject(parammeterArray[i].getType(), parameters.get(i)));
		}
		Object[] objects = objectList.toArray();
		return objects;
	}

	private static Object toObject(Class<?> clazz, String value) {
		if (Boolean.class == clazz || boolean.class == clazz)
			return Boolean.parseBoolean(value);
		if (Byte.class == clazz || byte.class == clazz)
			return Byte.parseByte(value);
		if (Short.class == clazz || short.class == clazz)
			return Short.parseShort(value);
		if (Integer.class == clazz || int.class == clazz)
			return Integer.parseInt(value);
		if (Long.class == clazz || long.class == clazz)
			return Long.parseLong(value);
		if (Float.class == clazz || float.class == clazz)
			return Float.parseFloat(value);
		if (Double.class == clazz || double.class == clazz)
			return Double.parseDouble(value);
		return value;
	}
}
