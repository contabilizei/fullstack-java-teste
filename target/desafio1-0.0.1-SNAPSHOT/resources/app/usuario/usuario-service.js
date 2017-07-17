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
		     * Executa a listagem de todos os usuarios salvos
		     */
		    function listarTudo() {
		    	return $http.post(url +'/api/usuario/listarTudo');
		    }
		    
		    function salvar(entity){
		    	return $http.post(url +'/api/usuario/salvar', entity);
		    }
		    function excluir(usuario){
		    	return $http.post(url +'/api/usuario/excluir', usuario);
		    }
//		    :((((((((((((((((((

//		    /**
//		     * Pesquisa usuarios por nome
//		     */
//		    function pesquisarPorNome(string){
//		    	if(string == undefined){
//					string =' ';
//				}
//		    	return $http.post(url +'/usuario/pesquisar', string);
//		    }
//		    
//		    /**
//		     * Service responsavel pela exclusão da entidade de Usuario
//		     */

       }
})();