package com.luan.controllers;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/hello")
public class HelloWorldController {
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = " Ol�, API. Seja bem vindo: " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
 
}