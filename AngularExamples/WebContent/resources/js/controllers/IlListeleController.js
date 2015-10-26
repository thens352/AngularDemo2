'use strict';
angular.module("examples.illistelecontroller", [ "ngRoute", "ngResource" ]).controller(
		'IlListeleController', function($scope, $http, IlFactory) {
			$scope.ilList = [];
			$scope.listele = function() {
				$scope.ilList = IlFactory.queryAll();
			};

			$scope.listele();
		})
