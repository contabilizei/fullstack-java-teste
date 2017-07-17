(function() {
	'use strict';

	angular.module('app').controller('ImpostoController', ImpostoController);
	ImpostoController.$inject = [ '$scope', '$http', 'MessageService','$url', '$uibModal', 'ImpostoService', '$state', 'ModalService' ];

	function ImpostoController($scope, $http, MessageService, $url,$uibModal, ImpostoService, $state, ModalService, close) {

		var vm = this;

		vm.usuarioE;
		vm.razaoSocial;
		vm.usuarios;
		vm.impostos;
		vm.impostosPesquisa;
		vm.impostoSelecionada;
		vm.showModal = false;
		vm.alerts = [];
		vm.myModal;
		vm.entity = {};
		vm.modal;
		vm.submitted = false;
		vm.modalExcluirImposto;
		vm.modalShowUsuario;

		var url = $url.url;

		vm.myDate = new Date();
		vm.isOpen = false;

		/** ---INIT --- * */


		
		
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
		 * 
		 */
		vm.init = function(){
			if($state.$current.name == 'imposto'){
	        	if ($state.params && $state.params.msg && $state.params.type) {
	        		vm.mensagemErro($state);
	        	}
	        }
		}
		/**
		 * Pesquisa credores de acordo com o nome a combo selecionados na modal
		 */
		vm.pesquisarImposto = function() {
			ImpostoService.pesquisarImposto(vm.impostoPesquisa).then(function sucess(result) {
				vm.usuarios = result.data;
			}, function error(response) {
				var mensagem = {};
				mensagem.data = {};
				mensagem.data.msg = response.data;
				mensagem.data.tipoMensagem = "danger";
				vm.mensagemErro(mensagem);
			});
		};
		
		/**
		 * Pesquisa usuarios para modal
		 */
		vm.pesquisarUsuarios = function() {
			ImpostoService.listarUsuarios(vm.usuarioE).then(function sucess(result) {
				vm.usuarios = result.data;
			},function error(response) {
				var mensagem = {};
				mensagem.data = {};
				mensagem.data.msg = response.data;
				mensagem.data.tipoMensagem = "danger";
				vm.mensagemErro(mensagem);
			});
		};
		
		 vm.selecionarUsuario = function(entity) {
			 vm.razaoSocial = entity.razaoSocial;
			 vm.entity.usuario = entity.id;
			 vm.anexoList = entity.anexos;
			 vm.modalShowUsuario.close();
		 };		

		/**
		 * Fecha a modal de gerar pagamentos
		 */
		vm.closeModalUsuario = function() {
			vm.modalUsuario.close();
		}

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
		vm.show = function(imposto) {
			vm.impostoSelecionada = imposto;

			vm.modalExcluirImposto = $uibModal.open({
				templateUrl : 'modal.html',
				size : 'lg',
				scope : $scope
			});
		};
		
		/**
		 * Abre modal de exclusão com decisão para o usuário
		 */
		vm.showMP = function(imposto) {
			vm.impostoSelecionada = imposto;

			vm.modalExcluirImposto = $uibModal.open({
				templateUrl : 'modalPago.html',
				size : 'lg',
				scope : $scope
			});
		};

		/**
		 * Fecha a modal de confirmação de exclusao de imposto
		 */
		vm.close = function() {
			vm.modalExcluirImposto.close();
		};

		/**
		 * Exclui a 'Imposto' da base de dados e volta para o fluxo de
		 * listagem.
		 */
		vm.excluir = function() {
			ImpostoService
					.excluir(vm.impostoSelecionada)
					.then(
							function sucess(result) {
								vm.imposto = undefined;
								$state.go('imposto', {
									msg : 'Imposto excluída com sucesso',
									type : 'success'
								});
								listarTudo();
							},
							function error(response) {
								var mensagem = {};
								mensagem.data = {};
								mensagem.data.msg = "Não foi possível efetuar a exclusão da imposto";
								mensagem.data.tipoMensagem = "danger";
								vm.mensagemErro(mensagem);
							});
			vm.close();
		}
		
		/**
		 * Exclui a 'Imposto' da base de dados e volta para o fluxo de
		 * listagem.
		 */
		vm.marcarPago = function() {
			ImpostoService
					.marcarPago(vm.impostoSelecionada)
					.then(
							function sucess(result) {
								vm.impostoSelecionada = undefined;
								$state.go('imposto', {
									msg : 'Imposto pago com sucesso',
									type : 'success'
								});
								listarTudo();
							},
							function error(response) {
								var mensagem = {};
								mensagem.data = {};
								mensagem.data.msg = "Não foi possível pagar o imposto";
								mensagem.data.tipoMensagem = "danger";
								vm.mensagemErro(mensagem);
							});
			vm.close();
		}



		
		/**
		 * Metodo responsavel por Salvar ou Alterar. A ação é executada conforme
		 * id da entidade de Imposto
		 */
		 vm.gerarImposto = function(form) {
		        vm.submitted = true;
		        if (form.$valid) {
		            	ImpostoService.gerarImposto(vm.entity)
		    			.then(function sucess(result) {
		                    $state.go('imposto',{msg: 'Imposto incluído com sucesso',type: 'success'});
		    			}, function error(response) {
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
		vm.mensagemErro = function mensagemErro(response) {
			if (response && response.data && response.data.msg) {
				MessageService.addAlert(response.data.msg,response.data.tipoMensagem);
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
		 * 
		 */
		vm.verificaNomeAtualizacao = function(imposto) {
			if (imposto == 'S') {
				return 'Sim';
			} else {
				return 'Não';
			}
		}

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
			ImpostoService.listarTudo().then(function sucess(result) {
				vm.impostos = result.data;
			}, function error(response) {
				var mensagem = {};
				mensagem.data = {};
				mensagem.data.msg = response.data;
				mensagem.data.tipoMensagem = "danger";
				vm.mensagemErro(mensagem);
			});
		}

		/**
		 * Retorna a lista com todos as 'Cotações de Impostos' existentes na
		 * base de dados.
		 */
		function listarCotacoes(entity) {
			ImpostoService.listarCotacoes(entity).then(
					function sucess(result) {
						vm.cotacoes = result.data;
					}, function error(response) {
						var mensagem = {};
						mensagem.data = {};
						mensagem.data.msg = response.data;
						mensagem.data.tipoMensagem = "danger";
						vm.mensagemErro(mensagem);
					});
		}

		/**
		 * Apresenta o formulário de inclusão de 'Imposto'.
		 */
		vm.incluir = function() {
			vm.imposto = {};
		}

		/**
		 * Apresenta o formulário de alteração de 'Imposto'.
		 */
		vm.alterar = function(imposto) {
			vm.imposto = imposto;
		}

		/**
		 * Volta para o fluxo de listagem.
		 */
		vm.cancelar = function() {
			vm.imposto = undefined;
		}

		/**
		 * Definição do escopo inicial da pagina de imposto A lista será
		 * inicializada ao abrir o caso de uso.
		 */
		vm.init();
		listarTudo();

	}

})();
