package com.kingfisher.utf.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;
import com.kingfisher.utf.constant.CompilerException;
import com.kingfisher.utf.exception.LoggerConstant;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeatureCompiler {
	public static JsonObject compile(InputStream inputStream) throws CompilerException, IOException {
		log.debug(LoggerConstant.DEBUG_LOG_ENTERED, "compile()");
		String featureContent = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		log.debug(featureContent);
		log.debug(LoggerConstant.DEBUG_LOG_LEAVING, "compile()");
		return null;
	}
}
