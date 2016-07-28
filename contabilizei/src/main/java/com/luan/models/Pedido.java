package com.luan.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {
	
	private int numero;
	private Date dataDeEmissao;
	private int valorTotal;
	private List<Produto>  produtos = new ArrayList<Produto>();
	private Cliente cliente;
	
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getDataDeEmissao() {
		return dataDeEmissao;
	}
	public void setDataDeEmissao(Date dataDeEmissao) {
		this.dataDeEmissao = dataDeEmissao;
	}
	public int getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(int valorTotal) {
		this.valorTotal = valorTotal;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public void addProduto(Produto produto) {
		produtos.add(produto);
	}
	
	public Produto getProduto(Produto produto){
		for (Produto p : produtos)
			if (p.getCodigo() == produto.getCodigo())
				return p;
		return null;
	}
	
}
