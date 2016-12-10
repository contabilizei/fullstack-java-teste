package com.contabilizei.teste.rest;

import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractRest {

	protected ObjectMapper getObjectMapper() {
		return new ObjectMapper();
	}

	protected Response buildResponse(Object o) {

		try {
			String json = getObjectMapper().writeValueAsString(new RestResponse(o));
			return Response.ok(json).build();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return buildErrorResponse(e);
		}
	}

	protected Response buildErrorResponse(Throwable e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
	}
}
