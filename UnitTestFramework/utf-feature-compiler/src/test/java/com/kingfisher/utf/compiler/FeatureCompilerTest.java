package com.kingfisher.utf.compiler;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.JsonSyntaxException;
import com.kingfisher.utf.exception.CompilerException;
import com.kingfisher.utf.exception.ParserException;

public class FeatureCompilerTest {

	@Test
	public void testCompile() throws CompilerException, IOException, JsonSyntaxException, ParserException {
		FeatureCompiler.compile(this.getClass().getClassLoader().getResourceAsStream("test.feature"));
	}

}
