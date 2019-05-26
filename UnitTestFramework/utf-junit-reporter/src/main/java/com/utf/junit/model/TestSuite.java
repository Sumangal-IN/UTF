package com.utf.junit.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestSuite {
	private String name;
	private int tests;
	private int failures;
	private double time;

	private List<TestCase> testCases;
}
