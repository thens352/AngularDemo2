angular.module('angularDemo2').factory('SokakResource', function($resource) {
	var resource = $resource('rest/sokaks/:SokakId', {
		SokakId : '@id'
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