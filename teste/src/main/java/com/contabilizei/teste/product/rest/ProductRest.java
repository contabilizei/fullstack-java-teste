package com.contabilizei.teste.product.rest;

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

import com.contabilizei.teste.product.controller.ProductController;
import com.contabilizei.teste.product.model.Product;
import com.contabilizei.teste.rest.RestResponse;

@Path("/products")
public class ProductRest {
	
	private ProductController controller = new ProductController();
	
	@GET
	@Path("/getProduct/{product}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProduct(@PathParam("product") Integer id ){
		return Response.ok(new RestResponse(this.controller.findById(id))).build(); 
	}
	
	@GET
	@Path("/getAllProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProduct(){
		return Response.ok(new RestResponse(this.controller.findAll())).build(); 
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response save(Product product){
		this.controller.create(product);
		return Response.ok("Produto salvo com sucesso").type(MediaType.TEXT_PLAIN).build();
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Product product){
		Product productReturn = this.controller.update(product);
		return Response.ok(productReturn.getName() + " atualizado!").build();
	}
	
	@DELETE
	@Path("/deleteProduct/{id}")
	public Response delete (@PathParam("id") int id){
		this.controller.delete(id);
		return Response.ok("Cliente Excluido").build();
		
	}

}
