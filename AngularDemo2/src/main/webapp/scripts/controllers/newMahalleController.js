angular.module('angularDemo2').controller(
		'NewMahalleController',
		function($scope, $location, locationParser, MahalleResource,
				IlceResource, IlResource) {
			$scope.disabled = false;
			$scope.$location = $location;
			$scope.mahalle = $scope.mahalle || {};
			$scope.ilceList = [];

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
					$scope.mahalle.ilce = {};
					$scope.mahalle.ilce= selection;
				}
			});

			$scope.save = function() {
				var successCallback = function(data, responseHeaders) {
					var id = locationParser(responseHeaders);
					$location.path('/Mahalles/edit/' + id);
					$scope.displayError = false;
				};
				var errorCallback = function() {
					$scope.displayError = true;
				};
				MahalleResource.save($scope.mahalle, successCallback,
						errorCallback);
			};

			$scope.cancel = function() {
				$location.path("/Mahalles");
			};
		});