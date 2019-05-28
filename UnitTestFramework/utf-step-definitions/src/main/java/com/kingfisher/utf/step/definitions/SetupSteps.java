package com.kingfisher.utf.step.definitions;

import com.kingfisher.utf.step.annotation.Setup;

public class SetupSteps {
	@Setup("^Create queue (.*)$")
	public void createQueue(String queueName) {
		
	}

	@Setup("^Create queue (.*) with capacity (\\d+)$")
	public void createQueueWithCapacity(String queueName, int capacity) {
		
	}
}
