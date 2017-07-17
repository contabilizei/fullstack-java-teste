(function() {
    'use strict';
	
angular.module('app').controller('UsuarioController', UsuarioController);

UsuarioController.$inject = ['$scope','$http','MessageService','$url','$uibModal','UsuarioService' ,'$state','ModalService'];

function UsuarioController($scope,$http,MessageService, $url, $uibModal,UsuarioService,$state,ModalService,close){
	
	  var vm = this;
	
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
	 * Init
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
	 * Exclui o 'Usuario' da base de dados e volta para o fluxo de listagem.
	 */
	 vm.excluir = function(){
		 UsuarioService.excluir(vm.usuarioSelecionada).then(function sucess(result) {
		 vm.usuario = undefined;
		 $state.go('usuario',{msg: 'Usuario excluído com sucesso',type: 'success'});
		 listarTudo();
			}, function error(response) {
				var mensagem = {};
				mensagem.data = {};
				mensagem.data.msg = response.data;
				mensagem.data.tipoMensagem = "danger";
				vm.mensagemErro(mensagem);
			});
		 vm.close();
	 } 
	 
	 
	 /**
	  * Metodo responsavel por Salvar .
	  */
	 vm.salvar = function(form) {
        vm.submitted = true;
        if (form.$valid) {
            	UsuarioService.salvar(vm.entity)
    			.then(function sucess(result) {
                    $state.go('usuario',{msg: 'Usuario incluída com sucesso',type: 'success'});
    			},function error(response) {
    				var mensagem = {};
    				mensagem.data = {};
    				mensagem.data.msg = response.data;
    				mensagem.data.tipoMensagem = "danger";
    				vm.mensagemErro(mensagem);
    			});
            }
	 }
	 
	 
	 /**
	  * Exibe mensagem de erro
	  */
	  vm.mensagemErro = function mensagemErro(response){		 
			 if(response && response.data && response.data.msg) {
				 MessageService.addAlert(response.data.msg, response.data.tipoMensagem);
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
	 * Retorna a lista com todos os Usuarios existentes na base de dados.
	 */
	function listarTudo() {
		UsuarioService.listarTudo().then(function sucess(result) {
			vm.usuarios = result.data;
			}, function error(response) {
				var mensagem = {};
				mensagem.data = {};
				mensagem.data.msg = response.data;
				mensagem.data.tipoMensagem = "danger";
				vm.mensagemErro(mensagem);
			});
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
