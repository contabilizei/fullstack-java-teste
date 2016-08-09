(function () {
	'use strict';

	angular.module('pedidos_app').factory('produtoAPI', produtoAPI);

	produtoAPI.$inject = ["$http"];

	function produtoAPI($http) {
		var _buscaTodos = function () {
			return $http.get('http://localhost:8080/pedidoscontabilizei/rest/produtos');
		}

		var _buscaPorCodigo = function (codigo) {
			return $http.get('http://localhost:8080/pedidoscontabilizei/rest/produtos/' + codigo);
		}

		var _inserir = function (produto) {
			return $http.post('http://localhost:8080/pedidoscontabilizei/rest/produtos', produto);
		}

		var _alterar = function (produto) {
			return $http.put('http://localhost:8080/pedidoscontabilizei/rest/produtos', produto);
		}

		var _excluir = function (codigo) {
			return $http.delete('http://localhost:8080/pedidoscontabilizei/rest/produtos/' + codigo);
		}

		return {
			buscaTodos : _buscaTodos,
			buscaPorCodigo : _buscaPorCodigo,
			inserir : _inserir,
			alterar : _alterar,
			excluir : _excluir
		}
	}
})();