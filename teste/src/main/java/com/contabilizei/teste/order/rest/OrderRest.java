package com.contabilizei.teste.order.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.contabilizei.teste.order.model.Order;
import com.contabilizei.teste.ordercontroller.OrderController;
import com.contabilizei.teste.orderitem.controller.OrderItemController;
import com.contabilizei.teste.rest.AbstractRest;
import com.contabilizei.teste.rest.RestResponse;

@Path("/orders")
public class OrderRest extends AbstractRest {
	
	private OrderController controller = new OrderController();
	private OrderItemController controllerItem = new OrderItemController();
	
	@GET
	@Path("/getOrder/{order}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrder(@PathParam("order") Integer id ){
		return Response.ok(new RestResponse(this.controller.findById(id))).build(); 
	}
	
	@GET
	@Path("/getAllOrders")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrders(){
		return buildResponse(this.controller.findAll()); 
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Order order){
		this.controller.create(order);
		return Response.ok("Contato salvo com sucesso").type(MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Order order){
		Order orderReturn = this.controller.update(order);
		return Response.ok(orderReturn.getId().toString() + " atualizado!").build();
	}
	
	@DELETE
	@Path("/deleteOrder/{id}")
	public Response delete (@PathParam("id") int id){
		this.controller.delete(id);
		return Response.ok("Cliente Excluido").build();
		
	}
	
	@DELETE
	@Path("/deleteItemOrder/{orderId}/{productId}")
	public Response deleteItemOrder (@PathParam("orderId") int orderId, @PathParam("productId") int productId){
		this.controllerItem.deleteItemOrder(orderId, productId);
		return Response.ok("Cliente Excluido").build();
		
	}

}
