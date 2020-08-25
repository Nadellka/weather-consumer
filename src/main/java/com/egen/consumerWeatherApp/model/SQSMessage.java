package com.egen.consumerWeatherApp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SQSMessage {
	@JsonProperty("Type")
	private String type;
	@JsonProperty("Message")
	private String message;
	@JsonProperty("MessageId")
	private String messageId;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	@Override
	public String toString() {
		return "SQSMessage [type=" + type + ", message=" + message + ", messageId=" + messageId + "]";
	}
	
	
	

}
