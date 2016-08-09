package br.com.pedidoscontabilizei.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PedidoDTO {
	
	private Long numero;
	private String documentoCliente;
	private String nomeCliente;	
	private List<String> descricaoProdutos;
	private List<Long> codigoProdutos;
	private List<BigDecimal> valorUnitarioProdutos;
	private BigDecimal valorTotal;
	private Calendar emissao;
	
	private List<Long> produtos;

	public PedidoDTO() { }

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public String getDocumentoCliente() {
		return documentoCliente;
	}

	public void setDocumentoCliente(String documentoCliente) {
		this.documentoCliente = documentoCliente;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public List<String> getDescricaoProdutos() {
		return descricaoProdutos;
	}

	public void setDescricaoProdutos(List<String> descricaoProdutos) {
		this.descricaoProdutos = descricaoProdutos;
	}

	public List<Long> getCodigoProdutos() {
		return codigoProdutos;
	}

	public void setCodigoProdutos(List<Long> codigoProdutos) {
		this.codigoProdutos = codigoProdutos;
	}
	
	public List<BigDecimal> getValorUnitarioProdutos() {
		return valorUnitarioProdutos;
	}
	
	public void setValorUnitarioProdutos(List<BigDecimal> valorUnitarioProdutos) {
		this.valorUnitarioProdutos = valorUnitarioProdutos;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}

	public List<Long> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Long> produtos) {
		this.produtos = produtos;
	}
	
	public Calendar getEmissao() {
		return emissao;
	}
	
	public void setEmissao(Calendar emissao) {
		this.emissao = emissao;
	}
	
	public void addDescricaoProduto(String descricao) {
		if (this.descricaoProdutos == null) {
			this.descricaoProdutos = new ArrayList<String>();
		}
		
		if (descricao != null || descricao.trim().length() > 0) {
			this.descricaoProdutos.add(descricao);
		}
	}
	
	public void addCodigoProduto(Long codigoProduto) {
		if (this.codigoProdutos == null) {
			this.codigoProdutos = new ArrayList<Long>();
		}
		
		if (codigoProduto > 0) {
			this.codigoProdutos.add(codigoProduto);
		}
	}
	
	public void addValorUnitarioProduto(BigDecimal valor) {
		if (this.valorUnitarioProdutos == null) {
			this.valorUnitarioProdutos = new ArrayList<BigDecimal>();
		}
		
		if (valor.compareTo(BigDecimal.ZERO) > 0) {
			this.valorUnitarioProdutos.add(valor);
		}
	}
}
