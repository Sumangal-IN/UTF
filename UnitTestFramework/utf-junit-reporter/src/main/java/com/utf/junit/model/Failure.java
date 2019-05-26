package com.utf.junit.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Failure {
	private String message;
	private String type;
	private String text;
}
