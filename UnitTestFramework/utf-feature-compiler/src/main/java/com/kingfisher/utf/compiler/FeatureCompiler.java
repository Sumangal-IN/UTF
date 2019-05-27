package com.kingfisher.utf.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.kingfisher.utf.compiler.parser.Parser;
import com.kingfisher.utf.constant.LoggerConstant;
import com.kingfisher.utf.exception.CompilerException;
import com.kingfisher.utf.exception.ParserException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeatureCompiler {
	public static JsonObject compile(InputStream inputStream) throws CompilerException, IOException, JsonSyntaxException, ParserException {
		log.debug(LoggerConstant.DEBUG_LOG_ENTERED, "compile()");
		String featureContent = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		new Parser().parse(featureContent);
		log.debug(LoggerConstant.DEBUG_LOG_LEAVING, "compile()");
		return null;
	}
}
