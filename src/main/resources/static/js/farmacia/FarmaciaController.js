(function () {
    'use strict';

    angular
            .module('myApp')
            .controller('FarmaciaController', FarmaciaController);

    FarmaciaController.$inject = ['$timeout', 'usSpinnerService', 'FarmaciaService'];

    function FarmaciaController($timeout, usSpinnerService, FarmaciaService) {
        var vm = this;

        vm.FarmaciaService = FarmaciaService;
        vm.addListaCompras = _addListaCompras;
        vm.prescList = [];

        vm.medCompras = [];
        
        function _enviarCompras(){
            //TODO
        }
        
        function _despacharPresc(){
            //TODO
        }

        function _addListaCompras(idMedicamento) {
            var idx = vm.medCompras.indexOf(idMedicamento);

            // Is currently selected
            if (idx > -1) {
                //vm.medCompras.splice(idx, 1);
                return;
            }

            // Is newly selected
            else {
                vm.medCompras.push(idMedicamento);
            }
        }

        function _buscarListaPrescricao() {
            vm.prescList = [];

            vm.FarmaciaService.buscarListaPrescricao().then(function successCallback(response) {
                vm.prescList = response.data;
                console.log(response.data);
                stopSpin();
            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível recuperar lista de prescrições!", "error");
                stopSpin();
            });
        }

        function startSpin() {
            usSpinnerService.spin('spinner-1');
        }
        function stopSpin() {
            usSpinnerService.stop('spinner-1');
        }

        _buscarListaPrescricao();

    }
})();