(function() {
	'use strict';

	angular.module('app').controller('NotaFiscalController', NotaFiscalController);
	NotaFiscalController.$inject = [ '$scope', '$http', 'MessageService','$url', '$uibModal', 'NotaFiscalService', '$state', 'ModalService' ];

	function NotaFiscalController($scope, $http, MessageService, $url,$uibModal, NotaFiscalService, $state, ModalService, close) {

		var vm = this;

		vm.usuarioE;
		vm.usuarios;
		vm.razaoSocial;
		vm.notafiscais;
		vm.notaFiscalSelecionada;
		vm.showModal = false;
		vm.alerts = [];
		vm.myModal;
		vm.entity = {};
		vm.modal;
		vm.submitted = false;
		vm.modalExcluirNotaFiscal;
		vm.modalShowUsuario;
		vm.anexoList;

		var url = $url.url;

		vm.myDate = new Date();
		vm.isOpen = false;

		/** ---INIT --- * */

		$scope.anexosList = [ {
			name : 'Comercio'
		}, {
			name : 'Industria'
		}, {
			name : 'Prestacao de servicos'
		} ];

		vm.today = function() {
			vm.dt = new Date();
		};

		vm.today();

		vm.clear = function() {
			vm.dt = null;
		};

		vm.inlineOptions = {
			customClass : getDayClass,
			minDate : new Date(),
			showWeeks : false
		};

		vm.dateOptions = {
			dateDisabled : disabled,
			formatYear : 'yy',
			maxDate : new Date(2020, 5, 22),
			minDate : new Date(),
			startingDay : 1
		};

		// Disable weekend selection
		function disabled(data) {
			var date = data.date, mode = data.mode;
			return mode === 'day'
					&& (date.getDay() === 0 || date.getDay() === 6);
		}

		vm.toggleMin = function() {
			vm.inlineOptions.minDate = vm.inlineOptions.minDate ? null
					: new Date();
			vm.dateOptions.minDate = vm.inlineOptions.minDate;
		};

		vm.toggleMin();

		vm.open1 = function() {
			vm.popup1.opened = true;
		};

		vm.setDate = function(year, month, day) {
			vm.dt = new Date(year, month, day);
		};

		vm.popup1 = {
			opened : false
		};

		function getDayClass(data) {
			var date = data.date, mode = data.mode;
			if (mode === 'day') {
				var dayToCheck = new Date(date).setHours(0, 0, 0, 0);

				for (var i = 0; i < vm.events.length; i++) {
					var currentDay = new Date(vm.events[i].date).setHours(0, 0,
							0, 0);

					if (dayToCheck === currentDay) {
						return vm.events[i].status;
					}
				}
			}

			return '';
		}

		  
		/**
		 * Init
		 */
		vm.init = function(){
			if($state.$current.name == 'notaFiscal'){
	        	if ($state.params && $state.params.msg && $state.params.type) {
	        		vm.mensagemErro($state);
	        	}
	        }
		}
		/**
		 * Pesquisa usuarios para modal
		 */
		vm.pesquisarUsuarios = function() {
			NotaFiscalService.listarUsuariosModal(vm.usuarioE).then(function sucess(result) {
				vm.usuarios = result.data;
			},function error(response) {
				var mensagem = {};
				mensagem.data = {};
				mensagem.data.msg = response.data;
				mensagem.data.tipoMensagem = "danger";
				vm.mensagemErro(mensagem);
			});
		};
		
		/**
		 * Seleciona Usuario
		 */
		 vm.selecionarUsuario = function(entity) {
			 vm.razaoSocial = entity.razaoSocial;
			 vm.entity.usuario = entity.id;
			 vm.anexoList = entity.anexos;
			 vm.modalShowUsuario.close();
		 };	

		/**
		 * Fecha a modal usuario
		 */
		vm.closeModalUsuario = function() {
			vm.modalUsuario.close();
		}
		
		
		/**
		 * Abre modal de exclusão com decisão para o usuário
		 */ 
		 vm.show = function(notaFiscal) {
			 vm.notaFiscalSelecionada = notaFiscal;
			 vm.modalExcluirNotaFiscal = $uibModal.open({
				templateUrl : 'modal.html',
				size:'lg',
				scope: $scope
			});
		  };

		/**
		 * Exibe modal usuario
		 */
		vm.showUsuario = function(e) {
			vm.modalShowUsuario = $uibModal.open({
				templateUrl : 'modalUsuario.html',
				size : 'lg',
				scope : $scope
			});
		}

		/**
		 * Abre modal de exclusão com decisão para o usuário
		 */
//		vm.show = function(notaFiscal) {
//			vm.notaFiscalSelecionada = notaFiscal;
//
//			vm.modalExcluirNotaFiscal = $uibModal.open({
//				templateUrl : 'modal.html',
//				size : 'lg',
//				scope : $scope
//			});
//		};

		/**
		 * Fecha a modal de confirmação de exclusao de notaFiscal
		 */
		vm.close = function() {
			vm.modalExcluirNotaFiscal.close();
		};

		/**
		 * Exclui a 'NotaFiscal' da base de dados e volta para o fluxo de
		 * listagem.
		 */
		vm.excluir = function() {
			NotaFiscalService
					.excluir(vm.notaFiscalSelecionada)
					.then(
							function sucess(result) {
								vm.notaFiscal = undefined;
								$state.go('notaFiscal', {
									msg : 'NotaFiscal excluída com sucesso',
									type : 'success'
								});
								listarTudo();
							},
							function error(response) {
								var mensagem = {};
								mensagem.data = {};
								mensagem.data.msg = "Não foi possível efetuar a exclusão da notaFiscal";
								mensagem.data.tipoMensagem = "danger";
								vm.mensagemErro(mensagem);
							});
			vm.close();
		}

		/* Metodo responsavel por Salvar ou Alterar. A ação é executada conforme
		 * id da entidade de NotaFiscal
		 */
		 vm.salvar = function(form) {
		        vm.submitted = true;
		        if (form.$valid) {
		            	NotaFiscalService.salvar(vm.entity)
		    			.then(function sucess(result) {
		                    $state.go('notaFiscal',{msg: 'NotaFiscal incluída com sucesso',type: 'success'});
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
		vm.addAlert = function(msg, type) {
			vm.alerts.push({
				msg : msg,
				type : type
			});
		};

		/**
		 * Fecha a alert da pagina
		 */
		vm.closeAlert = function(index) {
			vm.alerts.splice(index, 1);
		};

		/**
		 * Retorna a lista com todos as 'Notas Fiscais' existentes na base de dados.
		 */
		function listarTudo() {
			NotaFiscalService.listarTudo().then(function sucess(result) {
				vm.notafiscais = result.data;
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
			vm.notaFiscal = undefined;
		}

		/**
		 * Definição do escopo inicial da pagina de notaFiscal A lista será
		 * inicializada ao abrir o caso de uso.
		 */
		vm.init();
		listarTudo();

	}

})();
