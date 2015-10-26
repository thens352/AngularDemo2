'use strict';
angular.module(
		"examples",
		[ "ngRoute", "ngResource", "examples.illistelecontroller",
				"examples.yeniilcontroller", "examples.duzenleilcontroller",
				"examples.ilfactory", "examples.ilcelistelecontroller",
				"examples.yeniilcecontroller", "examples.ilcefactory" ])
		.config(function($routeProvider, Restangular) {
			$routeProvider.when("/", {
				templateUrl : "resources/views/main.html"
			}).when("/ilListesi", {
				templateUrl : "resources/views/il/ilListesi.html",
				controller : "IlListeleController"
			}).when("/yeniIl", {
				templateUrl : "resources/views/il/yeniIl.html",
				controller : "YeniIlController"
			}).when("/duzenleIl/:IlId", {
				templateUrl : "resources/views/il/duzenleIl.html",
				controller : "DuzenleIlController"
			}).when("/ilceListesi", {
				templateUrl : "resources/views/ilce/ilceListesi.html",
				controller : "IlceListeleController"
			}).when("/yeniIlce", {
				templateUrl : "resources/views/ilce/yeniIlce.html",
				controller : "YeniIlceController"
			})
		})