package com.luan.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.luan.models.Cliente;
import com.luan.models.Pedido;
import com.luan.models.Produto;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBPedidoDAO {
	
	private MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
	private DB db = mongoClient.getDB("contabilizei");
	private DBCollection colecao = db.getCollection("pedidos");
	
	public void inserir(Pedido pedido){
		
		BasicDBObject document = new BasicDBObject();
		document.put("numero", pedido.getNumero());
		document.put("data_de_emissao", pedido.getDataDeEmissao());
		document.put("valor_total", pedido.getValorTotal());
		
		BasicDBObject cliente = new BasicDBObject();
		cliente.put("cpf", pedido.getCliente().getCpf());
		cliente.put("nome", pedido.getCliente().getNome());
		cliente.put("email", pedido.getCliente().getEmail());
		cliente.put("telefone", pedido.getCliente().getTelefone());
		document.put("cliente", cliente);
		
		BasicDBList produtos = new BasicDBList();
		for(Produto produto : pedido.getProdutos()){
			BasicDBObject p = new BasicDBObject();
			p.put("codigo",produto.getCodigo());
			p.put("descricao",produto.getDescricao());
			p.put("quantidade",produto.getQuantidade());
			p.put("valor_unitario",produto.getValorUnitario());
			
			produtos.add(p);
		}
		
		document.put("produtos", produtos);
		
		colecao.insert(document);
	}
	
	public Pedido buscarPorNumero(int Numero){
		
		BasicDBObject query = new BasicDBObject("numero", new BasicDBObject("$eq", Numero));
		Pedido pedido = new Pedido();
		
		Cursor cursor = colecao.find(query);
		
		pedido = getPedido((BasicDBObject) cursor.next());
		cursor.close();
		
		return pedido;
	}

	private Pedido getPedido(BasicDBObject obj) {
		Pedido pedido = new Pedido();
		
		pedido.setNumero( obj.getInt("numero"));
		pedido.setValorTotal( obj.getInt("valor_total"));
		
		Date dataDeEmissao = obj.getDate("data_de_emissao");
		
		pedido.setDataDeEmissao(dataDeEmissao);
		BasicDBObject c = (BasicDBObject) obj.get("cliente");
		Cliente cliente = new Cliente();
		cliente.setCpf(c.getLong("cpf"));
		cliente.setNome(c.getString("nome"));
		cliente.setEmail(c.getString("email"));
		cliente.setTelefone(c.getLong("telefone"));
		pedido.setCliente(cliente);
		
		BasicDBList produtosObj = (BasicDBList) obj.get("produtos");
		BasicDBObject[] ps = produtosObj.toArray(new BasicDBObject[0]);
		List<Produto> produtos = new ArrayList<Produto>();
		for (BasicDBObject produtoObj : ps){
			Produto produto = new Produto();
			produto.setCodigo(produtoObj.getString("codigo"));
			produto.setDescricao(produtoObj.getString("descricao"));
			produto.setQuantidade(produtoObj.getInt("quantidade"));
			produto.setValorUnitario(produtoObj.getInt("valor_unitario"));
			
			produtos.add(produto);
		}
		
		pedido.setProdutos(produtos);
		
		return pedido;
	}
	
	public void remover(int numero){
		BasicDBObject query = new BasicDBObject();
		query.put("numero", new BasicDBObject("$eq", numero));
		colecao.remove(query);
	}
	
	public List<Pedido> listarTodos(){
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		Cursor cursor = colecao.find();
		
		try {
		    while (cursor.hasNext()) {
		    	pedidos.add(getPedido((BasicDBObject) cursor.next()));
		    }
		} finally {
		    cursor.close();
		}
		
		return pedidos;
		
	}
	

}
