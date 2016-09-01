angular.module('propel').service('OtherServices', ['$http', function ($http) {

    return {
        getBrowserCounts : function() {
            return $http.get('/browsers')
                .then(function(response) {
                    return response.data;
                })
        }
    }

}]);
