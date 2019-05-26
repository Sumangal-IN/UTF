package com.kingfisher.utf.compiler;

import java.io.IOException;

import org.junit.Test;

import com.kingfisher.utf.constant.CompilerException;

public class FeatureCompilerTest {

	@Test
	public void testCompile() throws CompilerException, IOException {
		FeatureCompiler.compile(this.getClass().getClassLoader().getResourceAsStream("test.feature"));
	}

}
