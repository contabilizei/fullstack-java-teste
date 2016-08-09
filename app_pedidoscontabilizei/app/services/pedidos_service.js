(function () {
	'use strict';

	angular.module('pedidos_app').factory('pedidoAPI', pedidoAPI);

	pedidoAPI.$inject = ['$http'];

	function pedidoAPI($http) {
		var _buscaTodos = function () {
			return $http.get('http://localhost:8080/pedidoscontabilizei/rest/pedidos');
		}

		var _buscaPorCodigo = function (codigo) {
			return $http.get('http://localhost:8080/pedidoscontabilizei/rest/pedidos/' + codigo);
		}

		var _inserir = function (pedido) {
			return $http.post('http://localhost:8080/pedidoscontabilizei/rest/pedidos', pedido);
		}

		var _alterar = function (pedido) {
			return $http.put('http://localhost:8080/pedidoscontabilizei/rest/pedidos', pedido);
		}

		var _excluir = function (codigo) {
			return $http.delete('http://localhost:8080/pedidoscontabilizei/rest/pedidos/' + codigo);
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