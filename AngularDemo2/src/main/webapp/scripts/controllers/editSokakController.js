angular
		.module('angularDemo2')
		.controller(
				'EditSokakController',
				function($scope, $routeParams, $location, IlResource,
						IlceResource, SokakResource, MahalleResource,
						Restangular) {
					var self = this;
					$scope.disabled = false;
					$scope.$location = $location;
					$scope.ilList = [];
					$scope.ilceList = [];
					$scope.mahalleList = [];
					$scope.ilSelection = {};
					$scope.ilceSelection = {};
					$scope.mahalleSelection = {};

					$scope.get = function() {
						var successCallback = function(data) {
							self.original = data;
							$scope.sokak = new SokakResource(self.original);

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
															if ($scope.sokak.mahalle.ilce.il
																	&& item.id == $scope.sokak.mahalle.ilce.il.id) {
																$scope.ilSelection = labelObject;
																$scope.sokak.mahalle.ilce.il = wrappedObject;
																self.original.mahalle.ilce.il = $scope.sokak.mahalle.ilce.il;
															}
															return labelObject;
														});
									});

							Restangular
									.all("ilces")
									.customGET("byIlId", {
										IlId : $scope.sokak.mahalle.ilce.il.id
									})
									.then(
											function(items) {
												$scope.ilceList = $
														.map(
																items,
																function(item) {
																	if ($scope.sokak.mahalle.ilce
																			&& item.id == $scope.sokak.mahalle.ilce.id) {
																		$scope.ilceSelection = item;
																		$scope.sokak.mahalle.ilce = item;
																		self.original.mahalle.ilce = $scope.sokak.mahalle.ilce;
																	}
																	return item;
																});
											});

							Restangular
									.all("mahalles")
									.customGET("byIlceId", {
										IlceId : $scope.sokak.mahalle.ilce.id
									})
									.then(
											function(items) {
												$scope.mahalleList = $
														.map(
																items,
																function(item) {
																	var wrappedObject = {
																		id : item.id
																	};
																	if ($scope.sokak.mahalle
																			&& item.id == $scope.sokak.mahalle.id) {
																		$scope.mahalleSelection = item;
																		$scope.sokak.mahalle = wrappedObject;
																		self.original.mahalle = $scope.sokak.mahalle;
																	}
																	return item;
																});
											});
						};
						var errorCallback = function() {
							$location.path("/Sokaks");
						};
						SokakResource.get({
							SokakId : $routeParams.SokakId
						}, successCallback, errorCallback);
					};

					$scope.isClean = function() {
						return angular.equals(self.original, $scope.sokak);
					};

					$scope.save = function() {
						var successCallback = function() {
							$scope.get();
							$scope.displayError = false;
						};
						var errorCallback = function() {
							$scope.displayError = true;
						};
						$scope.sokak.$update(successCallback, errorCallback);
					};

					$scope.cancel = function() {
						$location.path("/Sokaks");
					};

					$scope.remove = function() {
						var successCallback = function() {
							$location.path("/Sokaks");
							$scope.displayError = false;
						};
						var errorCallback = function() {
							$scope.displayError = true;
						};
						$scope.sokak.$remove(successCallback, errorCallback);
					};

					$scope.$watch("mahalleSelection", function(selection) {
						if (typeof selection != 'undefined') {
							$scope.sokak.mahalle = {};
							$scope.sokak.mahalle = selection;
						}
					});

					$scope.$watch("ilceSelection", function(selection) {
						if (typeof selection != 'undefined') {
							$scope.mahalleList = MahalleResource
									.queryByIlceId({
										IlceId : selection.id
									});
							$scope.sokak.mahalle.ilce = selection;
						}
					});

					$scope.$watch("ilSelection", function(selection) {
						if (typeof selection != 'undefined') {
							$scope.ilceList = IlceResource.queryByIlId({
								IlId : selection.value
							});
							$scope.sokak.mahalle.ilce.il = selection;
						}
					});

					$scope.get();
				});