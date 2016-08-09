package br.com.pedidoscontabilizei.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.pedidoscontabilizei.dao.EMF;
import br.com.pedidoscontabilizei.dao.ProdutoDao;
import br.com.pedidoscontabilizei.dto.ProdutoDTO;
import br.com.pedidoscontabilizei.modelos.Produto;

public class ProdutoServico {
	
	private ProdutoDao produtoDao;
	
	public ProdutoServico() {
		EMF emf = new EMF();
		produtoDao = new ProdutoDao(emf.createEntityManager());
	}

	public void inserir(ProdutoDTO produtoDTO) {
		Produto produto = converteParaModelo(produtoDTO);
		
		produtoDao.inserir(produto);
	}
	
	public void alterar(ProdutoDTO produtoDTO) {				
		Produto produto = converteParaModelo(produtoDTO);
		
		produtoDao.alterar(produto);
	}
	
	public List<ProdutoDTO> buscaTodosProdutos() {
		List<Produto> produtos = produtoDao.buscaTodos();
		List<ProdutoDTO> produtosDTO = new ArrayList<ProdutoDTO>();
		
		for (Produto produto : produtos) {
			produtosDTO.add(converteParaDTO(produto));
		}
		
		return produtosDTO;
	}
	
	public ProdutoDTO buscaProdutoPorCodigo(Long codigo) {		
		Produto produto = produtoDao.buscaPeloId(codigo);
		if (produto != null) {
			return converteParaDTO(produto);
		}
		
		return null;
	}	
	
	public boolean remover(Long codigo) {
		Produto produto = produtoDao.buscaPeloId(codigo);
		
		if (produto == null) {
			return false;
		}
		
		produtoDao.deletar(produto);
		
		return true;
	}

	private ProdutoDTO converteParaDTO(Produto produto) {
		ProdutoDTO dto = new ProdutoDTO();
		dto.setCodigo(produto.getCodigo());
		dto.setDescricao(produto.getDescricao());
		dto.setQuantidade(produto.getQuantidade());
		dto.setValorUnitario(produto.getValorUnitario());
		
		return dto;
	}

	private Produto converteParaModelo(ProdutoDTO produtoDTO) {
		Produto produto = new Produto();
		produto.setCodigo(produtoDTO.getCodigo());
		produto.setDescricao(produtoDTO.getDescricao());
		produto.setQuantidade(produtoDTO.getQuantidade());
		produto.setValorUnitario(produtoDTO.getValorUnitario());
		
		return produto;
	}
}
