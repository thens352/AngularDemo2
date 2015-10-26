'use strict';
angular.module("examples.ilfactory", [ "ngRoute", "ngResource" ]).factory('IlFactory',
		function($resource) {
			var resource = $resource('rest/il/:IlId', {
				IlId : '@id'
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