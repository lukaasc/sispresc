(function () {
    'use strict';

    angular
        .module('myApp', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'views/default.html',
            })
            .when('/login', {
                templateUrl: 'views/login.html',
                controller: 'LoginController',
                controllerAs: 'vm'
            })
            .otherwise({redirectTo: '/'});
    }

    run.$inject = ['$rootScope', '$location', '$cookies', '$http'];
    function run($rootScope, $location, $cookies, $http) {
        // keep user logged in after page refresh
        $rootScope.currentUser = $cookies.get('currentUser');

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login']) === -1;
            var loggedIn = $rootScope.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }

})();