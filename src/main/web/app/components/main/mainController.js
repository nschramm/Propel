angular.module('propel').controller('MainController', ['$scope', function ($scope) {

    $scope.input = "Type Here";
    $scope.output = "See Here";


    $scope.doOutput = function () {
        console.log($scope.input);
        $scope.output = $scope.input;
    };
}]);