package com.luan.services;

import com.luan.dao.MongoDBPedidoDAO;
import com.luan.models.Pedido;

public class PedidoService {

	public Pedido getByNumero(int numero) {
		MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
		return mdbpdao.buscarPorNumero(numero);
	}

}
