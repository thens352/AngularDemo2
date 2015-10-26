angular.module('angularDemo2').controller(
		'NewSokakController',
		function($scope, $location, locationParser, SokakResource,
				MahalleResource, IlceResource, IlResource) {
			$scope.disabled = false;
			$scope.$location = $location;
			$scope.sokak = $scope.sokak || {};
			$scope.ilceList = [];
			$scope.mahalleList = [];

			$scope.ilList = IlResource.queryAll(function(items) {
				$scope.ilSelectionList = $.map(items, function(item) {
					return ({
						value : item.id,
						text : item.ad
					});
				});
			});

			$scope.$watch("ilSelection", function(selection) {
				if (typeof selection != 'undefined') {
					$scope.ilceList = IlceResource.queryByIlId({
						IlId : selection.value
					});
				}
			});
			
			$scope.$watch("ilceSelection", function(selection) {
				if (typeof selection != 'undefined') {
					$scope.mahalleList = MahalleResource.queryByIlceId({
						IlceId : selection.id
					});
				}
			});

			$scope.$watch("mahalleSelection", function(selection) {
				if (typeof selection != 'undefined') {
					$scope.sokak.mahalle = {};
					$scope.sokak.mahalle = selection;
				}
			});

			$scope.save = function() {
				var successCallback = function(data, responseHeaders) {
					var id = locationParser(responseHeaders);
					$location.path('/Sokaks/edit/' + id);
					$scope.displayError = false;
				};
				var errorCallback = function() {
					$scope.displayError = true;
				};
				SokakResource
						.save($scope.sokak, successCallback, errorCallback);
			};

			$scope.cancel = function() {
				$location.path("/Sokaks");
			};
		});