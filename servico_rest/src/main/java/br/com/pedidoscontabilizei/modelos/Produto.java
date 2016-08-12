package br.com.pedidoscontabilizei.modelos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="produtos")
@XmlRootElement
public class Produto {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long codigo;
	
	@Column(nullable=false, length=100)
	private String descricao;
	
	@Column(nullable=false, precision=18, scale=2, columnDefinition="DECIMAL(10,2)")
	private BigDecimal quantidade;

	@Column(nullable=false, precision=18, scale=2, columnDefinition="DECIMAL(10,2)")
	private BigDecimal valorUnitario;
	
	@ManyToMany(mappedBy="produtos", fetch=FetchType.LAZY)
	private List<Pedido> pedidos;
	
	public Produto() {}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}	
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}
	
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void addPedido(Pedido pedido){
		if (this.pedidos == null) {
			this.pedidos = new ArrayList<Pedido>();
		}
		
		if (pedido != null) {
			this.pedidos.add(pedido);
		}
	}
}
