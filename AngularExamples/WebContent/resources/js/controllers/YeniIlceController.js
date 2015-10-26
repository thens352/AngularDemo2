'use strict';
angular.module("examples.yeniilcecontroller", [ "ngRoute", "ngResource", "restangular" ])
		.controller(
				'YeniIlceController',
				function($scope, $location, IlceFactory, IlFactory) {
					$scope.$location = $location;
					$scope.ilce = {};
					$scope.ilList = IlFactory.queryAll();
					$scope.secilenIl = {};
					$scope.save = function() {
						$scope.ilce.il = $scope.secilenIl;
						var successCallback = function(data, responseHeaders) {
							$location.path("/");
						};
						var errorCallback = function() {
						};
						IlceFactory.save($scope.ilce, successCallback,
								errorCallback);
					};
				})