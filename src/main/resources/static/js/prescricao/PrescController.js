(function () {
    'use strict';

    angular
            .module('myApp')
            .controller('PrescController', PrescController);

    PrescController.$inject = ['$location', '$rootScope', '$http', 'PrescService'];

    function PrescController($location, $rootScope, $http, PrescService) {
        var vm = this;

        vm.buscarInfoInternacao = _buscarInfoInternacao;
        vm.PrescService = PrescService;
        vm.cpf = '';
        vm.infoInternacao = null;

        function _buscarInfoInternacao() {
            
            vm.PrescService.buscarInfoInternacao(vm.cpf).then(function successCallback(response) {
                    vm.infoInternacao = response.data;
            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível localizar o paciente!", "error");
            });
            
            console.log(vm.cpf);
        }
    }

})();