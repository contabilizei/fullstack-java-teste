package br.com.entidades.Enums;

import java.math.BigDecimal;

/**
 * Enum Tipo Imposto
 * @author marinamontelo
 */
public enum ETipoImposto {
	
	Comercio("1 - Comércio","Comercio",new BigDecimal (0.06)),
	Industria("2 - Indústria","Industria",new BigDecimal (0.085)),
	Servico("2 - Prestação de Serviços","Prestacao de servicos",new BigDecimal (0.11)),
	IRPJ("2 - IRPJ","4",new BigDecimal (0.048)),
	ISS("2 - ISS","5",new BigDecimal (0.02)),
	COFINS("2 - COFINS","6",new BigDecimal (0.03));
	
	private String descricao;
	private String codigoImposto;
	private BigDecimal taxa;

	
	private ETipoImposto(String descricao, String codigoImposto, BigDecimal taxa) {
		this.descricao = descricao;
		this.codigoImposto = codigoImposto;
		this.taxa = taxa;
	}
	public String getDescricao() {
		return descricao;
	}
	public String getCodigoImposto() {
		return codigoImposto;
	}
	public BigDecimal getTaxa() {
		return taxa;
	}
	
	public static ETipoImposto getEnumPorCodigoImposto(String codigoImposto){
		for( ETipoImposto e:ETipoImposto.values()){
			if (e.getCodigoImposto().equals(codigoImposto)){
				return e;
			}
		}
		return null;
	}
}
