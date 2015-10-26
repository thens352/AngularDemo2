'use strict';
angular.module("examples.ilcefactory", [ "ngRoute", "ngResource" ]).factory(
		'IlceFactory', function($resource) {
			var resource = $resource('rest/ilce/:IlceId', {
				IlceId : '@id'
			}, {
				'queryAll' : {
					method : 'GET',
					isArray : true
				},
				'query' : {
					method : 'GET',
					isArray : false
				},
				'update' : {
					method : 'PUT'
				}
			});
			return resource;
		})