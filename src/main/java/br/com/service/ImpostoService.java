package br.com.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.Negocio.ImpostoNegocio;
import br.com.entidades.Imposto;

/**
 * Classe de servico do Imposto
 * @author marinamontelo
 */
@Path("/imposto")
public class ImpostoService {

	
	private ImpostoNegocio negocio = new ImpostoNegocio();
	
	@POST
	@Path("salvar")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Imposto salvar(Imposto imposto) {
		return negocio.salvar(imposto);
	}
	
	@POST
	@Path("gerarImposto")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Imposto> gerar(Imposto imposto) {
		return negocio.gerarImposto(imposto);
	}
	
	@POST
	@Path("marcarPago")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Imposto marcarPago(Imposto imposto) {
		return negocio.marcarPago(imposto);
	}

	
	@POST
	@Path("listarTudo")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Imposto> getImpostos() {
		return negocio.listar(null);
	}	
	@POST
	@Path("pesquisarImposto")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Imposto> pesquisarImposto(Imposto imposto) {
		return negocio.listarPorMes(imposto);
	}
	
	@POST
	@Path("obterPorId")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Imposto obterPorId(long id) {
		return negocio.obterPorId(id);
	}
	
//	@POST
//	@Path("listarImpostosModal")
//	@Produces(MediaType.APPLICATION_JSON)
////	@Consumes(MediaType.APPLICATION_JSON)
//	public List<Imposto> getImpostos(String parametro) {
//		return negocio.listarPorAtributo(parametro, "email");
//	}

	@POST
	@Path("excluir")
	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes(MediaType.APPLICATION_JSON)
	public Imposto excluir(Imposto imposto){
		return negocio.deletar(imposto);
	}
	
}
