package com.kingfisher.utf.compiler.lexer;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.JsonSyntaxException;
import com.kingfisher.utf.compiler.model.Statement;

public class LexerTest {

	@Test
	public void testAnalyseKeywordWithValue() throws JsonSyntaxException, IOException {
		String line = "Scenario: New Scenario";
		int lineNo = 1;
		assertEquals(new Statement(lineNo, line, "scenario", "New Scenario"), new Lexer().analyseKeyword(line, lineNo));
	}

	@Test
	public void testAnalyseKeywordWithValue2() throws JsonSyntaxException, IOException {
		String line = "Given there is a bus";
		int lineNo = 1;
		assertEquals(new Statement(lineNo, line, "given", "there is a bus"), new Lexer().analyseKeyword(line, lineNo));
	}

	@Test
	public void testAnalyseKeywordWithoutValue() throws JsonSyntaxException, IOException {
		String line = "Setup:";
		int lineNo = 1;
		assertEquals(new Statement(lineNo, line, "setup", null), new Lexer().analyseKeyword(line, lineNo));
	}

	@Test
	public void testAnalyseKeywordInvalid() throws JsonSyntaxException, IOException {
		String line = "Setup: ok";
		int lineNo = 1;
		assertEquals(null, new Lexer().analyseKeyword(line, lineNo));
	}

	@Test
	public void testAnalyseKeywordEmpty() throws JsonSyntaxException, IOException {
		String line = "";
		int lineNo = 1;
		assertEquals(null, new Lexer().analyseKeyword(line, lineNo));
	}

	@Test
	public void testAnalyseKeywordWhiteSpaces() throws JsonSyntaxException, IOException {
		String line = "   ";
		int lineNo = 1;
		assertEquals(null, new Lexer().analyseKeyword(line, lineNo));
	}

	@Test
	public void testAnalyseKeywordTabs() throws JsonSyntaxException, IOException {
		String line = " \t ";
		int lineNo = 1;
		assertEquals(null, new Lexer().analyseKeyword(line, lineNo));
	}

	@Test
	public void testAnalyseComment() throws JsonSyntaxException, IOException {
		String line = "#this is a comment";
		int lineNo = 1;
		assertEquals(null, new Lexer().analyseKeyword(line, lineNo));
	}

	@Test
	public void testAnalyseArbitaryText() throws JsonSyntaxException, IOException {
		String line = " this is a text";
		int lineNo = 1;
		assertEquals(null, new Lexer().analyseKeyword(line, lineNo));
	}

}
