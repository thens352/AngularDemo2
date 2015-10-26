angular.module('angularDemo2').factory('IlResource', function($resource) {
	var resource = $resource('rest/ils/:IlId', {
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
});