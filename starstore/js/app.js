angular.module("starstore",['ngRoute'])

.config(function($routeProvider, $locationProvider)
{
   // remove o # da url
   $locationProvider.html5Mode(false);
 
   $routeProvider
 
   // para a rota '/', carregaremos o template home.html e o controller 'HomeCtrl'
   .when('/', {
      templateUrl : 'view/home.html',
      controller     : 'HelloWorldCtrl',
})

.otherwise ({ redirectTo: '/' });
});