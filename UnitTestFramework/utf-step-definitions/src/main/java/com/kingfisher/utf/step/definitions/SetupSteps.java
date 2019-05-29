package com.kingfisher.utf.step.definitions;

import com.kingfisher.utf.step.annotation.Setup;

public class SetupSteps {
	@Setup("^Create queue (\\w+)$")
	public void createQueue(String queueName) {
		System.out.println("Queue " + queueName + " created");
	}

	@Setup("^Create queue (\\w+) with capacity (\\d+)$")
	public void createQueueWithCapacity(String queueName, int capacity) {
		System.out.println("Queue " + queueName + " created with capacity " + capacity);
	}
}
