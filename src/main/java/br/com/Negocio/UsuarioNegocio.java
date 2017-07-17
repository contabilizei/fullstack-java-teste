package br.com.Negocio;

import java.util.List;

import br.com.entidades.Usuario;

import com.google.appengine.api.datastore.Entity;

/**
 * Classe de negocio do usuario
 * @author marinamontelo
 *
 */
public class UsuarioNegocio extends NegocioGenerico<Usuario> {


	@Override
	public String getNomeEntidade() {
		return "usuario";
	}

	@Override
	public String getNomeTabela() {
		return "usuarios";
	}

	@Override
	public String atributoOrdenacaoPadrao() {
		return "razaoSocial";
	}

	@Override
	protected void entidadeGenericoToEntity(Usuario entidade,Entity entity) {
		entity.setProperty("razaoSocial", entidade.getRazaoSocial());
		entity.setProperty("regimeTributario",	entidade.getRegimeTributario());
		entity.setProperty("anexos",entidade.getAnexos());  
		entity.setProperty("email", entidade.getEmail());
		entity.setProperty("id", entidade.getId());
	}

	@Override
	protected Usuario entityToEntidadeGenerico(Entity entity) {

		Usuario usuario = new Usuario();
		usuario.setGoogleId((entity.getKey().getId()));
		usuario.setId((Long)entity.getProperty("id"));
		usuario.setRazaoSocial((String) entity.getProperty("razaoSocial"));
		usuario.setRegimeTributario((String) entity.getProperty("regimeTributario"));
		usuario.setEmail((String) entity.getProperty("email"));
		usuario.setAnexos((List<String>) entity.getProperty("anexos"));
		return usuario;
	}
}
