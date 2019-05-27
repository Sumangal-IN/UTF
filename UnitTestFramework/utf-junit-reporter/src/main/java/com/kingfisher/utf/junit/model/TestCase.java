package com.kingfisher.utf.junit.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "name", "time", "failure" })
public class TestCase {

	private String name;
	private Double time;
	private Failure failure;

	public TestCase() {
		super();
	}

	public TestCase(String name, Double time, Failure failure) {
		super();
		this.name = name;
		this.time = time;
		this.failure = failure;
	}

	public TestCase(String name, Double time) {
		super();
		this.name = name;
		this.time = time;
	}

	@XmlAttribute(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlAttribute(name = "time")
	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	@XmlElement(name = "failure")
	public Failure getFailure() {
		return failure;
	}

	public void setFailure(Failure failure) {
		this.failure = failure;
	}

	@Override
	public String toString() {
		return "TestCase [name=" + name + ", time=" + time + ", failure=" + failure + "]";
	}

}
