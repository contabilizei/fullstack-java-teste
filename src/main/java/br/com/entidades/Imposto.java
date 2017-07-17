package br.com.entidades;

import java.util.Date;

/**
 * Classe Imposto
 * @author marinamontelo
 */
public class Imposto extends EntidadeGenerico {

	private Long usuario;
	private String tipoImposto;
	private Date vencimento;
	private Double valor;
	private Date mesAnoRef;
	private Boolean pgOuNao;

	public long getUsuario() {
		return usuario;
	}

	public void setUsuario(long usuario) {
		this.usuario = usuario;
	}

	public String getTipoImposto() {
		return tipoImposto;
	}

	public void setTipoImposto(String tipoImposto) {
		this.tipoImposto = tipoImposto;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getMesAnoRef() {
		return mesAnoRef;
	}

	public void setMesAnoRef(Date mesAnoRef) {
		this.mesAnoRef = mesAnoRef;
	}

	public Boolean getPgOuNao() {
		return pgOuNao;
	}

	public void setPgOuNao(Boolean pgOuNao) {
		this.pgOuNao = pgOuNao;
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