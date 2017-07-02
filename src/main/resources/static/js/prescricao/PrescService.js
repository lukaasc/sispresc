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
        vm.buscarHistoricoInternacao = _buscarHistoricoInternacao;
        vm.getRankingMedicamentos = _getRankingMedicamentos;

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

        function _getRankingMedicamentos() {
            return $http({
                method: 'GET',
                url: '/api/medicamento/ranking'
            });
        }

        function _criarPrescricao(presc) {
            return $http({
                method: 'POST',
                url: '/api/prescricao/criar',
                data: presc
            });
        }

        function _buscarHistoricoInternacao(cpf) {
            return $http({
                method: 'GET',
                url: '/api/prescricao/historicoPaciente/' + cpf
            });
        }

        return vm;
    }

})();