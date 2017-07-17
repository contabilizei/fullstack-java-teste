package br.com.Negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.Util.CalendarioUtil;
import br.com.entidades.NotaFiscal;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

/**
 * Classe de negocio da nota fiscal
 * @author marinamontelo
 */
public class NotaFiscalNegocio extends NegocioGenerico<NotaFiscal>{

	
	@Override
	public String getNomeEntidade() {
		return "notaFiscal";
	}

	@Override
	public String getNomeTabela() {
		return "notasFiscais";
	}

	@Override
	public String atributoOrdenacaoPadrao() {
		return "nmrNotaFiscal";
	}

	@Override
	protected void entidadeGenericoToEntity(NotaFiscal entidade, Entity entity) {
		entity.setProperty("usuario", entidade.getUsuario());
		entity.setProperty("nmrNotaFiscal",entidade.getNmrNotaFiscal());
		entity.setProperty("dataEmissao",entidade.getDataEmissao());  
		entity.setProperty("descricao", entidade.getDescricao());
		entity.setProperty("valor", entidade.getValor());
		entity.setProperty("anexo", entidade.getAnexo());
		entity.setProperty("id", entidade.getId());
		
	}

	@Override
	protected NotaFiscal entityToEntidadeGenerico(Entity entity) {
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setGoogleId((entity.getKey().getId()));
		notaFiscal.setId((Long)entity.getProperty("id"));
		notaFiscal.setNmrNotaFiscal((String) entity.getProperty("nmrNotaFiscal"));
		notaFiscal.setDataEmissao((Date) entity.getProperty("dataEmissao"));
		notaFiscal.setDescricao((String) entity.getProperty("descricao"));
		notaFiscal.setValor((Double) entity.getProperty("valor"));
		notaFiscal.setAnexo((String) entity.getProperty("anexo"));
		notaFiscal.setUsuario((long) entity.getProperty("usuario"));
		return notaFiscal;
	}
	
	
	public List<NotaFiscal> listarPorMes(NotaFiscal notaFiscal) {
		List<NotaFiscal> retorno = new ArrayList<NotaFiscal>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter filtro1 = new FilterPredicate("dataEmissao",	FilterOperator.GREATER_THAN_OR_EQUAL, (CalendarioUtil.obterPrimeiroDiaDoMes(notaFiscal.getDataEmissao())));
		Filter filtro2 = new FilterPredicate("dataEmissao",	FilterOperator.LESS_THAN_OR_EQUAL,(CalendarioUtil.obterUltimoDiaDoMes(notaFiscal.getDataEmissao())));
		Filter filtro3 = new FilterPredicate("usuario", FilterOperator.EQUAL, notaFiscal.getUsuario());
		Filter filtroComposto = CompositeFilterOperator.and(filtro1, filtro2, filtro3);
		
		
		Query query = new Query(getNomeTabela()).setFilter(filtroComposto);
		List<Entity> listEntities = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
		for (Entity productEntity : listEntities) {
			NotaFiscal ent = entityToEntidadeGenerico(productEntity);
			retorno.add(ent);
		}
		validarListaRetorno(retorno);
		return retorno;
	}
}
