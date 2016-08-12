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

import br.com.pedidoscontabilizei.dto.ClienteDTO;
import br.com.pedidoscontabilizei.servicos.ClienteServico;

@Path("/clientes")
public class ClientesResource {
	private ClienteServico clienteServico;

	public ClientesResource() {
		clienteServico = new ClienteServico();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response inserir(ClienteDTO cliente) {
		try {

			clienteServico.inserir(cliente);

			return Response.ok(cliente).build();

		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response alterar(ClienteDTO cliente) {
		try {			
			clienteServico.alterar(cliente);

			return Response.ok(cliente).build();
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("{cpf}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response excluir(@PathParam("cpf") String cpf) {
		try {
			boolean sucesso = clienteServico.remover(cpf);
			
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
	public List<ClienteDTO> buscarTodos() {		
		try {			
			return clienteServico.buscaTodosClientes();
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("{cpf}")
	@Produces(MediaType.APPLICATION_JSON)
	public ClienteDTO buscarPorCodigo(@PathParam("cpf") String cpf) {
		try {			
			return clienteServico.buscaClientePorDocumento(cpf);
			
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
}
