package com.contabilizei.teste.rest;

public class RestResponse {
	
	private Object data;

	public RestResponse(Object data) {
		super();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
}
