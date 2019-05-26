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
		assertEquals(new Statement(line, "scenario", "New Scenario"), new Lexer().analyseKeyword(line));
	}

	@Test
	public void testAnalyseKeywordWithValue2() throws JsonSyntaxException, IOException {
		String line = "Given there is a bus";
		assertEquals(new Statement(line, "given", "there is a bus"), new Lexer().analyseKeyword(line));
	}

	@Test
	public void testAnalyseKeywordWithoutValue() throws JsonSyntaxException, IOException {
		String line = "Setup:";
		assertEquals(new Statement(line, "setup", null), new Lexer().analyseKeyword(line));
	}

	@Test
	public void testAnalyseKeywordInvalid() throws JsonSyntaxException, IOException {
		String line = "Setup: ok";
		assertEquals(null, new Lexer().analyseKeyword(line));
	}

	@Test
	public void testAnalyseKeywordEmpty() throws JsonSyntaxException, IOException {
		String line = "";
		assertEquals(null, new Lexer().analyseKeyword(line));
	}

	@Test
	public void testAnalyseKeywordWhiteSpaces() throws JsonSyntaxException, IOException {
		String line = "   ";
		assertEquals(null, new Lexer().analyseKeyword(line));
	}

	@Test
	public void testAnalyseKeywordTabs() throws JsonSyntaxException, IOException {
		String line = " \t ";
		assertEquals(null, new Lexer().analyseKeyword(line));
	}

	@Test
	public void testAnalyseComment() throws JsonSyntaxException, IOException {
		String line = "#this is a comment";
		assertEquals(null, new Lexer().analyseKeyword(line));
	}

	@Test
	public void testAnalyseArbitaryText() throws JsonSyntaxException, IOException {
		String line = " this is a text";
		assertEquals(null, new Lexer().analyseKeyword(line));
	}

}
