(function () {
    'use strict';

    angular
        .module('myApp')
        .controller('LoginController', LoginController);

    LoginController.$inject = ['$location', '$rootScope', '$http', '$cookies', '$timeout'];
    function LoginController($location, $rootScope, $http, $cookies, $timeout) {
        var vm = this;

        vm.login = _login;
        vm.username = '';
        vm.password = '';

        function _login() {
            $http({
                method: 'POST',
                url: '/api/login/authenticate',
                data: {'username': vm.username, 'password': vm.password}
            }).then(function successCallback(response) {
                    var loggedUser = {
                        username: response.data.username,
                        password: response.data.password,
                        authorized: response.data.authorized,
                        role: response.data.role,
                        name: response.data.name,
                        lastname: response.data.lastname
                    }
                    var expireDate = new Date();
                    expireDate.setDate(expireDate.getDate() + 1);
                    $cookies.put('currentUser', loggedUser, {
                        'expires': expireDate
                    });
                    $location.path('/');
            }, function errorCallback(response) {
                swal("Oops...", "User not authorized!", "error");
            });
    }

}})();