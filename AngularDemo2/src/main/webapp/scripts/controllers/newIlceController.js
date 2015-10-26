
angular.module('angularDemo2').controller('NewIlceController', function ($scope, $location, locationParser, IlceResource , IlResource) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.ilce = $scope.ilce || {};
    
    $scope.ilList = IlResource.queryAll(function(items){
        $scope.ilSelectionList = $.map(items, function(item) {
            return ( {
                value : item.id,
                text : item.ad
            });
        });
    });
    $scope.$watch("ilSelection", function(selection) {
        if ( typeof selection != 'undefined') {
            $scope.ilce.il = {};
            $scope.ilce.il.id = selection.value;
        }
    });
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Ilces/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        IlceResource.save($scope.ilce, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Ilces");
    };
});