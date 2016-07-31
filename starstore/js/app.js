angular.module("starstore",['ngRoute'])

.config(function($routeProvider, $locationProvider)
{

	$routeProvider

   // para a rota '/', view 'home.html'
   .when('/', {
   	templateUrl : 'view/home.html',
   	controller     : 'HomeCtrl',
   })

   .when('/sobre', {
   	templateUrl : 'view/sobre.html'
   })


   .when('/novo', {
   	templateUrl : 'view/novo.html',
   	controller     : 'NovoPedidoCtrl',
   })


   .when('/pedidos', {
   	templateUrl : 'view/pedidos.html',
   	controller     : 'PedidosCtrl',
   })

   .when('/show/:numero', {
   	templateUrl : 'view/show.html',
   	controller     : 'ShowCtrl',
   })

   .when('/edit/:numero', {
   	templateUrl : 'view/edit.html',
   	controller     : 'EditCtrl',
   })



   .otherwise ({ redirectTo: '/' });
});