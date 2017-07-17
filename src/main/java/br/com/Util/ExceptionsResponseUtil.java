package br.com.Util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

public class ExceptionsResponseUtil {

	public static WebApplicationException obterWebApplicationExceptionComResponse (String mensagem, Status badRequest){
		ResponseBuilder builder = Response.status(badRequest);
	    builder.entity(mensagem);
	    Response response = builder.build();
	    return new WebApplicationException(response);
	}
}
