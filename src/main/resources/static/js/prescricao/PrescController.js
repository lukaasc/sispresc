(function () {
    'use strict';

    angular
            .module('myApp')
            .controller('PrescController', PrescController);

    PrescController.$inject = ['$location', '$rootScope', '$http', '$timeout', 'PrescService', 'usSpinnerService'];

    function PrescController($location, $rootScope, $http, $timeout, PrescService, usSpinnerService) {
        var vm = this;

        vm.buscarInfoInternacao = _buscarInfoInternacao;
        vm.criarPrescricao = _criarPrescricao;
        vm.PrescService = PrescService;
        vm.infoInternacao = null;
        vm.medicamentosList = [];
        vm.prescricao = {};

        vm.startSpin = function () {
            usSpinnerService.spin('spinner-1');
        }
        vm.stopSpin = function () {
            usSpinnerService.stop('spinner-1');
        }
        function _buscarInfoInternacao() {
            vm.startSpin();
            vm.PrescService.buscarInfoInternacao(vm.prescricao.cpf).then(function successCallback(response) {
                vm.infoInternacao = response.data;
                _buscarListaMedicamentos();
                vm.stopSpin();
            }, function errorCallback(response) {
                vm.infoInternacao = null;
                swal("Oops...", "Não foi possível localizar o paciente!", "error");
                vm.stopSpin();
            });
        }

        function _buscarListaMedicamentos() {

            vm.PrescService.buscarListaMedicamentos().then(function successCallback(response) {
                vm.medicamentosList = response.data;

                $timeout(function () {
                    $('.selectpicker').selectpicker('refresh');
                });

            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível carregar a lista de medicamentos!", "error");
            });
        }

        function _criarPrescricao() {
            vm.startSpin();
            vm.PrescService.criarPrescricao(vm.prescricao).then(function successCallback(response) {
                swal("Good job!", "Prescrição criada!", "success")
                vm.prescricao = {};
                vm.infoInternacao = null;
                vm.medicamentosList = [];
                vm.stopSpin();
            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível criar prescrição!", "error");
                vm.stopSpin();
            });
        }
    }

})();