package br.com.entidades.Enums;

/**
 * Enum Tipo de regime tributario
 * @author marinamontelo
 *
 */
public enum ETipoRegimeTributario {

	SimplesNacional("Simples Nacional","Simples Nacional"),
	LucroPresumido("Lucro Presumido","Lucro Presumido");
	
	private ETipoRegimeTributario(String descricao, String codigo){
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	private String descricao;
	private String codigo;
	
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
