angular
		.module('angularDemo2')
		.controller(
				'EditMahalleController',
				function($scope, $routeParams, $location, $filter,
						MahalleResource, IlceResource, IlResource, Restangular) {
					var self = this;
					$scope.disabled = false;
					$scope.$location = $location;
					$scope.ilList = [];
					$scope.ilceList = [];
					$scope.ilSelection = {};
					$scope.ilceSelection = {};

					$scope.get = function() {
						var successCallback = function(data) {
							self.original = data;
							$scope.mahalle = new MahalleResource(self.original);

							IlResource
									.queryAll(function(items) {
										$scope.ilSelectionList = $
												.map(
														items,
														function(item) {
															var wrappedObject = {
																id : item.id
															};
															var labelObject = {
																value : item.id,
																text : item.ad
															};
															if ($scope.mahalle.ilce.il
																	&& item.id == $scope.mahalle.ilce.il.id) {
																$scope.ilSelection = labelObject;
																$scope.mahalle.ilce.il = wrappedObject;
																self.original.ilce.il = $scope.mahalle.ilce.il;
															}
															return labelObject;
														});
									});
							Restangular
									.all("ilces")
									.customGET("byIlId", {
										IlId : $scope.mahalle.ilce.il.id
									})
									.then(
											function(items) {
												$scope.ilceList = $
														.map(
																items,
																function(item) {
																	if ($scope.mahalle.ilce
																			&& item.id == $scope.mahalle.ilce.id) {
																		$scope.ilceSelection = item;
																		$scope.mahalle.ilce = item;
																		self.original.ilce = $scope.mahalle.ilce;
																	}
																	return item;
																});
											});
						};

						var errorCallback = function() {
							$location.path("/Mahalles");
						};
						MahalleResource.get({
							MahalleId : $routeParams.MahalleId
						}, successCallback, errorCallback);
					};

					$scope.isClean = function() {
						return angular.equals(self.original, $scope.mahalle);
					};

					$scope.save = function() {
						var successCallback = function() {
							$scope.get();
							$scope.displayError = false;
						};
						var errorCallback = function() {
							$scope.displayError = true;
						};
						$scope.mahalle.$update(successCallback, errorCallback);
					};

					$scope.cancel = function() {
						$location.path("/Mahalles");
					};

					$scope.remove = function() {
						var successCallback = function() {
							$location.path("/Mahalles");
							$scope.displayError = false;
						};
						var errorCallback = function() {
							$scope.displayError = true;
						};
						$scope.mahalle.$remove(successCallback, errorCallback);
					};

					$scope.ilList = IlResource.queryAll(function(items) {
						$scope.ilSelectionList = $.map(items, function(item) {
							return ({
								value : item.id,
								text : item.ad
							});
						});
					});

					$scope.$watch("ilceSelection", function(selection) {
						if (typeof selection != 'undefined') {
							$scope.mahalle.ilce = {};
							$scope.mahalle.ilce = selection;
						}
					});

					$scope.$watch("ilSelection", function(selection) {
						if (typeof selection != 'undefined') {
							$scope.ilceList = IlceResource.queryByIlId({
								IlId : selection.value
							});
							$scope.mahalle.ilce.il = selection;
						}
					});

					$scope.get();
				});