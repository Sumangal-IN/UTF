package com.kingfisher.utf.junit.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlType(propOrder = {"message", "type"})
public class Failure {

	private String message;
	private String type;
	private String text;

	public Failure() {
		super();
	}

	public Failure(String message, String type, String text) {
		super();
		this.message = message;
		this.type = type;
		this.text = text;
	}

	@XmlAttribute(name = "message")
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@XmlAttribute(name = "type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@XmlValue
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Failure [message=" + message + ", type=" + type + ", text=" + text + "]";
	}

}
