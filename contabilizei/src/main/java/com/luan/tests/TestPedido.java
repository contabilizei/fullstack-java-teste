package com.luan.tests;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

import com.luan.dao.MongoDBPedidoDAO;
import com.luan.models.Cliente;
import com.luan.models.Pedido;
import com.luan.models.Produto;

public class TestPedido {
	Produto produto, produto2;
	Pedido pedido, pedido2;
	Cliente cliente;
	Date date;
	
	@Before
	public void inicializaPedidos()  throws ParseException{
		produto = new Produto();
		produto.setCodigo("ABC");
		produto.setDescricao("DEF");
		produto.setQuantidade(10);
		produto.setValorUnitario(9);
		
		cliente = new Cliente();
		cliente.setCpf(1234567890);
		cliente.setEmail("eu@email.com");
		cliente.setNome("Joao");
		cliente.setTelefone(33333333);
		
		String dataDeEmissao = "2017-07-21";
		date = new SimpleDateFormat("y-m-d", Locale.ENGLISH).parse(dataDeEmissao);
		
		pedido = new Pedido();
		pedido.setNumero(1);
		pedido.setDataDeEmissao(date);
		pedido.addProduto(produto);
		pedido.setValorTotal(100);
		pedido.setCliente(cliente);
		
		produto2 = new Produto();
		produto2.setCodigo("ABCDEF");
		produto2.setDescricao("GHI");
		produto2.setQuantidade(19);
		produto2.setValorUnitario(7);
		
		pedido2 = new Pedido();
		try{
			Date date = new SimpleDateFormat("y-m-d", Locale.ENGLISH).parse(dataDeEmissao);
			pedido.setDataDeEmissao(date);
		} catch (ParseException pe) {}
		pedido2.setNumero(2);
		pedido2.addProduto(produto);
		pedido2.addProduto(produto2);
		
		pedido2.setValorTotal(100);
		pedido2.setCliente(cliente);
	
	
	}
	
	@Test
	public void testCriacao() {
		assertEquals(1, pedido.getNumero());
		assertEquals(date, pedido.getDataDeEmissao());
		assertEquals(cliente, pedido.getCliente());
		assertEquals(100, pedido.getValorTotal());
		assertEquals("ABC", pedido.getProdutoByCodigo("ABC").getCodigo());
	}
	
	@Test
	public void testSalvarPedido(){
		MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
		mdbpdao.inserir(pedido);
	}
	
	@Test
	public void testSalvarPedidoComDoisProdutos(){
		MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
		mdbpdao.inserir(pedido2);
	}
	
	@Test
	public void testRecuperarPedido(){
		
		MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
		Pedido recuperado = mdbpdao.buscarPorNumero(2);
		
		assertEquals(pedido2.getNumero(),recuperado.getNumero());
		assertEquals(cliente.getCpf(),recuperado.getCliente().getCpf());
		assertEquals(produto.getCodigo(),recuperado.getProdutos().get(0).getCodigo());
		assertEquals(produto2.getCodigo(),recuperado.getProdutos().get(1).getCodigo());
		
	}
	
	@Test
	public void testRemoverPedido(){
		MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
		mdbpdao.remover(1);
	}
	
	@Test
	public void testListarPedidos(){
		MongoDBPedidoDAO mdbpdao = new MongoDBPedidoDAO();
		List<Pedido> pedidos = mdbpdao.listarTodos();
		
		assertEquals(pedido2.getNumero(),pedidos.get(0).getNumero());
	}

}
