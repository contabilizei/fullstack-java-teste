(function() {
	'use strict';

	angular.module('app').service('MessageService', MessageService);

	MessageService.$inject = [ '$rootScope', '$timeout' ]; 

	function MessageService($rootScope, $timeout) {

		$rootScope.alerts = [];

		return {
			addAlert : addAlert,
			closeAlert : closeAlert
		};

		function addAlert(msg, type, timeout) {
			angular.forEach($rootScope.alerts, function(alert, index) {
				if (alert.msg === msg) {
					closeAlert(index);
				}
			});
			$rootScope.alerts.push({
				msg : msg,
				type : type !== undefined ? type.toLowerCase() : 'danger'
			});

			window.scroll(0, findPos(document.getElementById("message")));

			timeout = timeout !== undefined ? timeout : 5000;
			if (timeout !== 'none') {
				$timeout(function() {
					closeAlert(this);
				}, timeout);
			}
		}
		;

		function closeAlert(index) {
			$rootScope.alerts.splice(index, 1);
		}
		;

		function findPos(obj) {
			var curtop = 0;
			if (obj.offsetParent) {
				do {
					curtop += obj.offsetTop;
				} while (obj = obj.offsetParent);
				return [ curtop ];
			}
		}

		$rootScope.closeAlert = closeAlert;

	}
})();