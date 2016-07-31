angular.module("starstore")

.factory('Produtos', function() {
  
  var produtos = [{
  	codigo: 'ABC',
  	descricao: 'Nave 1',
  	valor: 10,
  	img: 'nave1.jpg'
  },
  {
  	codigo: 'DEF',
  	descricao: 'Nave 2',
  	valor: 19,
  	img: 'nave2.jpg'
  },
  {
  	codigo: 'GHI',
  	descricao: 'Nave 3',
  	valor: 30,
  	img: 'nave3.jpg'
  },
  {
  	codigo: 'JKL',
  	descricao: 'Nave 4',
  	valor: 99,
  	img: 'nave4.jpg'
  },
  ];

  return {
  	all: function() {
  		return produtos;
  	},
  	get: function(produtoCod) {
  		for (var i = 0; i < produtos.length; i++) {
  			if (produtos[i].codigo === produtoCod) {
  				return produtos[i];
  			}
  		}
  		return null;
  	}
  };
});
