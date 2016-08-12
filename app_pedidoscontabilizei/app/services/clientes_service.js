(function () {
	'use strict';

	angular.module('pedidos_app').factory('clienteAPI', clienteAPI);

	clienteAPI.$inject = ['$http'];

	function clienteAPI($http) {
		var _buscaTodos = function () {
			return $http.get('http://localhost:8080/pedidoscontabilizei/rest/clientes');
		}
		
		var _inserir = function (cliente) {
			return $http.post('http://localhost:8080/pedidoscontabilizei/rest/clientes', cliente);	
		}

		
		var _alterar = function (cliente) {
			return $http.put('http://localhost:8080/pedidoscontabilizei/rest/clientes', cliente);	
		}

		var _excluir = function (cpf) {
			return $http.delete('http://localhost:8080/pedidoscontabilizei/rest/clientes/' + cpf);	
		}

		var _buscaPorCPF = function (cpf) {
			return $http.get('http://localhost:8080/pedidoscontabilizei/rest/clientes/' + cpf);	
		}

		return {
			buscaTodos : _buscaTodos,
			buscaPorCPF : _buscaPorCPF,
			inserir : _inserir,
			alterar : _alterar,
			excluir : _excluir
		}
	}

})();