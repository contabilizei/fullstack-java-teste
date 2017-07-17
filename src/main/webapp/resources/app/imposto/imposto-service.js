(function() {
 'use strict';

angular
       .module('app') 
       .service('ImpostoService', ImpostoService); 
 
		ImpostoService.$inject = ['$http','$url']; //Lista de dependências
 
       function ImpostoService($http,$url) {
    	   	
    	   var url =  $url.url;
		   
		   return {
			   listarTudo : listarTudo,
			   salvar: salvar,
			   excluir: excluir,
			   listarUsuarios: listarUsuarios,
			   gerarImposto: gerarImposto,
			   marcarPago: marcarPago,
			   pesquisarImposto: pesquisarImposto
		    };
		    
		    /**
		     * Executa a listagem de todos os impostos
		     */
		    function listarTudo() {
		    	return $http.post(url +'/api/imposto/listarTudo');
		    }
		    
		    /**
		     * Executa o metodo de salvar impostos
		     */
		    function salvar(entity){
		    	return $http.post(url +'/api/imposto/salvar', entity);
		    }
		    
		    /**
		     * Executa o metodo de delecao impostos
		     */
		    function excluir(entity){
		    	return $http.post(url +'/api/imposto/excluir', entity);
		    }
		    /**
		     * Gerar impostos  
		     */
		    function gerarImposto(entity){
		    	return $http.post(url +'/api/imposto/gerarImposto', entity);
		    }
		    /**
		     * Gerar impostos  
		     */
		    function marcarPago(entity){
		    	return $http.post(url +'/api/imposto/marcarPago', entity);
		    }
		    /**
		     * Executa o metodo de listar usuarios no modal
		     */
		    function listarUsuarios(usuario){
		    	return $http.post(url +'/api/usuario/listarUsuariosModal', usuario);
		    }
		    
		    /**
		     * Executa o metodo de listar usuarios no modal
		     */
		    function pesquisarImposto(entity){
		    	return $http.post(url +'/api/imposto/pesquisarImposto', entity);
		    }
       }
})();