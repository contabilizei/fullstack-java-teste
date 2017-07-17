package br.com.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.Negocio.NotaFiscalNegocio;
import br.com.entidades.NotaFiscal;

@Path("/notafiscal")
public class NotaFiscalService {

	NotaFiscalNegocio negocio= new NotaFiscalNegocio();
	
	@POST
	@Path("salvar")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public NotaFiscal salvar(NotaFiscal notaFiscal) {
		return negocio.salvar(notaFiscal);
	}

	@POST
	@Path("listarTudo")
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<NotaFiscal> getProducts() {
		return negocio.listar(null);
	}

	
	@POST
	@Path("excluir")
	// @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public NotaFiscal excluir(NotaFiscal notaFiscalObj) {
		return negocio.deletar(notaFiscalObj);
	}

}
