package com.kingfisher.utf.compiler;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonObject;
import com.kingfisher.utf.exception.compilerException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FeatureCompiler {
	public static JsonObject compile(InputStream inputStream) throws compilerException, IOException {
		String featureContent = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
		return null;
	}
}
