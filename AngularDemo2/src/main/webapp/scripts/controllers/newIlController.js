
angular.module('angularDemo2').controller('NewIlController', function ($scope, $location, locationParser, IlResource ) {
    $scope.disabled = false;
    $scope.$location = $location;
    $scope.il = $scope.il || {};
    

    $scope.save = function() {
        var successCallback = function(data,responseHeaders){
            var id = locationParser(responseHeaders);
            $location.path('/Ils/edit/' + id);
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError = true;
        };
        IlResource.save($scope.il, successCallback, errorCallback);
    };
    
    $scope.cancel = function() {
        $location.path("/Ils");
    };
});