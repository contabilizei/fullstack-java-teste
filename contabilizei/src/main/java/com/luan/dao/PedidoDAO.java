package com.luan.dao;

import java.util.List;

import com.luan.models.Pedido;

public interface PedidoDAO {
	public void inserir(Pedido pedido);
    public Pedido buscarPorNumero(int Numero);
    public void remover(String codigo);
    public List<Pedido> listarTodos();
}
