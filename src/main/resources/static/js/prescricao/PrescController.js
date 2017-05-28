(function () {
    'use strict';

    angular
            .module('myApp')
            .controller('PrescController', PrescController);

    PrescController.$inject = ['$location', '$rootScope', '$http', '$timeout', 'PrescService'];

    function PrescController($location, $rootScope, $http, $timeout, PrescService) {
        var vm = this;

        vm.buscarInfoInternacao = _buscarInfoInternacao;
        vm.criarPrescricao = _criarPrescricao;
        vm.PrescService = PrescService;
        vm.cpf = '';
        vm.infoInternacao = null;
        vm.medicamentosList = [];
        vm.selectedMedicamento = [];

        function _buscarInfoInternacao() {

            vm.PrescService.buscarInfoInternacao(vm.cpf).then(function successCallback(response) {
                vm.infoInternacao = response.data;
                _buscarListaMedicamentos();
            }, function errorCallback(response) {
                vm.infoInternacao = null;
                swal("Oops...", "Não foi possível localizar o paciente!", "error");
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

            vm.PrescService.criarPrescricao().then(function successCallback(response) {


            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível criar prescrição!", "error");
            });
        }
    }

})();