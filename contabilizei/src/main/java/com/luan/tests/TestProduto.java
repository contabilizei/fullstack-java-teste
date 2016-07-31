package com.luan.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import com.luan.models.Produto;

public class TestProduto {

	@Test
	public void testCriacao() {
		Produto p = new Produto();
		p.setCodigo("ABC");
		p.setDescricao("DEF");
		p.setQuantidade(10);
		p.setValorUnitario(9);
		assertEquals("ABC", p.getCodigo());
		assertEquals("DEF", p.getDescricao());
		assertEquals(10, p.getQuantidade());
		assertEquals(9, p.getValorUnitario());
	}

}
