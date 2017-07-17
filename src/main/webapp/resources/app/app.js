var app = angular.module('app', ['ui.router','ui.bootstrap','mgcrea.ngStrap','ngAnimate', 'angularUtils.directives.dirPagination','angularModalService',
                                 'ui.utils.masks','idf.br-filters','ui.utils.masks','angucomplete']);
app.config(function($modalProvider) {
	  angular.extend($modalProvider.defaults, {
		    html: true
		  });
		})
app.config(function($stateProvider, $urlRouterProvider) {
    
    $stateProvider
    /*****************************************Rota do Usuario *****************************************/
    .state('home', {
        url: '/',
        templateUrl: 'index.html',
        controller: 'UsuarioController as vm'
    })
    .state('usuario', {
        url: '/usuario',
        templateUrl: 'resources/app/usuario/usuario.list.html',
        controller: 'UsuarioController as vm',
        params : {msg: null, type: null, }
    })
    .state('createUsuario', {
        url: '/usuario/create',
        templateUrl: 'resources/app/usuario/usuario.create.html',
        controller: 'UsuarioController as vm'
    })
    
     .state('notaFiscal', {
        url: '/notafiscal',
        templateUrl: 'resources/app/notafiscal/notafiscal.list.html',
        controller: 'NotaFiscalController as vm',
        params : {msg: null, type: null, }
    })
    .state('createNotaFiscal', {
        url: '/notafiscal/create',
        templateUrl: 'resources/app/notafiscal/notafiscal.create.html',
        controller: 'NotaFiscalController as vm'
    })
    .state('imposto', {
        url: '/imposto',
        templateUrl: 'resources/app/imposto/imposto.list.html',
        controller: 'ImpostoController as vm',
        params : {msg: null, type: null, }
    })
    .state('createImposto', {
        url: '/imposto/create',
        templateUrl: 'resources/app/imposto/imposto.create.html',
        controller: 'ImpostoController as vm'
    })
    ;   
    
});