'use strict';
angular.module("examples.ilcelistelecontroller", [ "ngRoute", "ngResource" ])
		.controller('IlceListeleController',
				function($scope, $http, IlceFactory) {
					$scope.ilceList = [];
					$scope.listele = function() {
						$scope.ilceList = IlceFactory.queryAll();
					};

					$scope.listele();
				})
