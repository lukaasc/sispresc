/* global vm */
(function () {
    'use strict';

    angular
            .module('myApp')
            .factory('PrescService', PrescService);

    PrescService.$inject = ['$location', '$rootScope', '$http'];

    function PrescService($location, $rootScope, $http) {
        var vm = this;

        vm.buscarInfoInternacao = _buscarInfoInternacao;

        function _buscarInfoInternacao(cpf) {
            return $http({
                method: 'GET',
                url: '/api/internacao/' + cpf
            });
        }

        return vm;
    }

})();