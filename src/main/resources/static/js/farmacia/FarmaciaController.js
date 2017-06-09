(function () {
    'use strict';

    angular
            .module('myApp')
            .controller('FarmaciaController', FarmaciaController);

    FarmaciaController.$inject = ['$timeout', 'usSpinnerService', 'FarmaciaService'];

    function FarmaciaController($timeout, usSpinnerService, FarmaciaService) {
        var vm = this;
        
        vm.FarmaciaService = FarmaciaService;

        $timeout(_buscarListaPrescricao(), 1000);

        function _buscarListaPrescricao() {
            //vm.startSpin();
            
            vm.FarmaciaService.buscarListaPrescricao().then(function successCallback(response) {
                console.log(response.data);
                //vm.stopSpin();
            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível recuperar lista de prescrições!", "error");
                //vm.stopSpin();
            });
        }

        vm.startSpin = function () {
            usSpinnerService.spin('spinner-1');
        }
        vm.stopSpin = function () {
            usSpinnerService.stop('spinner-1');
        }

    }
})();