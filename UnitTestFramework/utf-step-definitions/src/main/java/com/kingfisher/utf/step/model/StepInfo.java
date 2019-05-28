package com.kingfisher.utf.step.model;

import java.lang.reflect.Method;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class StepInfo {
	private String expression;
	private Method method;
}
