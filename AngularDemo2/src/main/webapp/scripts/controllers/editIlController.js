

angular.module('angularDemo2').controller('EditIlController', function($scope, $routeParams, $location, IlResource ) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.il = new IlResource(self.original);
        };
        var errorCallback = function() {
            $location.path("/Ils");
        };
        IlResource.get({IlId:$routeParams.IlId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.il);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.il.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Ils");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Ils");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.il.$remove(successCallback, errorCallback);
    };
    
    
    $scope.get();
});