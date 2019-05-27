package com.kingfisher.utf.compiler.lexer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.IOUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.kingfisher.utf.compiler.model.Statement;

public class Lexer {

	private static JsonArray keywords;

	public Lexer() throws JsonSyntaxException, IOException {
		keywords = new JsonParser()
				.parse(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("keywords.json"),
						StandardCharsets.UTF_8.name()))
				.getAsJsonArray();
	}

	public Statement analyseKeyword(String line, int lineNo) {
		line = trim(line);
		if (line.startsWith("#") || line.equals(""))
			return null;
		for (JsonElement keyword : keywords) {
			String regex = keyword.getAsJsonObject().get("regex").getAsString();
			if (Pattern.matches(regex, line)) {
				if (keyword.getAsJsonObject().get("value") != null) {
					String value = line;
					for (JsonElement exclude : keyword.getAsJsonObject().get("value").getAsJsonObject().get("exclude")
							.getAsJsonArray()) {
						Matcher matcher = Pattern.compile(exclude.getAsString()).matcher(line);
						if (matcher.find())
							value = value.replace(matcher.group(), "");
					}
					return new Statement(lineNo, line, keyword.getAsJsonObject().get("state").getAsString(),
							trim(value), keyword.getAsJsonObject().get("actionable").getAsBoolean());
				} else
					return new Statement(lineNo, line, keyword.getAsJsonObject().get("state").getAsString(), null,
							keyword.getAsJsonObject().get("actionable").getAsBoolean());
			}
		}
		return null;
	}

	private String trim(String statement) {
		statement = statement.replaceAll("\\t", " ");
		return statement.trim();
	}
}
