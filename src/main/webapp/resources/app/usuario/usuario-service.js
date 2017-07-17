(function() {
 'use strict';

angular
       .module('app') 
       .service('UsuarioService', UsuarioService); 
 
		UsuarioService.$inject = ['$http','$url']; //Lista de dependências
 
       function UsuarioService($http,$url) {
    	   	
    	   var url =  $url.url;
		   
		   return {
			   listarTudo : listarTudo,
			   salvar: salvar,
			   excluir: excluir
		    };
		    
		    /**
		     * Executa a listagem de todos os usuarios
		     */
		    function listarTudo() {
		    	return $http.post(url +'/api/usuario/listarTudo');
		    }
		    
		    /**
		     * Executa o metodo de salvar usuarios
		     */
		    function salvar(entity){
		    	return $http.post(url +'/api/usuario/salvar', entity);
		    }
		    /**
		     * Executa o metodo de delecao usuarios
		     */
		    function excluir(usuario){
		    	return $http.post(url +'/api/usuario/excluir', usuario);
		    }
       }
})();