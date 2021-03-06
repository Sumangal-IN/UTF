package com.kingfisher.utf.compiler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class Statement {
	private int lineNo;
	private String line;
	private String keyword;
	private String value;
	private boolean actionable;
}
