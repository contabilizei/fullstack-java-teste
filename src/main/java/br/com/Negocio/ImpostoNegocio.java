package br.com.Negocio;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import br.com.Util.CalendarioUtil;
import br.com.entidades.EntidadeGenerico;
import br.com.entidades.Imposto;
import br.com.entidades.NotaFiscal;
import br.com.entidades.Usuario;
import br.com.entidades.Enums.ESimNao;
import br.com.entidades.Enums.ETipoImposto;
import br.com.entidades.Enums.ETipoRegimeTributario;

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
 * Classe de negocio do Imposto
 * @author marinamontelo
 */
public class ImpostoNegocio extends NegocioGenerico<Imposto> {

	@Override
	public String getNomeEntidade() {
		return "imposto";
	}

	@Override
	public String getNomeTabela() {
		return "impostos";
	}

	@Override
	public String atributoOrdenacaoPadrao() {
		return "valor";
	}

	@Override
	protected void entidadeGenericoToEntity(Imposto entidade, Entity entity) {
		entity.setProperty("id", entidade.getId());
		entity.setProperty("tipoImposto", entidade.getTipoImposto());
		entity.setProperty("vencimento", entidade.getVencimento());
		entity.setProperty("valor", entidade.getValor());
		entity.setProperty("mesAnoRef", entidade.getMesAnoRef());
		entity.setProperty("pgOuNao", entidade.getPgOuNao());
		entity.setProperty("usuario", entidade.getUsuario());
	}

	@Override
	protected Imposto entityToEntidadeGenerico(Entity entity) {
		Imposto imposto = new Imposto();
		imposto.setGoogleId(entity.getKey().getId());
		imposto.setId((Long) entity.getProperty("id"));
		imposto.setTipoImposto((String) entity.getProperty("tipoImposto"));
		imposto.setVencimento((Date) entity.getProperty("vencimento"));
		imposto.setMesAnoRef((Date) entity.getProperty("mesAnoRef"));
		imposto.setPgOuNao((Boolean) entity.getProperty("pgOuNao"));
		imposto.setUsuario((Long)entity.getProperty("usuario"));
		imposto.setValor((Double)entity.getProperty("valor"));
		return imposto;
	}

	public List<Imposto> listarPorMes(Imposto imposto) {

		List<Imposto> retorno = new ArrayList<Imposto>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter filtro1 = new FilterPredicate("mesAnoRef", FilterOperator.GREATER_THAN_OR_EQUAL,
		CalendarioUtil.obterPrimeiroDiaDoMes(imposto.getMesAnoRef()));
		Filter filtro2 = new FilterPredicate("mesAnoRef", FilterOperator.LESS_THAN_OR_EQUAL,
		CalendarioUtil.obterUltimoDiaDoMes(imposto.getMesAnoRef()));
		Filter filtro3 = new FilterPredicate("usuario", FilterOperator.EQUAL, imposto.getUsuario());
		Filter filtroComposto = CompositeFilterOperator.and(filtro1, filtro2, filtro3);

		Query query = new Query(getNomeTabela()).setFilter(filtroComposto);

		List<Entity> productsEntities = datastore.prepare(query).asList(
		FetchOptions.Builder.withDefaults());
		for (Entity productEntity : productsEntities) {
			Imposto ent = entityToEntidadeGenerico(productEntity);
			retorno.add(ent);
		}
		validarListaRetorno(retorno);
		return retorno;
	}

