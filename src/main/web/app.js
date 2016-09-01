angular.module('propel', ['ui.router']).config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider.state('home', {
            url: '/',
            templateUrl: 'app/components/main/main.html'
        }
    );
    $stateProvider.state('other',{
        url: '/other',
        templateUrl: 'app/components/other/other.html'
    });
});
