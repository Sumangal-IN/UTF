package com.kingfisher.utf.plugin.task;

import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import com.kingfisher.utf.plugin.exception.InsufficientArgumentException;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UnitTest extends DefaultTask {
	private String featureDir;
	private String reportDir;

	@TaskAction
	void task() throws InsufficientArgumentException {
		if (featureDir == null || reportDir == null)
			throw new InsufficientArgumentException(
					"arguments required: -DfeatureDir=<path to feature dir> -DreportDir=<path to report dir>");
		System.out.println(this);
	}
}