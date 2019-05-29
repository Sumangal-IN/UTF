package com.kingfisher.utf.plugin.task;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.TaskAction;

import lombok.Data;

@Data
public class UnitTest extends DefaultTask {
    @TaskAction
    void sayGreeting() {
        System.out.println("Hello"); 
    }
}