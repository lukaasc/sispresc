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
                    console.log(response.data.authorized);
                    var loggedUser = {
                        username: response.data.username,
                        password: response.data.password,
                        authorized: true
                    }
                    var expireDate = new Date();
                    expireDate.setDate(expireDate.getDate() + 1);
                    $cookies.put('currentUser', loggedUser, {
                        'expires': expireDate
                    });
                    $rootScope.currentUser = $cookies.get('currentUser');
                    $location.path('/');
            }, function errorCallback(response) {
                swal("Oops...", "User not authorized!", "error");
            });
    }

}})();