package com.kingfisher.utf.compiler.parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.kingfisher.utf.compiler.lexer.Lexer;
import com.kingfisher.utf.compiler.model.Statement;
import com.kingfisher.utf.compiler.parser.statemachine.NFA;
import com.kingfisher.utf.exception.ParseException;

public class Parser {

	public JsonObject parse(String featureContent) throws JsonSyntaxException, IOException, ParseException {
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
		return null;
	}

	private boolean checkGrammar(List<Statement> statements) throws IOException, ParseException {
		NFA stateMachine = new NFA();
		String currentState = stateMachine.getInitialState();
		for (Statement statement : statements) {
			if (stateMachine.isTransitionValid(currentState, statement.getKeyword())) {
				currentState = statement.getKeyword();
			} else {
				throw new ParseException(
						"@line " + statement.getLineNo() + " Unexpected keyword " + statement.getKeyword()
								+ " Expected one of the " + stateMachine.getExpectedNextState(currentState));
			}
		}
		return true;
	}
}
