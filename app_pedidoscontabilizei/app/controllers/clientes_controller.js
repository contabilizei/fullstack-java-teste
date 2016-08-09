(function () {
	'use strict';

	angular.module('pedidos_app').controller('clientesController', clientesController);

	clientesController.$inject = ['clienteAPI', '$scope'];

	function clientesController(clienteAPI, $scope, $uibModal) {
		$scope.clientes = [];		
		$scope.cliente;
		$scope.escondeAdicionar = false;

		$scope.buscaTodos = function () {
			buscarTodos();
		}

		$scope.carregarFormulario = function (cpf) {
			clienteAPI.buscaPorCPF(cpf).success(function (data) {
				$scope.cliente = data;
				$scope.escondeAdicionar = true;
			});
		}

		$scope.inserir = function (cliente) {
			clienteAPI.inserir(cliente).success(function (data) {
				limparDados();
			})
			.error(function (data) {
				mostraErro(data);
			});
		}

		$scope.alterar = function (cliente) {
			clienteAPI.alterar(cliente).success(function (data) {
				limparDados();
			})
			.error(function (data) {
				mostraErro(data);
			});
		}

		$scope.excluir = function (cpf) {
			clienteAPI.excluir(cpf).success(function (data) {				
				limparDados();
			})
			.error(function (data) {
				mostraErro(data);
			});
		}

		$scope.cancelar = function () {
			limparDados();
		}

		function buscarTodos() {
			clienteAPI.buscaTodos().success(function (result) {
					$scope.clientes = result;
			});
		}

		function limparDados() {
			$scope.cliente = null;
			$scope.escondeAdicionar = false;

			resetCliente();
			buscarTodos();
		}

		function resetCliente() {
			$('#nomRazaoSocial').val('');
			$('#cpfCnpj').val('');
			$('#telefone').val('');
			$('#telefone').val('');
		}

		function mostraErro(data) {
			alert("Infelizmente ocorreu um erro. Verifique seus dados e tente novamente mais tarde");
		}
	}

})();