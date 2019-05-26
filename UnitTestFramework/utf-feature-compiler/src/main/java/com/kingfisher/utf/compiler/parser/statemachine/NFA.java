package com.kingfisher.utf.compiler.parser.statemachine;

import lombok.Data;

@Data
public class NFA {
	private String state;

	public NFA() {

	}

	public boolean isTransitionValid(String lastState, String currentState) {
		return true; 
	}

	public boolean isStateIgnorable(String state) {
		return true;
	}

	public boolean isStateAcceptable(String state) {
		return true;
	}

	public boolean isStateValid(String state) {
		return true;
	}
}
