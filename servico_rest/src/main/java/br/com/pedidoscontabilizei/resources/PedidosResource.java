package br.com.pedidoscontabilizei.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.pedidoscontabilizei.dto.PedidoDTO;
import br.com.pedidoscontabilizei.servicos.PedidoServico;

@Path("/pedidos")
public class PedidosResource {

	private PedidoServico pedidoServico;

	public PedidosResource() {
		pedidoServico = new PedidoServico();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(PedidoDTO pedido) {
		try {

			pedidoServico.inserir(pedido);

			return Response.ok(pedido).build();

		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterar(PedidoDTO pedido) {
		try {			
			pedidoServico.alterar(pedido);

			return Response.ok(pedido).build();
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("{numero}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("numero") Long numero) {
		try {
			boolean sucesso = pedidoServico.remover(numero);
			
			if (!sucesso) {
				throw new WebApplicationException(404);
			}
			
			return Response.ok().build();
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}		
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<PedidoDTO> buscarTodos() {		
		try {			
			return pedidoServico.buscaTodosPedidos();
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{numero}")
	@Produces(MediaType.APPLICATION_JSON)
	public PedidoDTO buscarPorCodigo(@PathParam("numero") Long numero) {
		try {			
			return pedidoServico.buscaPedidoPorDocumento(numero);
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
