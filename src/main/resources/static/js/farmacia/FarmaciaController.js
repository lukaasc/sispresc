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
        vm.enviarCompras = _enviarCompras;
        vm.buscarListaPrescricao = _buscarListaPrescricao;
        vm.despacharPresc = _despacharPresc;
        vm.prescList = [];

        vm.medCompras = [];
        
        function startSpin() {
            usSpinnerService.spin('spinner-1');
        }
        function stopSpin() {
            usSpinnerService.stop('spinner-1');
        }
        
        function _enviarCompras(listaCompras) {
            startSpin();
            $timeout(function () {
                stopSpin();
                swal("Nice!", "Lista de medicamentos enviada para o setor de compras!", "success");
            }, 2000)
        }

        function _despacharPresc(presc) {
            alert(presc.medResponsavel);
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
            startSpin();
            vm.FarmaciaService.buscarListaPrescricao().then(function successCallback(response) {
                vm.prescList = response.data;
                console.log(response.data);
                stopSpin();
            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível recuperar lista de prescrições!", "error");
                stopSpin();
            });
        }
    }
})();