'use strict';
angular.module("examples.duzenleilcontroller", [ "ngRoute", "ngResource" ]).controller(
		'DuzenleIlController',
		function($scope, $routeParams, $location, IlFactory) {
			var self = this;
			$scope.$location = $location;

			$scope.get = function() {
				var successCallback = function(data) {
					self.original = data;
					$scope.il = new IlFactory(self.original);
				};
				var errorCallback = function() {
				};
				IlFactory.get({
					IlId : $routeParams.IlId
				}, successCallback, errorCallback);
			};

			$scope.save = function() {
				var successCallback = function() {
					$scope.get();
					$location.path("/");
				};
				var errorCallback = function() {
				};
				$scope.il.$update(successCallback, errorCallback);
			};

			$scope.cancel = function() {
				$location.path("/");
			};

			$scope.remove = function() {
				var successCallback = function() {
					$location.path("/");
				};
				var errorCallback = function() {
				};
				$scope.il.$remove(successCallback, errorCallback);
			};

			$scope.get();
		})