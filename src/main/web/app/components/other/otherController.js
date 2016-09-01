angular.module('propel').controller('OtherController', ['$scope', 'OtherServices', function ($scope, OtherServices) {

        OtherServices.getBrowserCounts()
            .then(
                function(browsers) {
                    $scope.browsers = browsers;
                });

}]);