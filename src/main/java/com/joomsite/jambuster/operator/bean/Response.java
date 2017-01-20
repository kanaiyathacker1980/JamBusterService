package com.joomsite.jambuster.operator.bean;

public class Response<T> {

	String message;
	String status;
	T data;
	
	public Response(String status ) {
		this.status = status;
	}
	
	public Response(String status , String message ) {
		this.message = message;
		this.status = status;
	}
	
	public Response(String status , T data) {
		this.status = status;
		this.data = data;
	}
	
	public Response(String status , String message ,T data) {
		this.message = message;
		this.status = status;
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "Response [message=" + message + ", status=" + status
				+ ", data=" + data + "]";
	}
}
