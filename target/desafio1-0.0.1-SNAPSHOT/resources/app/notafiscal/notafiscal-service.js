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
			   listarUsuarios: listarUsuarios
		    };
		    
		    /**
		     * Executa a listagem de todos os notafiscals salvos
		     */
		    function listarTudo() {
		    	return $http.post(url +'/api/notafiscal/listarTudo');
		    }
		    
		    function salvar(entity){
		    	return $http.post(url +'/api/notafiscal/salvar', entity);
		    }
		    function excluir(notafiscal){
		    	return $http.post(url +'/api/notafiscal/excluir', notafiscal);
		    }
		    function listarUsuarios(usuario){
		    	return $http.post(url +'/api/usuario/listarUsuariosModal', usuario);
		    }
       }
})();