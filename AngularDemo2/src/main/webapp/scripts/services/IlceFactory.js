angular.module('angularDemo2').factory('IlceResource', function($resource) {
	var resource = $resource('rest/ilces/:IlceId', {
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
		},
		'queryByIlId' : {
			method : 'GET',
			isArray : true,
			url : 'rest/ilces/byIlId?IlId=:IlId'
		}
	});
	return resource;
});