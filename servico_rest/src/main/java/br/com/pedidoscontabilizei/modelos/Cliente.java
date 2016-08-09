package br.com.pedidoscontabilizei.modelos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "clientes")
@XmlRootElement
public class Cliente {

	@Id
	@Column(length = 18)
	private String cpfCnpj;

	@Column(nullable = false, length = 80)
	private String nomRazaoSocial;

	@Column(nullable = false, length = 14)
	private String telefone;

	@Column(nullable = false, length = 80)
	private String email;

	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Pedido> pedidos;
	
	public Cliente() { }

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
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

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public void addPedido(Pedido pedido) {
		if (pedidos == null) {
			this.pedidos = new ArrayList<Pedido>();
		}

		if (pedido != null) {
			this.pedidos.add(pedido);
		}
	}
}
