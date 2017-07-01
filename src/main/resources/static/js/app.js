(function () {
    'use strict';

    angular
            .module('myApp', ['ngRoute', 'ngCookies', 'angularSpinner'])
            .config(config)
            .run(run);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
                .when('/', {
                    templateUrl: 'views/default.html',
                    controller: 'PrescController',
                    controllerAs: 'vm'
                })
                .when('/historico', {
                    templateUrl: 'views/historico.html',
                    controller: 'HistoricoController',
                    controllerAs: 'vm'
                })
                .when('/farmacia', {
                    templateUrl: 'views/farmacia.html',
                    controller: 'FarmaciaController',
                    controllerAs: 'vm'
                })
                .when('/login', {
                    templateUrl: 'views/login.html',
                    controller: 'LoginController',
                    controllerAs: 'vm'
                })
                .otherwise({redirectTo: '/'});
    }

    run.$inject = ['$rootScope', '$location', '$cookies'];
    function run($rootScope, $location, $cookies) {
        // keep user logged in after page refresh
        $rootScope.currentUser = $cookies.get('currentUser');

        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            $rootScope.currentUser = $cookies.get('currentUser');
            // redirect to login page if not logged in and trying to access a restricted page
            var restrictedPage = $.inArray($location.path(), ['/login']) === -1;
            var loggedIn = $rootScope.currentUser;
            if (restrictedPage && !loggedIn) {
                $location.path('/login');
            }
        });

        $rootScope.logout = function () {
            $rootScope.currentUser = null;
            $cookies.remove('currentUser');
            $location.path('/login');
        };

        $rootScope.getUser = function () {
            var user = $cookies.getObject('currentUser');
            return user;
        };

    }

})();