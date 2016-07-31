angular.module("starstore")

.controller("HomeCtrl",function($scope){
	$scope.message = "Home";
})

.controller("NovoPedidoCtrl",function($scope, $http, Produtos){
	$scope.produtos = Produtos.all();

	// calcular valor total dos produtos solicitados pelo cliente
	$scope.getValorTotal = function(produtos){
		var valorTotal = 0;
		for (var i =0; i<produtos.length; i++){
			valorTotal += (produtos[i].valorUnitario * produtos[i].quantidade);
		}
		return valorTotal;
	}

	// a partir dos valores preenchidos no formulário, cria um array de Pedidos que será submetido a API
	$scope.criarArrayProdutos = function(quantidade){
		var produtos = [];

		for (q in quantidade){
			var p = Produtos.get(q);
			var produto = { codigo: p.codigo, 
				descricao: p.descricao, 
				quantidade: quantidade[q], 
				valorUnitario: p.valor
			};
			produtos.push(produto);
		}
		return produtos;
	}

	// Estrutura o pedido e submete os valores
	$scope.comprar = function(pedido){
		
		var produtos = $scope.criarArrayProdutos(pedido.quantidade);
		console.log(produtos);
		var valorTotal = $scope.getValorTotal(produtos);
		
		var pedidos = { cliente: pedido.cliente,
			produtos: produtos,
			valorTotal: valorTotal,
			dataDeEmissao: new Date()
		};

		$http({
			method: 'POST',
			url : 'http://localhost:8080/contabilizei/rest/pedidos/',
			data: pedidos,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
		});

		
	};
})

.controller("PedidosCtrl",function($scope, $http){

	$http({
		method: 'GET',
		url: 'http://localhost:8080/contabilizei/rest/pedidos/'
	})
	.then(function(result){
		console.log(result.data);
		$scope.pedidos = result.data;
	}, function error(response){
		alert('Ocorreu um erro. tente novamente');
	});

	$scope.delete = function (numero){
		$http.delete('http://localhost:8080/contabilizei/rest/pedidos/'+numero);

	};
})


.controller("ShowCtrl",function($scope, $http, $routeParams){
	$scope.numero = $routeParams.numero;
	$http({
		method: 'GET',
		url: 'http://localhost:8080/contabilizei/rest/pedidos/'+$scope.numero
	})
	.then(function(result){
		console.log(result.data);
		$scope.pedido = result.data;
	}, function error(response){
		alert('Ocorreu um erro. tente novamente');
	});
})

.controller("EditCtrl",function($scope, $http, $routeParams, Produtos){
	$scope.produtos = Produtos.all();

	$scope.numero = $routeParams.numero;
	$http({
		method: 'GET',
		url: 'http://localhost:8080/contabilizei/rest/pedidos/'+$scope.numero
	})
	.then(function(result){
		console.log(result.data);
		$scope.pedido = result.data;
	}, function error(response){
		alert('Ocorreu um erro. tente novamente');
	});


	// calcular valor total dos produtos solicitados pelo cliente
	$scope.getValorTotal = function(produtos){
		var valorTotal = 0;
		for (var i =0; i<produtos.length; i++){
			valorTotal += (produtos[i].valorUnitario * produtos[i].quantidade);
		}
		return valorTotal;
	}

	// a partir dos valores preenchidos no formulário, cria um array de Pedidos que será submetido a API
	$scope.criarArrayProdutos = function(quantidade){
		var produtos = [];

		for (q in quantidade){
			var p = Produtos.get(q);
			var produto = { codigo: p.codigo, 
				descricao: p.descricao, 
				quantidade: quantidade[q], 
				valorUnitario: p.valor
			};
			produtos.push(produto);
		}
		return produtos;
	}

	// Estrutura o pedido e submete os valores
	$scope.atualizar = function(pedido){
		
		var produtos = $scope.criarArrayProdutos(pedido.quantidade);
		console.log(produtos);
		var valorTotal = $scope.getValorTotal(produtos);
		
		var pedidos = { cliente: pedido.cliente,
			produtos: produtos,
			valorTotal: valorTotal,
			dataDeEmissao: new Date()
		};

		$http({
			method: 'PUT',
			url : 'http://localhost:8080/contabilizei/rest/pedidos/',
			data: pedidos,
			headers: { 'Content-Type': 'application/x-www-form-urlencoded' }
		});

		
	};
})


;