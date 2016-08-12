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

import br.com.pedidoscontabilizei.dto.ProdutoDTO;
import br.com.pedidoscontabilizei.servicos.ProdutoServico;

@Path("/produtos")
public class ProdutosResource {
	private ProdutoServico produtoServico;

	public ProdutosResource() {
		produtoServico = new ProdutoServico();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(ProdutoDTO cliente) {
		try {

			produtoServico.inserir(cliente);

			return Response.ok(cliente).build();

		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterar(ProdutoDTO cliente) {
		try {			
			produtoServico.alterar(cliente);

			return Response.ok(cliente).build();
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("codigo") Long codigo) {
		try {
			boolean sucesso = produtoServico.remover(codigo);
			
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
	public List<ProdutoDTO> buscarTodos() {		
		try {			
			return produtoServico.buscaTodosProdutos();
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{codigo}")
	@Produces(MediaType.APPLICATION_JSON)
	public ProdutoDTO buscarPorCodigo(@PathParam("codigo") Long codigo) {
		try {			
			return produtoServico.buscaProdutoPorCodigo(codigo);
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
