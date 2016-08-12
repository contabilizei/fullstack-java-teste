package br.com.pedidoscontabilizei.dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClienteDTO {
	
	private String documentoCliente;
	private String nomRazaoSocial;
	private String telefone;
	private String email;

	public ClienteDTO() {
	}

	public String getDocumentoCliente() {
		return documentoCliente;
	}

	public void setDocumentoCliente(String cpfCnpj) {
		this.documentoCliente = cpfCnpj;
	}

	public String getNomRazaoSocial() {
		return nomRazaoSocial;
	}

	public void setNomRazaoSocial(String nomRazaoSocial) {
		this.nomRazaoSocial = nomRazaoSocial;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
