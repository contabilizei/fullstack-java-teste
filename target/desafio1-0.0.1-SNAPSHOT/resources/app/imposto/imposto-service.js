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
			   listarUsuarios: listarUsuarios
		    };
		    
		    /**
		     * Executa a listagem de todos os usuarios salvos
		     */
		    function listarTudo() {
		    	return $http.post(url +'/api/imposto/listarTudo');
		    }
		    
		    function salvar(entity){
		    	return $http.post(url +'/api/imposto/salvar', entity);
		    }
		    function excluir(usuario){
		    	return $http.post(url +'/api/imposto/excluir', usuario);
		    }
		    
		    function listarUsuarios(emailUsuario){
		    	return $http.post(url +'/api/usuario/listarUsuariosModal', emailUsuario);
		    }

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