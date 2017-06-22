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
        vm.compraEnviada = false;
        
        function startSpin() {
            usSpinnerService.spin('spinner-1');
        }
        function stopSpin() {
            usSpinnerService.stop('spinner-1');
        }
        
        function _enviarCompras(listaCompras) {
            startSpin();
            vm.medCompras = [];
            vm.compraEnviada = true;
            $timeout(function () {
                stopSpin();
                swal("Nice!", "Lista de medicamentos enviada para o setor de compras!", "success");
            }, 2000)
        }

        function _despacharPresc(presc) {
          startSpin();
            vm.FarmaciaService.despacharPrescricao(presc).then(function successCallback(response) {
                var idx = vm.prescList.indexOf(presc);
                vm.prescList.splice(idx, 1);

                swal("Nice!", "Prescrição despachada!", "success");
                stopSpin();
            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível despachar prescrição!", "error");
                stopSpin();
            });
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
                if(response.status === 200 && response.data.length === 0){
                    swal("Farmácia sem prescrições em espera!");
                }
                vm.prescList = response.data;
                stopSpin();
            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível recuperar lista de prescrições!", "error");
                stopSpin();
            });
        }
    }
})();