/* global vm */
(function () {
    'use strict';

    angular
            .module('myApp')
            .factory('FarmaciaService', FarmaciaService);

    FarmaciaService.$inject = ['$location', '$rootScope', '$http'];

    function FarmaciaService($location, $rootScope, $http) {
        var vm = this;
        
        vm.buscarListaPrescricao = _buscarListaPrescricao;
        vm.despacharPrescricao = _despacharPrescricao;

        function _buscarListaPrescricao() {
            return $http({
                method: 'GET',
                url: '/api/prescricao/enviadasFarmacia'
            });
        }

        function _despacharPrescricao(presc) {
            return $http({
                method: 'POST',
                url: '/api/prescricao/despachar',
                data: presc
            });
        }
        
        return vm;
    }

})();