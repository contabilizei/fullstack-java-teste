(function() {
    'use strict';
	
angular.module('app').controller('UsuarioController', UsuarioController);

UsuarioController.$inject = ['$scope','$http','MessageService','$url','$uibModal','UsuarioService' ,'$state','ModalService'];

function UsuarioController($scope,$http,MessageService, $url, $uibModal,UsuarioService,$state,ModalService,close){
	
	  var vm = this;
	
	  vm.search;
	  vm.usuarios;
	  vm.usuarioSelecionada;
	  vm.showModal = false;
	  vm.alerts = [];
	  vm.myModal;
	  vm.entity = {};
	  vm.modal;
	  vm.submitted = false;
	  vm.modalExcluirUsuario;
	  
	  var url = $url.url;  
	  
	  	  
	  /** ---INIT ---Variaveis de contrato * */
	  vm.regimeTributario = [{
		 label: 'Simples Nacional',
		 value: 'Simples Nacional',
	  }, {
		 label: 'Lucro Presumido',
		 value: 'Lucro Presumido',
	  }];
	  
	  
	  
	  $scope.anexosList = ['Comercio','Industria','Prestacao de servicos'];
	  
	 
	  
	/**
	 * 
	 */
	vm.init = function(){
		if($state.$current.name == 'usuario'){
        	if ($state.params && $state.params.msg && $state.params.type) {
        		vm.mensagemErro($state);
        	}
        }
	}
	
	/**
	 * Abre modal de exclusão com decisão para o usuário
	 */ 
	 vm.show = function(usuario) {
		 vm.usuarioSelecionada = usuario;
		 vm.modalExcluirUsuario = $uibModal.open({
			templateUrl : 'modal.html',
			size:'lg',
			scope: $scope
		});
	  };
	  
	
	 /**
	  * Fecha a modal de confirmação de exclusao de usuario 
	  */
	 vm.close = function() {
	 	vm.modalExcluirUsuario.close();
	 };
	 
	 
	/**
	 * Exclui a 'Usuario' da base de dados e volta para o fluxo de listagem.
	 */
	 vm.excluir = function(){
		 UsuarioService.excluir(vm.usuarioSelecionada).then(function sucess(result) {
		 vm.usuario = undefined;
		 $state.go('usuario',{msg: 'Usuario excluída com sucesso',type: 'success'});
		 listarTudo();
			}, function error(response) {
				var mensagem = {};
				mensagem.data = {};
				mensagem.data.msg = "Não foi possível efetuar a exclusão da usuario";
				mensagem.data.tipoMensagem = "danger";
				vm.mensagemErro(mensagem);
			});
		 vm.close();
	 } 
	 
	 
	 /**
	  * Metodo responsavel por Salvar .
	  * A ação é executada conforme id da entidade de Usuario
	  */
	 vm.salvar = function(form) {
        vm.submitted = true;
        if (form.$valid) {
            	UsuarioService.salvar(vm.entity)
    			.then(function sucess(result) {
                    $state.go('usuario',{msg: 'Usuario incluída com sucesso',type: 'success'});
    			},function error(response) {
    				vm.mensagemErro(response);
    			});
            }
	 }
	 
	 /**
	   * Abre a modal de exclusão de pagamentos e parcelas
	   */
	  vm.showUsuario = function(usuario) {
		  vm.entity = notfiscal;
		 vm.modalUsuario = $uibModal.open({
			templateUrl : 'modalUsuario.html',
			size:'lg',
			scope: $scope
		 });
	  }
  
	 
	 /**
	  * Exibe mensagem de erro
	  */
	 vm.mensagemErro = function mensagemErro(response){
		 if(response && response.data && response.data.msg) {
			 MessageService.addAlert(response.data.msg, response.data.tipoMensagem);
		 } else if (response.params && response.params.msg && response.params.type){
			 MessageService.addAlert(response.params.msg, response.params.type);
		 } else {
			 MessageService.addAlert('Erro inesperado. Contate o administrador do sistema.');
		 }
	 }
	 
	 /**
	  * Adiciona alert a pagina
	  */
	 vm.addAlert = function(msg,type) {
		 vm.alerts.push({msg: msg,type: type});
	 };
	 
	   
	 /**
	  * Fecha a alert da pagina
	  */
	 vm.closeAlert = function(index) {
		 vm.alerts.splice(index, 1);
	 };
	 
	 
 /**
	 * Retorna a lista com todos as 'Contratos' existentes na base de dados.
	 */
	function listarTudo() {
		UsuarioService.listarTudo().then(function sucess(result) {
			vm.usuarios = result.data;
			}, function error(response) {
				vm.mensagemErro(response);
			});
	}

	
	/**
	 * Apresenta o formulário de inclusão de 'Usuario'.
	 */
	vm.incluir = function() {
		vm.usuario = {};
	}

	
	/**
	 * Apresenta o formulário de alteração de 'Usuario'.
	 */
	vm.alterar = function(usuario) {
		vm.usuario = usuario;
	}
	
	/**
	 * Volta para o fluxo de listagem.
	 */
	vm.cancelar = function() {
		vm.usuario = undefined;
	}
	
	
	/**
	 *  Definição do escopo inicial da pagina de usuario
	 *  A lista será inicializada ao abrir o caso de uso.
	 */
	vm.init();
	listarTudo();
	
	}
	
})();
