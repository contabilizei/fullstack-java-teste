package com.luan.controllers;
 
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.luan.models.Pedido;
import com.luan.services.PedidoService;
 
@Path("/pedidos")
public class PedidosController {
	
	private PedidoService pedidoService = new PedidoService();
 
/*	@GET
	@Path("/{param}")
	public Response getMsg(@PathParam("param") String msg) {
 
		String output = " Olá, API. Seja bem vindo: " + msg;
 
		return Response.status(200).entity(output).build();
 
	}
	*/
	@GET
	@Path("/{numero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPedido(@PathParam("numero") int numero) {
		Pedido pedido = pedidoService.getByNumero(numero);
		return Response.status(200).entity(pedidoService.toJson(pedido)).build();
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTodosOsPedidos() {
		List<Pedido> pedidos = pedidoService.getAll();
		return Response.status(200).entity(pedidoService.toJson(pedidos)).build();
	}
	
	@POST
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvaPedido(String pedidoJson) {
		Pedido pedido = pedidoService.fromJson(pedidoJson);
		pedidoService.salvaPedido(pedido);
		return Response.status(200).entity(pedidoService.toJson(pedido)).build();
	}
	
	@DELETE
	@Path("/{numero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deletePedido(@PathParam("numero") int numero) {
		pedidoService.remover(numero);
		return Response.status(200).entity("").build();
	}

 
}