package br.com.pedidoscontabilizei.servicos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.pedidoscontabilizei.dao.PedidoDao;
import br.com.pedidoscontabilizei.dao.ProdutoDao;
import br.com.pedidoscontabilizei.dao.ClienteDao;
import br.com.pedidoscontabilizei.dao.EMF;
import br.com.pedidoscontabilizei.dto.PedidoDTO;
import br.com.pedidoscontabilizei.modelos.Cliente;
import br.com.pedidoscontabilizei.modelos.Pedido;
import br.com.pedidoscontabilizei.modelos.Produto;

public class PedidoServico {
	private PedidoDao pedidoDao;
	private ClienteDao clienteDao;
	private ProdutoDao produtoDao;

	public PedidoServico() {
		EMF emf = new EMF();
		pedidoDao = new PedidoDao(emf.createEntityManager());
		clienteDao = new ClienteDao(emf.createEntityManager());
		produtoDao = new ProdutoDao(emf.createEntityManager());
	}

	public void inserir(PedidoDTO pedidoDTO) {
		Pedido pedido = converteParaModelo(pedidoDTO);
		Cliente cliente = pedido.getCliente();
		cliente.addPedido(pedido);
		
		for (Produto produto : pedido.getProdutos()) {
			produto.addPedido(pedido);			
		}
		
		pedidoDao.inserir(pedido);
	}
	
	public void alterar(PedidoDTO pedidoDTO) {				
		Pedido pedido = converteParaModelo(pedidoDTO);
		
		pedidoDao.alterar(pedido);
	}
	
	public List<PedidoDTO> buscaTodosPedidos() {
		List<Pedido> pedidos = pedidoDao.buscaTodos();
		List<PedidoDTO> pedidosDTO = new ArrayList<PedidoDTO>();
		
		for (Pedido pedido : pedidos) {
			pedidosDTO.add(converteParaDTO(pedido));
		}
		
		return pedidosDTO;
	}
	
	public PedidoDTO buscaPedidoPorDocumento(Long numero) {		
		Pedido pedido = pedidoDao.buscaPeloId(numero);
		if (pedido != null) {
			return converteParaDTO(pedido);
		}
		
		return null;
	}	
	
	public boolean remover(Long numero) {
		Pedido pedido = pedidoDao.buscaPeloId(numero);
		
		if (pedido == null) {
			return false;
		}
		
		pedidoDao.deletar(pedido);
		
		return true;
	}
		

	private PedidoDTO converteParaDTO(Pedido pedido) {		
		PedidoDTO dto = new PedidoDTO();
		dto.setNumero(pedido.getNumero());
		dto.setNomeCliente(pedido.getCliente().getNomRazaoSocial());
		dto.setDocumentoCliente(pedido.getCliente().getCpfCnpj());
		dto.setValorTotal(pedido.getValorTotal());
		dto.setEmissao(pedido.getEmissao());
		
		for (Produto produto : pedido.getProdutos()) {
			if (produto != null) {
				dto.addDescricaoProduto(produto.getDescricao());
				dto.addCodigoProduto(produto.getCodigo());
				dto.addValorUnitarioProduto(produto.getValorUnitario());
			}
		}		
		
		return dto;
	}

	private Pedido converteParaModelo(PedidoDTO pedidoDTO) {

		Pedido pedido = new Pedido();
		pedido.setEmissao(Calendar.getInstance());
		pedido.setNumero(pedidoDTO.getNumero());
		pedido.setValorTotal(pedidoDTO.getValorTotal());
		pedido.setCliente(clienteDao.buscaPeloId(pedidoDTO.getDocumentoCliente()));

		for (Long codigo : pedidoDTO.getCodigoProdutos()) {
			if (codigo != null) {
				pedido.addProduto(produtoDao.buscaPeloId(codigo));
			}
		}		
		
		return pedido;
	}

}
