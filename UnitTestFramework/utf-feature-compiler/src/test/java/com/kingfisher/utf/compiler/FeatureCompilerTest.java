package com.kingfisher.utf.compiler;

import java.io.IOException;

import org.junit.Test;

import com.kingfisher.utf.exception.compilerException;

public class FeatureCompilerTest {

	@Test
	public void testCompile() throws compilerException, IOException {
		FeatureCompiler.compile(this.getClass().getClassLoader().getResourceAsStream("user.feature"));
	}

}