	public List<Imposto> gerarImposto(Imposto imposto) {

		NotaFiscalNegocio nfNeg = new NotaFiscalNegocio();
		UsuarioNegocio uNeg = new UsuarioNegocio();

		List<Imposto> retorno = new ArrayList<Imposto>();
		Usuario usuario;
		usuario = uNeg.obterPorId(imposto.getUsuario());

		NotaFiscal nfDadosPesquisa = new NotaFiscal();
		nfDadosPesquisa.setUsuario(imposto.getUsuario());
		nfDadosPesquisa.setDataEmissao(imposto.getMesAnoRef());
		List<NotaFiscal> notasFiscaisParaCalculo = new ArrayList<NotaFiscal>();
		notasFiscaisParaCalculo.addAll(nfNeg.listarPorMes(nfDadosPesquisa));

		if (usuario.getRegimeTributario().equals(
			ETipoRegimeTributario.SimplesNacional.getCodigo())) {
			BigDecimal valorTotalNotasAnexo1 = BigDecimal.ZERO;
			BigDecimal valorTotalNotasAnexo2 = BigDecimal.ZERO;
			BigDecimal valorTotalNotasAnexo3 = BigDecimal.ZERO;
			BigDecimal impostoTotal = BigDecimal.ZERO;
			for (NotaFiscal nf : notasFiscaisParaCalculo) {
				if (nf.getAnexo().equals(
					ETipoImposto.Comercio.getCodigoImposto())) {
					valorTotalNotasAnexo1 = valorTotalNotasAnexo1.add(new BigDecimal(nf.getValor()));
				} else if (nf.getAnexo().equals(ETipoImposto.Industria.getCodigoImposto())) {
					valorTotalNotasAnexo2 = valorTotalNotasAnexo2.add(new BigDecimal(nf.getValor()));
				} else if (nf.getAnexo().equals(ETipoImposto.Servico.getCodigoImposto())) {
					valorTotalNotasAnexo3 = valorTotalNotasAnexo3.add(new BigDecimal(nf.getValor()));
				}
			}
			impostoTotal = impostoTotal.add(valorTotalNotasAnexo1.multiply(ETipoImposto.Comercio.getTaxa()))
			.add(valorTotalNotasAnexo2.multiply(ETipoImposto.Industria.getTaxa()))
			.add(valorTotalNotasAnexo3.multiply(ETipoImposto.Servico.getTaxa()));
			//Monta imposto simples
			Imposto simples = new Imposto();
			simples.setValor(impostoTotal.doubleValue());
			simples.setUsuario(usuario.getId());
			simples.setMesAnoRef(CalendarioUtil.obterPrimeiroDiaDoMes(imposto.getMesAnoRef()));
			simples.setPgOuNao(ESimNao.NAO.getValor());
			simples.setTipoImposto(ETipoRegimeTributario.SimplesNacional.getDescricao());
			simples.setVencimento(CalendarioUtil.adicionarDiasAData(imposto.getMesAnoRef(), 30));
			retorno.add(salvar(simples));

		} else if (usuario.getRegimeTributario().equals(ETipoRegimeTributario.LucroPresumido.getCodigo())) {
			BigDecimal valorTotalDasNotas = BigDecimal.ZERO;
			for (NotaFiscal nf : notasFiscaisParaCalculo) {
				valorTotalDasNotas = valorTotalDasNotas.add(new BigDecimal(nf.getValor()));
			}
			Imposto irpj = new Imposto();
			Imposto iss = new Imposto();
			Imposto cofins = new Imposto();
			// monta irpj
			irpj.setValor(valorTotalDasNotas.multiply(ETipoImposto.IRPJ.getTaxa()).doubleValue());
			irpj.setUsuario(usuario.getId());
			irpj.setMesAnoRef(imposto.getMesAnoRef());
			irpj.setPgOuNao(ESimNao.NAO.getValor());
			irpj.setTipoImposto(ETipoImposto.IRPJ.getDescricao());
			irpj.setVencimento(CalendarioUtil.adicionarDiasAData(
					imposto.getMesAnoRef(), 30));
			// monta iss
			iss.setValor(valorTotalDasNotas.multiply(ETipoImposto.ISS.getTaxa()).doubleValue());
			iss.setUsuario(usuario.getId());
			iss.setMesAnoRef(imposto.getMesAnoRef());
			iss.setPgOuNao(ESimNao.NAO.getValor());
			iss.setTipoImposto(ETipoImposto.ISS.getDescricao());
			iss.setVencimento(CalendarioUtil.adicionarDiasAData(imposto.getMesAnoRef(), 30));
			// monta cofins
			cofins.setValor(valorTotalDasNotas.multiply(ETipoImposto.COFINS.getTaxa()).doubleValue());
			cofins.setUsuario(usuario.getId());
			cofins.setMesAnoRef(imposto.getMesAnoRef());
			cofins.setPgOuNao(ESimNao.NAO.getValor());
			cofins.setTipoImposto(ETipoImposto.COFINS.getDescricao());
			cofins.setVencimento(CalendarioUtil.adicionarDiasAData(imposto.getMesAnoRef(), 30));

			retorno.add(salvar(irpj));
			retorno.add(salvar(iss));
			retorno.add(salvar(cofins));
		} else {
			throw new WebApplicationException("Usuário sem regime tributário",Status.INTERNAL_SERVER_ERROR);
		}
		if (retorno.isEmpty()) {
			throw new WebApplicationException("Não foi possível calcular os Impostos",Status.INTERNAL_SERVER_ERROR);
		}
		return retorno;
	}

	public Imposto marcarPago(Imposto imposto) {
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Filter codeFilter = new FilterPredicate("id", FilterOperator.EQUAL, ((EntidadeGenerico)imposto).getId());
		Query query = new Query(getNomeTabela()).setFilter(codeFilter);
		Entity entity = datastore.prepare(query).asSingleEntity();
		Imposto retorno = entityToEntidadeGenerico(entity);
		retorno.setPgOuNao(ESimNao.SIM.getValor());
		entidadeGenericoToEntity(retorno, entity);
		datastore.put(entity);
		validarRetorno(retorno);
		return retorno;
	}

}
