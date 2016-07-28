package com.luan.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.luan.models.Cliente;
import com.luan.models.Pedido;
import com.luan.models.Produto;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
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
		
		BasicDBObject produtos = new BasicDBObject();
		for(Produto produto : pedido.getProdutos()){
			BasicDBObject p = new BasicDBObject();
			p.put("codigo",produto.getCodigo());
			p.put("descricao",produto.getDescricao());
			p.put("quantidade",produto.getQuantidade());
			p.put("valor_unitario",produto.getValorUnitario());
			
			produtos.append("produto", p);
		}
		
		document.put("produtos", produtos);
		
		colecao.insert(document);
	}
	
	public Pedido buscarPorNumero(int Numero){
		
		BasicDBObject query = new BasicDBObject("numero", new BasicDBObject("$eq", Numero));
		Pedido pedido = new Pedido();
		
		Cursor cursor = colecao.find(query);
		try {
		    while (cursor.hasNext()) {
		    	DBObject o = new BasicDBObject();
		    	pedido.setNumero( Integer.parseInt((String) o.get("numero")));
		    	pedido.setValorTotal(Integer.parseInt((String) o.get("valor_total")));
		        
		    	String dataDeEmissao = (String) o.get("data_emissao");
		    	try{
		    		Date date = new SimpleDateFormat("y-m-d", Locale.ENGLISH).parse(dataDeEmissao);
		    		pedido.setDataDeEmissao(date);
		    	} catch ( ParseException pe) { }
		    	
		    	BasicDBObject c = (BasicDBObject) o.get("cliente");
		    	Cliente cliente = new Cliente();
		    	cliente.setCpf(c.getInt("cpf"));
		    	cliente.setNome(c.getString("nome"));
		    	cliente.setEmail(c.getString("email"));
		    	cliente.setTelefone(c.getInt("telefone"));
		    	pedido.setCliente(cliente);
		    	
		    	BasicDBObject[] ps = (BasicDBObject[]) o.get("produtos");
		    	for (BasicDBObject p : ps){
		    		//pegar produtos. produtos { [produto: {}}, {produto:{}} ]
		    	}
		    	
		    	//System.out.println(cursor.next());
		    }
		} finally {
		    cursor.close();
		}
		
		return pedido;
	}
	

}
