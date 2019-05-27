package com.kingfisher.utf.compiler;

import java.io.IOException;

import org.junit.Test;

import com.google.gson.JsonSyntaxException;
import com.kingfisher.utf.constant.CompilerException;
import com.kingfisher.utf.exception.ParseException;

public class FeatureCompilerTest {

	@Test
	public void testCompile() throws CompilerException, IOException, JsonSyntaxException, ParseException {
		FeatureCompiler.compile(this.getClass().getClassLoader().getResourceAsStream("test.feature"));
	}

}
