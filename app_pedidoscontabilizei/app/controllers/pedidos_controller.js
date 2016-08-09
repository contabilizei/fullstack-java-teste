(function () {	
	'use strict';

	angular.module('pedidos_app').controller('pedidosController', pedidosController);

	pedidosController.$inject = ['pedidoAPI', 'clienteAPI', 'produtoAPI', '$scope'];

	function pedidosController(pedidoAPI, clienteAPI, produtoAPI, $scope) {
		$scope.pedido;
		$scope.pedidos 					= [];
		$scope.clientes 				= [];
		$scope.produto					= [];
		$scope.produtosPedido 	= [];		
		$scope.codigoProdutos 	= [];
		$scope.escondeAdicionar = false;		

		$scope.buscaTodos = function () {
			buscaDadosIniciais();
		}

		$scope.carregarFormulario = function (codigo) {
			resetPedidos();

			pedidoAPI.buscaPorCodigo(codigo).success(function (data) {
				$scope.pedido = data;
				$scope.escondeAdicionar = true;
				let tamanho = data.codigoProdutos.length;

				for (var i = 0; i < tamanho; i++) {
						var obj = new Object();
						obj.codigo = data.codigoProdutos[i];
						obj.descricao = data.descricaoProdutos[i];
						obj.valorUnitario = data.valorUnitarioProdutos[i];

						$scope.codigoProdutos.push(obj.codigo);
						$scope.produtosPedido.push(obj);						
				}				
			})
		}

		$scope.inserir = function (pedido) {			
			if (pedido === undefined) {
				alert("Ops! Os dados do seu pedido são inválidos!");
			}

			pedido.codigoProdutos = $scope.codigoProdutos;

			pedidoAPI.inserir(pedido).success(function (data) {
				limpaDados();
			})
			.error(function (data) {
				mostraErro(data);
			});
		}

		$scope.alterar = function (pedido) {
			pedido.codigoProdutos = $scope.codigoProdutos;

			pedidoAPI.alterar(pedido).success(function (data) {
				limpaDados();
			})
			.error(function (data) {
				mostraErro(data);
			});
		}

		$scope.excluir = function (numero) {
			pedidoAPI.excluir(numero).success(function (data) {
				limpaDados();
			})
			.error(function (data) {
				mostraErro(data);
			});
		}

		$scope.adicionarProduto = function (codigo) {
			if (codigo === '' || codigo === undefined) {
				return;
			}

			let existente = $scope.produtosPedido.some(function (arg) {
				return arg.codigo == codigo;
			})

			if (existente) {
				alert("Produto já adicionado!")				
				return false;
			}

			produtoAPI.buscaPorCodigo(codigo).success(function (data) {
				$scope.codigoProdutos.push(data.codigo);

				$scope.produtosPedido.push(data);

				calculaTotal();
			})
		}

		$scope.removerProduto = function (codigo) {
			var novosCodigos = $scope.codigoProdutos.filter(function (param) {
				return param != codigo;
			});
			$scope.codigoProdutos = [];

			var novosProdutos = $scope.produtosPedido.filter(function (param) {
				return param.codigo != codigo;
			});			
			$scope.produtosPedido = [];

			$scope.codigoProdutos = novosCodigos;
			$scope.produtosPedido = novosProdutos;

			calculaTotal();
		}

		$scope.cancelar = function () {
			resetPedidos();
		}

		function calculaTotal() {
			var total = $scope.produtosPedido.reduce(function (result, prod) {
					return result + prod.valorUnitario;
				}, 0);

				$scope.pedido.valorTotal = parseFloat(total.toFixed(2));
		}

		function buscaDadosIniciais() {
			pedidoAPI.buscaTodos().success(function (data) {
				$scope.pedidos = data;				
			});

			clienteAPI.buscaTodos().success(function (data) {
				$scope.clientes = data;				
			});

			produtoAPI.buscaTodos().success(function (data) {
				$scope.produtos = data;
			});
		}

		function limpaDados() {
			$scope.pedido = null;				
			$scope.escondeAdicionar	= false;

			resetPedidos();
			buscaDadosIniciais();
		}

		function resetPedidos(argument) {
			$('select').val('');
			$('#valorUnitario').val('');

			$scope.produtosPedido		= [];
			$scope.codigoProdutos 	= [];
		}

		function mostraErro(data) {
			alert("Infelizmente ocorreu um erro. Verifique seus dados e tente novamente mais tarde");
		}
	}
})();