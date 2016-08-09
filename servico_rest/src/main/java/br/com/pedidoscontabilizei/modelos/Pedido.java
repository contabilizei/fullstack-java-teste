package br.com.pedidoscontabilizei.modelos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;	
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name="pedidos")
@XmlRootElement
public class Pedido {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long numero;
	
	@Column(nullable=false)
	@Temporal(TemporalType.DATE)
	private Calendar emissao;
	
	@Column(nullable=false, precision=18, scale=2, columnDefinition="DECIMAL(10,2)")
	private BigDecimal valorTotal;
	
	@ManyToOne(fetch=FetchType.EAGER)
	//@JsonBackReference
	private Cliente cliente;
	
	@ManyToMany(fetch=FetchType.EAGER)
	//@JsonManagedReference
	private List<Produto> produtos;
	
	public Pedido() { }

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Calendar getEmissao() {
		return emissao;
	}

	public void setEmissao(Calendar emissao) {
		this.emissao = emissao;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void addProduto(Produto produto) {
		if (this.produtos == null) {
			this.produtos = new ArrayList<Produto>();
		}
		
		if (produto != null) {
			this.produtos.add(produto);
		}				
	}
}
