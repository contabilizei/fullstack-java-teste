package br.com.pedidoscontabilizei.servicos;

import java.util.ArrayList;
import java.util.List;

import br.com.pedidoscontabilizei.dao.ClienteDao;
import br.com.pedidoscontabilizei.dao.EMF;
import br.com.pedidoscontabilizei.dto.ClienteDTO;
import br.com.pedidoscontabilizei.modelos.Cliente;

public class ClienteServico {
	private ClienteDao clienteDao;

	public ClienteServico() {
		EMF emf = new EMF();
		clienteDao = new ClienteDao(emf.createEntityManager());
	}

	public void inserir(ClienteDTO clienteDTO) {
		Cliente cliente = converteParaModelo(clienteDTO);
		
		clienteDao.inserir(cliente);
	}
	
	public void alterar(ClienteDTO clienteDTO) {				
		Cliente cliente = converteParaModelo(clienteDTO);
		
		clienteDao.alterar(cliente);
	}
	
	public List<ClienteDTO> buscaTodosClientes() {
		List<Cliente> clientes = clienteDao.buscaTodos();
		List<ClienteDTO> clientesDTO = new ArrayList<ClienteDTO>();
		
		for (Cliente cliente : clientes) {
			clientesDTO.add(converteParaDTO(cliente));
		}
		
		return clientesDTO;
	}
	
	public ClienteDTO buscaClientePorDocumento(String cpf) {		
		Cliente cliente = clienteDao.buscaPeloId(cpf);
		if (cliente != null) {
			return converteParaDTO(cliente);
		}
		
		return null;
	}	
	
	public boolean remover(String cpf) {
		Cliente cliente = clienteDao.buscaPeloId(cpf);
		
		if (cliente == null) {
			return false;
		}
		
		clienteDao.deletar(cliente);
		
		return true;
	}

	private ClienteDTO converteParaDTO(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		dto.setCpfCnpj(cliente.getCpfCnpj());
		dto.setNomRazaoSocial(cliente.getNomRazaoSocial());
		dto.setEmail(cliente.getEmail());
		dto.setTelefone(cliente.getTelefone());
		
		return dto;
	}

	private Cliente converteParaModelo(ClienteDTO clienteDTO) {
		Cliente cliente = new Cliente();
		cliente.setCpfCnpj(clienteDTO.getCpfCnpj());
		cliente.setNomRazaoSocial(clienteDTO.getNomRazaoSocial());
		cliente.setEmail(clienteDTO.getEmail());
		cliente.setTelefone(clienteDTO.getTelefone());
		
		return cliente;
	}
}
