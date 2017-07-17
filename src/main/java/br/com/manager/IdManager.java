package br.com.manager;

/**
 * Manager para gerar id no tempo de execucao
 * @author marinamontelo
 *
 */
public class IdManager {

	private static IdManager idManager;
	private Long id;
	
	private IdManager() {
	}
		
	public static IdManager getInstance(){
		if(idManager == null){
			idManager = new IdManager();
		}
		return idManager;
	
	}
	
	public Long obterProximoId(){
		if(id==null){
			id = new Long(0);
		}
		this.id++;
		return this.id;
	}
}
