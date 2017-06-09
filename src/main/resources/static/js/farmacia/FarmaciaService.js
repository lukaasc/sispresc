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

        function _buscarListaPrescricao() {
            return $http({
                method: 'GET',
                url: '/api/prescricao/enviadasFarmacia'
            });
        }

        // function _criarPrescricao(presc) {
        //     return $http({
        //         method: 'POST',
        //         url: '/api/prescricao/criar',
        //         data: presc
        //     });
        // }
        
        return vm;
    }

})();