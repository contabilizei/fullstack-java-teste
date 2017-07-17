package br.com.entidades;

import java.util.List;

/**
 * Classe Usuario
 * @author marinamontelo
 */
public class Usuario extends EntidadeGenerico {

	private String razaoSocial;
	private String regimeTributario;
	private String email;
	private List<String> anexos;

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getRegimeTributario() {
		return regimeTributario;
	}

	public void setRegimeTributario(String regimeTributario) {
		this.regimeTributario = regimeTributario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAnexos() {
		return anexos;
	}

	public void setAnexos(List<String> anexos) {
		this.anexos = anexos;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setGoogleId(long googleId) {
		this.googleId = googleId;
	}

	public long getGoogleId() {
		return this.googleId;
	}

}
