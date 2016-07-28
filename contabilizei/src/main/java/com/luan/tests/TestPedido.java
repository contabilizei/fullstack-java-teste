package com.luan.tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.*;
import org.junit.Test;

import com.luan.models.Cliente;
import com.luan.models.Pedido;
import com.luan.models.Produto;

public class TestPedido {

	@Test
	public void testCriacao() throws ParseException {
		Produto produto = new Produto();
		produto.setCodigo("ABC");
		produto.setDescricao("DEF");
		produto.setQuantidade(10);
		produto.setValorUnitario(9);
		
		Cliente cliente = new Cliente();
		cliente.setCpf(1234567890);
		cliente.setEmail("eu@email.com");
		cliente.setNome("Joao");
		cliente.setTelefone(33333333);
		
		String dataDeEmissao = "2017-07-21";
		Date date = new SimpleDateFormat("y-m-d", Locale.ENGLISH).parse(dataDeEmissao);
		
		Pedido pedido = new Pedido();
		pedido.setNumero(1);
		pedido.setDataDeEmissao(date);
		pedido.addProduto(produto);
		pedido.setValorTotal(100);
		pedido.setCliente(cliente);
		
		assertEquals(1, pedido.getNumero());
		assertEquals(date, pedido.getDataDeEmissao());
		assertEquals(cliente, pedido.getCliente());
		assertEquals(100, pedido.getValorTotal());
		assertEquals("ABC", pedido.getProduto(produto).getCodigo());
	}

}
