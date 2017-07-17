(function() {
 'use strict';
	angular
	   .module('app').factory('$url',$url);
	
	 $url.$inject = ['$location']; //Lista de dependências
	
	  function $url($location) {
		  console.log($location)
	    return {
	      url: $location.absUrl().substr(0, $location.absUrl().indexOf('/#'))
	    };
	  }
})();
