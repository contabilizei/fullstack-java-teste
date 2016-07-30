package com.luan.services;

import java.util.List;

import com.google.gson.Gson;
import com.luan.dao.MongoDBPedidoDAO;
import com.luan.models.Pedido;

public class PedidoService {
	private MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
	
	public Pedido getByNumero(int numero) {
		return mdbpdao.buscarPorNumero(numero);
	}

	public List<Pedido> getAll() {
		return mdbpdao.listarTodos();
	}
	
	public String toJson(Object object){
		Gson gson = new Gson();
		return  gson.toJson(object);
	}
	
	public Pedido fromJson(String pedidoJson){
		Gson gson = new Gson();
		return gson.fromJson(pedidoJson, Pedido.class);
	}

	public void salvaPedido(Pedido pedido) {
		pedido.setNumero(getNumero());
		mdbpdao.inserir(pedido);
	}
	
	private int getNumero(){
		return (mdbpdao.listarTodos().size()+1);
	}

	public void remover(int numero) {
		mdbpdao.remover(numero);;
	}

}
