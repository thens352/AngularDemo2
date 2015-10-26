'use strict';
angular.module("examples.yeniilcontroller", [ "ngRoute", "ngResource" ])
		.controller('YeniIlController', function($scope, $location, IlFactory) {
			$scope.$location = $location;
			$scope.il = {};

			$scope.save = function() {
				var successCallback = function(data, responseHeaders) {
					$location.path("/");
				};
				var errorCallback = function() {
				};
				IlFactory.save($scope.il, successCallback, errorCallback);
			};
		})