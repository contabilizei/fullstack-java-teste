package br.com.Negocio;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import br.com.Util.ExceptionsResponseUtil;
import br.com.entidades.EntidadeGenerico;
import br.com.manager.IdManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;

/**
 * Classe de negocio generica
 * @author marinamontelo
 * @param <T>
 */

public abstract class NegocioGenerico <T> implements NegocioGenericoInterface{

	abstract protected void entidadeGenericoToEntity(T entidade, Entity entity) ;
	abstract protected T entityToEntidadeGenerico(Entity entity) ;
	
	public T salvar (T entidade){
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		if (!checkIfExist (entidade) ){
			Key entidadeKey = KeyFactory.createKey(getNomeEntidade(), getNomeEntidade()+"Key");
			Entity entity = new Entity(getNomeTabela(), entidadeKey);
			((EntidadeGenerico)entidade).setId(IdManager.getInstance().obterProximoId());
			entidadeGenericoToEntity(entidade, entity);
			datastore.put(entity);
			((EntidadeGenerico)entidade).setGoogleId(entity.getKey().getId());
		} else {
			throw ExceptionsResponseUtil.obterWebApplicationExceptionComResponse(getNomeEntidade()+" já existe ", Response.Status.BAD_REQUEST);
		}
		return  entidade;
	}

	public List<T> listar(String atributoOrdenacao) {
		List<T> retorno = new ArrayList<T>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query query;
		query = new Query(getNomeTabela()).addSort(atributoOrdenacao==null?atributoOrdenacaoPadrao():atributoOrdenacao,SortDirection.ASCENDING);
		List<Entity> listEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		for (Entity entity : listEntities) {
			T entidade = entityToEntidadeGenerico(entity);
			retorno.add(entidade);
		}
		return retorno;
	}
	
	public T deletar(T entidade) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter codeFilter = new FilterPredicate("id", FilterOperator.EQUAL, ((EntidadeGenerico)entidade).getId());
		Query query = new Query(getNomeTabela()).setFilter(codeFilter);
		Entity entity = datastore.prepare(query).asSingleEntity();
		if (entity != null) {
			datastore.delete(entity.getKey());
		} else {
			throw ExceptionsResponseUtil.obterWebApplicationExceptionComResponse("Não foi possivel excluir o(a) " +getNomeEntidade(), Response.Status.BAD_REQUEST);
		}
		return entidade;
	}
	
	public List<T> listarPorAtributo(String parametro, String atributo){
		List<T> retorno = new ArrayList<T>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter codeFilter = new FilterPredicate(atributo, FilterOperator.EQUAL, parametro);
		Query query = new Query(getNomeTabela()).setFilter(codeFilter);
		List<Entity> productsEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		for (Entity productEntity : productsEntities) {
			T ent = entityToEntidadeGenerico(productEntity);
			retorno.add(ent);
		}
		validarListaRetorno(retorno);
		return retorno;
	}
	
	
	public T obterPorId(Long id) {
		T retorno;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter codeFilter = new FilterPredicate("id", FilterOperator.EQUAL, id);
		Query query = new Query(getNomeTabela()).setFilter(codeFilter);
		Entity ent = datastore.prepare(query).asSingleEntity();
		retorno = entityToEntidadeGenerico(ent);
		validarRetorno(retorno);
		return retorno;
	}

	private boolean checkIfExist(T entidade) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter codeFilter = new FilterPredicate("id", FilterOperator.EQUAL, (((EntidadeGenerico)entidade)).getId());
		Query query = new Query(getNomeTabela()).setFilter(codeFilter);
		Entity ent = datastore.prepare(query).asSingleEntity();
		if (ent == null) {
			return false;
		} else {
			return true;
		}
	}
	
	
	protected void validarRetorno(T entidade){
		if(((EntidadeGenerico)entidade).getId()== null)
			throw ExceptionsResponseUtil.obterWebApplicationExceptionComResponse("Não encontrado registro de "+getNomeEntidade()+" para a pesquisa", Response.Status.BAD_REQUEST);
	}
	protected void validarListaRetorno(List<T> retorno){
		if(retorno.isEmpty())
			throw ExceptionsResponseUtil.obterWebApplicationExceptionComResponse("Não encontrado registros de "+getNomeEntidade()+" para a pesquisa", Response.Status.BAD_REQUEST);
	}
}
