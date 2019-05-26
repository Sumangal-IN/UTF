package com.utf.junit.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TestCase {
	private String name;
	private double time;
	private Failure failure;
}
