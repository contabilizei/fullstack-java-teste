package com.luan.controllers;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.luan.models.Pedido;
import com.luan.services.PedidoService;
 
@Path("/pedidos")
public class HelloWorldController {
	
	private PedidoService pedidoService = new PedidoService();
 
	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = " Olá, API. Seja bem vindo: " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	
	@GET
	@Path("/get/{numero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPedido(@PathParam("numero") int numero) {

		Pedido pedido = pedidoService.getByNumero(numero);
		
		Gson gson = new Gson();
        String json = gson.toJson(pedido);
		
		return Response.status(200).entity(json).build();

	}
 
}