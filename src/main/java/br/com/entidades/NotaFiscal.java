package br.com.entidades;

import java.util.Date;

/**
 * Classe Nota Fiscal
 * @author marinamontelo
 *
 */
/**
 * @author marinamontelo
 *
 */
public class NotaFiscal extends EntidadeGenerico {

	private Long usuario;
	private String nmrNotaFiscal;
	private Date dataEmissao;
	private Double valor;
	private String descricao;
	private String anexo;

	public String getNmrNotaFiscal() {
		return nmrNotaFiscal;
	}

	public void setNmrNotaFiscal(String nmrNotaFiscal) {
		this.nmrNotaFiscal = nmrNotaFiscal;
	}

	public Date getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAnexo() {
		return anexo;
	}

	public void setAnexo(String anexo) {
		this.anexo = anexo;
	}

	public long getUsuario() {
		return usuario;
	}

	public void setUsuario(long usuario) {
		this.usuario = usuario;
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
