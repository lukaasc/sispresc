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
        vm.buscarListaMedicamentos = _buscarListaMedicamentos;
        vm.criarPrescricao = _criarPrescricao;

        function _buscarInfoInternacao(cpf) {
            return $http({
                method: 'GET',
                url: '/api/internacao/' + cpf
            });
        }

        function _buscarListaMedicamentos() {
            return $http({
                method: 'GET',
                url: '/api/medicamento'
            });
        }

        function _criarPrescricao(presc) {
            return $http({
                method: 'POST',
                url: '/api/prescricao/criar',
                data: presc
            });
        }

        return vm;
    }

})();