package com.luan.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.luan.dao.MongoDBPedidoDAO;
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
		assertEquals("ABC", pedido.getProdutoByCodigo("ABC").getCodigo());
	}
	/*
	@Test
	public void testSalvarPedido(){
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
		
		Pedido pedido = new Pedido();
		String dataDeEmissao = "2017-07-21";
		try{
			Date date = new SimpleDateFormat("y-m-d", Locale.ENGLISH).parse(dataDeEmissao);
			pedido.setDataDeEmissao(date);
		} catch (ParseException pe) {}
		pedido.setNumero(1);
		pedido.addProduto(produto);
		pedido.setValorTotal(100);
		pedido.setCliente(cliente);
		
		MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
		mdbpdao.inserir(pedido);
		
	}
	@Test
	public void testSalvarPedidoComDoisProdutos(){
		Produto produto = new Produto();
		produto.setCodigo("ABC");
		produto.setDescricao("DEF");
		produto.setQuantidade(10);
		produto.setValorUnitario(9);
		
		Produto produto2 = new Produto();
		produto2.setCodigo("ABCDEF");
		produto2.setDescricao("GHI");
		produto2.setQuantidade(19);
		produto2.setValorUnitario(7);
		
		
		Cliente cliente = new Cliente();
		cliente.setCpf(1234567890);
		cliente.setEmail("eu@email.com");
		cliente.setNome("Joao");
		cliente.setTelefone(33333333);
		
		Pedido pedido = new Pedido();
		String dataDeEmissao = "2017-07-21";
		try{
			Date date = new SimpleDateFormat("y-m-d", Locale.ENGLISH).parse(dataDeEmissao);
			pedido.setDataDeEmissao(date);
		} catch (ParseException pe) {}
		pedido.setNumero(2);
		pedido.addProduto(produto);
		pedido.addProduto(produto2);
		
		pedido.setValorTotal(100);
		pedido.setCliente(cliente);
		
		MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
		mdbpdao.inserir(pedido);
		
	}*/
	
	@Test
	public void testRecuperarPedido(){
		Produto produto = new Produto();
		produto.setCodigo("ABC");
		produto.setDescricao("DEF");
		produto.setQuantidade(10);
		produto.setValorUnitario(9);
		
		Produto produto2 = new Produto();
		produto2.setCodigo("ABCDEF");
		produto2.setDescricao("GHI");
		produto2.setQuantidade(19);
		produto2.setValorUnitario(7);
		
		
		Cliente cliente = new Cliente();
		cliente.setCpf(1234567890);
		cliente.setEmail("eu@email.com");
		cliente.setNome("Joao");
		cliente.setTelefone(33333333);
		
		Pedido pedido = new Pedido();
		String dataDeEmissao = "2017-07-21";
		try{
			Date date = new SimpleDateFormat("y-m-d", Locale.ENGLISH).parse(dataDeEmissao);
			pedido.setDataDeEmissao(date);
		} catch (ParseException pe) {}
		pedido.setNumero(2);
		pedido.addProduto(produto);
		pedido.addProduto(produto2);
		
		pedido.setValorTotal(100);
		pedido.setCliente(cliente);
		
		MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
		Pedido recuperado = mdbpdao.buscarPorNumero(2);
		
		assertEquals(pedido.getNumero(),recuperado.getNumero());
		assertEquals(cliente.getCpf(),recuperado.getCliente().getCpf());
		assertEquals(produto.getCodigo(),recuperado.getProdutos().get(0).getCodigo());
		assertEquals(produto2.getCodigo(),recuperado.getProdutos().get(1).getCodigo());
		
	}

}
