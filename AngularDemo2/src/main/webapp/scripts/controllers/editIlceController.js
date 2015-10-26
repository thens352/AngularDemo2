

angular.module('angularDemo2').controller('EditIlceController', function($scope, $routeParams, $location, IlceResource , IlResource) {
    var self = this;
    $scope.disabled = false;
    $scope.$location = $location;
    
    $scope.get = function() {
        var successCallback = function(data){
            self.original = data;
            $scope.ilce = new IlceResource(self.original);
            IlResource.queryAll(function(items) {
                $scope.ilSelectionList = $.map(items, function(item) {
                    var wrappedObject = {
                        id : item.id
                    };
                    var labelObject = {
                        value : item.id,
                        text : item.ad
                    };
                    if($scope.ilce.il && item.id == $scope.ilce.il.id) {
                        $scope.ilSelection = labelObject;
                        $scope.ilce.il = wrappedObject;
                        self.original.il = $scope.ilce.il;
                    }
                    return labelObject;
                });
            });
        };
        var errorCallback = function() {
            $location.path("/Ilces");
        };
        IlceResource.get({IlceId:$routeParams.IlceId}, successCallback, errorCallback);
    };

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.ilce);
    };

    $scope.save = function() {
        var successCallback = function(){
            $scope.get();
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        };
        $scope.ilce.$update(successCallback, errorCallback);
    };

    $scope.cancel = function() {
        $location.path("/Ilces");
    };

    $scope.remove = function() {
        var successCallback = function() {
            $location.path("/Ilces");
            $scope.displayError = false;
        };
        var errorCallback = function() {
            $scope.displayError=true;
        }; 
        $scope.ilce.$remove(successCallback, errorCallback);
    };
    
    $scope.$watch("ilSelection", function(selection) {
        if (typeof selection != 'undefined') {
            $scope.ilce.il = {};
            $scope.ilce.il.id = selection.value;
        }
    });
    
    $scope.get();
});