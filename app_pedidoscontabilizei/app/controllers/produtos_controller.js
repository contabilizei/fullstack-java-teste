(function () {
	'use strict';

	angular.module('pedidos_app').controller('produtosController', produtosController);

	produtosController.$inject = ['produtoAPI', '$scope'];

	function produtosController(produtoAPI, $scope) {
		$scope.produtos = [];
		$scope.produto;
		$scope.escondeAdicionar = false;

		$scope.buscaTodos = function () {
			buscarTodos();
		}

		$scope.carregarFormulario = function (codigo) {
			produtoAPI.buscaPorCodigo(codigo).success(function (data) {
				$scope.produto = data;
				$scope.escondeAdicionar = true;
			});
		}

		$scope.inserir = function (produto) {
			produto.valorUnitario = produto.valorUnitario.replace(',', '.');

			produtoAPI.inserir(produto).success(function (data) {
				limparDados();
			})
			.error(function (data) {
				mostraErro(data);
			});
		}

		$scope.alterar = function (produto) {
			produto.valorUnitario = produto.valorUnitario.replace(',', '.');

			produtoAPI.alterar(produto).success(function (data) {
				limparDados();
			})
			.error(function (data) {
				mostraErro(data);
			});
		}

		$scope.excluir = function (codigo) {
			produtoAPI.excluir(codigo).success(function (data) {
				limparDados();
			})
			.error(function (data) {
				mostraErro(data);
			});
		}

		$scope.cancelar = function (argument) {
			limparDados();
		}

		function buscarTodos() {
			produtoAPI.buscaTodos().success(function (data) {
				$scope.produtos = data;
			});
		}

		function limparDados(argument) {
			$scope.produto = null;			
			$scope.escondeAdicionar = false;

			resetProdutos();
			buscarTodos();
		}

		function resetProdutos() {
			$('#descricao').val('');
			$('#quantidade').val('');
			$('#valorUnitario').val('');			
		}

		function mostraErro(data) {
			alert("Infelizmente ocorreu um erro. Verifique seus dados e tente novamente mais tarde");
		}
	}
})();