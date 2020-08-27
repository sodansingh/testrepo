package com.task.login.model;


public class APIResponse<T> {
	
	//structure of API response
	private int status;
	private String message;
	private T response;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResponse() {
		return response;
	}
	public void setResponse(T response) {
		this.response = response;
	}
	
	
	@Override
	public String toString() {
		return "APIResponse [status=" + status + ", message=" + message + ", response=" + response + "]";
	}
	
	
	
	

}
