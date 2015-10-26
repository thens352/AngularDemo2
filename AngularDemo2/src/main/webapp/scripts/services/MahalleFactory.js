angular.module('angularDemo2').factory('MahalleResource', function($resource) {
	var resource = $resource('rest/mahalles/:MahalleId', {
		MahalleId : '@id'
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
		'queryByIlceId' : {
			method : 'GET',
			isArray : true,
			url : 'rest/mahalles/byIlceId?IlceId=:IlceId'
		}
	});
	return resource;
});