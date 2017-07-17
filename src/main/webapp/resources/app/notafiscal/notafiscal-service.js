(function() {
 'use strict';

angular
       .module('app') 
       .service('NotaFiscalService', NotaFiscalService); 
 
		NotaFiscalService.$inject = ['$http','$url']; //Lista de dependências
 
       function NotaFiscalService($http,$url) {
    	   	
    	   var url =  $url.url;
		   
		   return {
			   listarTudo : listarTudo,
			   salvar: salvar,
			   excluir: excluir,
			   listarUsuariosModal: listarUsuariosModal
		    };
		    
		    /**
		     * Executa a listagem de todos os notafiscais
		     */
		    function listarTudo() {
		    	return $http.post(url +'/api/notafiscal/listarTudo');
		    }
		    
		    /**
		     * Executa o metodo de salvar notas fiscais
		     */
		    function salvar(entity){
		    	return $http.post(url +'/api/notafiscal/salvar', entity);
		    }
		    /**
		     * Executa o metodo de delecao usuarios
		     */
		    function excluir(notafiscal){
		    	return $http.post(url +'/api/notafiscal/excluir', notafiscal);
		    }
		    /**
		     * Executa o metodo de listar usuarios no modal
		     */
		    function listarUsuariosModal(usuario){
		    	return $http.post(url +'/api/usuario/listarUsuariosModal', usuario);
		    }
       }
})();