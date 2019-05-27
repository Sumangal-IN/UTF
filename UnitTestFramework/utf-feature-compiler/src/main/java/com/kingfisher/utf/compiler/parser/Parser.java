package com.kingfisher.utf.compiler.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.kingfisher.utf.compiler.lexer.Lexer;
import com.kingfisher.utf.compiler.model.Statement;
import com.kingfisher.utf.compiler.parser.statemachine.NFA;
import com.kingfisher.utf.exception.ParserException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Parser {

	public String parse(String featureContent) throws JsonSyntaxException, IOException, ParserException {
		Lexer lexer = new Lexer();
		Scanner scanner = new Scanner(featureContent);
		int lineNo = 0;
		List<Statement> statements = new ArrayList<>();
		while (scanner.hasNextLine()) {
			lineNo = lineNo + 1;
			Statement statement = lexer.analyseKeyword(scanner.nextLine(), lineNo);
			if (statement != null)
				statements.add(statement);
		}
		scanner.close();
		checkGrammar(statements);
		return exportJSON(statements);
	}

	private boolean checkGrammar(List<Statement> statements) throws IOException, ParserException {
		NFA stateMachine = new NFA();
		String currentState = stateMachine.getInitialState();
		for (Statement statement : statements) {
			if (stateMachine.isTransitionValid(currentState, statement.getKeyword())) {
				currentState = statement.getKeyword();
			} else {
				throw new ParserException(
						"@line " + statement.getLineNo() + ": Unexpected keyword " + statement.getKeyword()
								+ "; Expected one of the " + stateMachine.getExpectedNextState(currentState));
			}
		}
		return true;
	}

	private String exportJSON(List<Statement> statements) throws JsonSyntaxException, IOException, ParserException {
		Gson gson = new Gson();
		JsonParser jsonParser = new JsonParser();
		JsonObject root = (JsonObject) jsonParser.parse("{}");

		JsonArray setups = new JsonArray();
		JsonArray teardowns = new JsonArray();
		for (Statement statement : statements) {
			if (statement.getKeyword().equals("Setup"))
				setups.add(gson.toJsonTree(statement));
			if (statement.getKeyword().equals("Teardown"))
				teardowns.add(gson.toJsonTree(statement));
		}

		JsonArray features = new JsonArray();
		JsonArray scenarios = null;
		JsonElement feature = null;
		JsonElement scenario = null;
		JsonElement data = null;
		List<String> headers = null;
		for (Statement statement : statements) {
			switch (statement.getKeyword()) {
			case "Feature":
				feature = gson.toJsonTree(statement);
				scenarios = new JsonArray();
				feature.getAsJsonObject().add("scenario", scenarios);
				features.add(feature);
				break;
			case "Scenario":
				scenario = gson.toJsonTree(statement);
				scenarios.add(scenario);
				break;
			case "Given":
				scenario.getAsJsonObject().add("Given", gson.toJsonTree(statement));
				break;
			case "When":
				scenario.getAsJsonObject().add("When", gson.toJsonTree(statement));
				break;
			case "Then":
				scenario.getAsJsonObject().add("Then", gson.toJsonTree(statement));
				break;
			case "DataHeader":
				headers = getSplits(statement.getValue());
				data = new JsonArray();
				scenario.getAsJsonObject().add("Data", data);
				break;
			case "DataRow":
				List<String> fields = getSplits(statement.getValue());
				if (headers.size() != fields.size())
					throw new ParserException("@line " + statement.getLineNo()
							+ ": values are not aligned to headers; Expected " + headers.size() + " values");
				JsonObject d = new JsonObject();
				for (int i = 0; i < headers.size(); i++)
					d.addProperty(headers.get(i), fields.get(i));
				scenario.getAsJsonObject().get("Data").getAsJsonArray().add(d);
				break;
			}
		}
		root.add("Setup", setups);
		root.add("Teardown", teardowns);
		root.add("Features", features);
		return gson.toJson(root);
	}

	private String trim(String statement) {
		statement = statement.replaceAll("\\t", " ");
		return statement.trim();
	}

	private List<String> getSplits(String value) {
		List<String> values = new ArrayList<>();
		value = trim(value);
		String[] splittedValues = value.split("\\|");
		for (int i = 0; i < splittedValues.length; i++) {
			splittedValues[i] = trim(splittedValues[i]);
			if (!splittedValues[i].equals(""))
				values.add(splittedValues[i]);
		}
		return values;
	}
}
