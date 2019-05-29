package com.kingfisher.utf.step.model;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Pattern;

import com.kingfisher.utf.exception.NoSuchStepException;
import com.kingfisher.utf.step.StepProvider;

import lombok.Data;

@Data
public class Step {

	private StepInfo stepInfo;

	public Step(String state, String statement)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException,
			SecurityException, ClassNotFoundException, NoSuchStepException {
		List<StepInfo> stepInfos = StepProvider.getStepInfos(state);
		for (StepInfo stepInfo : stepInfos)
			if (isExpressionMatches(stepInfo.getExpression(), statement)) {
				this.stepInfo = stepInfo;
				return;
			}
		throw new NoSuchStepException();
	}

	private boolean isExpressionMatches(String expression, String statement) {

		return Pattern.matches(expression, statement);
	}

}
