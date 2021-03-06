package com.kingfisher.utf.compiler.parser.statemachine;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.Data;

@Data
public class NFA {

	private static JsonObject stateTransitions;

	public NFA() throws IOException {
		stateTransitions = new JsonParser()
				.parse(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("state_transitions.json"),
						StandardCharsets.UTF_8.name()))
				.getAsJsonObject();
	}

	public String getInitialState() {
		return stateTransitions.get("initial_state").getAsString();
	}

	public List<String> getExpectedNextState(String currentState) {
		JsonElement allowedTransitions = stateTransitions.get("allowed_transitions").getAsJsonObject()
				.get(currentState);
		if (allowedTransitions != null)
			return Arrays.asList(new Gson().fromJson(allowedTransitions, String[].class));
		return null;
	}

	public boolean isTransitionValid(String lastState, String currentState) {
		JsonElement allowedTransitions = stateTransitions.get("allowed_transitions").getAsJsonObject().get(lastState);
		if (allowedTransitions != null) {
			for (JsonElement allowedState : allowedTransitions.getAsJsonArray()) {
				if (allowedState.getAsString().equals(currentState))
					return true;
			}
		}
		return false;
	}

	public boolean isStateAcceptable(String state) {
		for (JsonElement ignorableState : stateTransitions.get("accepted_states").getAsJsonArray()) {
			if (ignorableState.getAsString().equals(state))
				return true;
		}
		return false;
	}

	public boolean isStateValid(String state) {
		for (JsonElement ignorableState : stateTransitions.get("states").getAsJsonArray()) {
			if (ignorableState.getAsString().equals(state))
				return true;
		}
		return false;
	}
}
