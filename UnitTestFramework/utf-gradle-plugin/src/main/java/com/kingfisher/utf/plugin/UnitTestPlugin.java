package com.kingfisher.utf.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import com.kingfisher.utf.plugin.task.UnitTest;

public class UnitTestPlugin implements Plugin<Project> {
	public void apply(Project project) {
		project.getTasks().create("UnitTest", UnitTest.class, (task) -> {
		});
	}
}
