'use strict';

angular
		.module('angularDemo2', [ 'ngRoute', 'ngResource', 'restangular' ])
		.config(
				function($routeProvider, RestangularProvider) {
					$routeProvider.when('/', {
						templateUrl : 'views/landing.html',
						controller : 'LandingPageController'
					}).when('/Ils', {
						templateUrl : 'views/Il/search.html',
						controller : 'SearchIlController'
					}).when('/Ils/new', {
						templateUrl : 'views/Il/detail.html',
						controller : 'NewIlController'
					}).when('/Ils/edit/:IlId', {
						templateUrl : 'views/Il/detail.html',
						controller : 'EditIlController'
					}).when('/Ilces', {
						templateUrl : 'views/Ilce/search.html',
						controller : 'SearchIlceController'
					}).when('/Ilces/new', {
						templateUrl : 'views/Ilce/detail.html',
						controller : 'NewIlceController'
					}).when('/Ilces/edit/:IlceId', {
						templateUrl : 'views/Ilce/detail.html',
						controller : 'EditIlceController'
					}).when('/Mahalles', {
						templateUrl : 'views/Mahalle/search.html',
						controller : 'SearchMahalleController'
					}).when('/Mahalles/new', {
						templateUrl : 'views/Mahalle/detail.html',
						controller : 'NewMahalleController'
					}).when('/Mahalles/edit/:MahalleId', {
						templateUrl : 'views/Mahalle/detail.html',
						controller : 'EditMahalleController'
					}).when('/Sokaks', {
						templateUrl : 'views/Sokak/search.html',
						controller : 'SearchSokakController'
					}).when('/Sokaks/new', {
						templateUrl : 'views/Sokak/detail.html',
						controller : 'NewSokakController'
					}).when('/Sokaks/edit/:SokakId', {
						templateUrl : 'views/Sokak/detail.html',
						controller : 'EditSokakController'
					}).otherwise({
						redirectTo : '/'
					});

					RestangularProvider
							.setBaseUrl('http://localhost:8080/AngularDemo2/rest/');
				}).controller('LandingPageController',
				function LandingPageController($scope) {
				}).controller(
				'NavController',
				function NavController($scope, $location) {
					$scope.matchesRoute = function(route) {
						var path = $location.path();
						return (path === ("/" + route) || path.indexOf("/"
								+ route + "/") == 0);
					};
				}).filter('ilceFilter', function() {
			return function(items, ID) {
				var returnIlceList = [];
				for (var i = 0; i < items.length; i++) {
					if (items[i].il.id === ID)
						returnIlceList.push(items[i]);
				}
				return returnIlceList;
			};
		}).filter('mahalleFilter', function() {
			return function(items, ID) {
				var returnMahalleList = [];
				for (var i = 0; i < items.length; i++) {
					if (items[i].ilce.id === ID)
						returnMahalleList.push(items[i]);
				}
				return returnMahalleList;
			};
		});
