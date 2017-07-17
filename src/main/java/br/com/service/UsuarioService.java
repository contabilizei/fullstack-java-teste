package br.com.service;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.Negocio.UsuarioNegocio;
import br.com.entidades.Usuario;

@Path("/usuario")
public class UsuarioService {

	UsuarioNegocio negocio = new UsuarioNegocio();
	
	@POST
	@Path("salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario salvar(Usuario usuario) {
		return negocio.salvar(usuario);
	}

	
	@POST
	@Path("listarTudo")
    @Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios() {
		return negocio.listar(null);
	}
	
	@POST
	@Path("listarUsuariosModal")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listarPorAtributo(String razaoSocial) {
		return negocio.listarPorAtributo(razaoSocial, "razaoSocial");
	}

	@POST
	@Path("excluir")
	@Consumes(MediaType.APPLICATION_JSON)
	public Usuario excluir(Usuario usuario) {
		return negocio.deletar(usuario); 
	}


}
