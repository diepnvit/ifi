package com.ifi.message;

public class MessageInfo {
	
	private String status;
	private Object data;
	
	public MessageInfo() {
		// TODO Auto-generated constructor stub
	}

	public MessageInfo(String status, Object data) {
		super();
		this.status = status;
		this.data = data;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	

}
