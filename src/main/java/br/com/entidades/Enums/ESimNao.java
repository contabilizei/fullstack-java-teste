package br.com.entidades.Enums;

/**
 *  Enum / sim e não
 * @author marinamontelo
 */
public enum ESimNao {
	SIM("Sim",true),NAO("Não",false);
	
	private ESimNao(String descricao,boolean valor){
		this.descricao = descricao;
		this.valor = valor;
	}
	
	private String descricao;
	private boolean valor;
	
	public String getDescricao() {
		return descricao;
	}
	public boolean getValor() {
		return valor;
	}
	
	
}
