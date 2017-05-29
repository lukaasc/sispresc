(function () {
    'use strict';

    angular
            .module('myApp')
            .controller('HistoricoController', HistoricoController);

    HistoricoController.$inject = ['$timeout', 'usSpinnerService', 'PrescService'];

    function HistoricoController($timeout, usSpinnerService, PrescService) {
        var vm = this;
        vm.PrescService = PrescService;

        vm.buscarHistoricoInternacao = _buscarHistoricoInternacao;
        vm.cpf = '';
        vm.listaPrescricoes = [];

        function _buscarHistoricoInternacao(cpf) {
            vm.startSpin();
            
            vm.PrescService.buscarHistoricoInternacao(cpf).then(function successCallback(response) {
                vm.listaPrescricoes = response.data;
                vm.stopSpin();
            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível recuperar histórico!", "error");
                vm.stopSpin();
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