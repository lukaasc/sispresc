(function () {
    'use strict';

    angular
            .module('myApp')
            .controller('AnaliseController', AnaliseController);

    AnaliseController.$inject = ['$timeout', '$scope', 'usSpinnerService', 'PrescService'];

    function AnaliseController($timeout, $scope, usSpinnerService, PrescService) {
        var vm = this;

        this.medicamentos = null;
        this.labels = [];
        this.data = [];

        this.startSpin = function () {
            usSpinnerService.spin('spinner-1');
        };

        this.stopSpin = function () {
            usSpinnerService.stop('spinner-1');
        };

        this.plotChart = function () {
            this.startSpin();
            PrescService.getRankingMedicamentos().then(function successCallback(response) {
                
                var labels = [];
                var data = [];
                
                angular.forEach(response.data, function(value){
                    labels.push(value.nome.substring(0, 18) + '...');
                    data.push(value.count);
                });
                
                var ctx = document.getElementById("myChart").getContext('2d');
                
                var myChart = new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                                label: '# Prescrições',
                                data: data,
                                backgroundColor: [
                                    'rgba(255, 99, 132, 0.2)',
                                    'rgba(54, 162, 235, 0.2)',
                                    'rgba(255, 206, 86, 0.2)',
                                    'rgba(75, 192, 192, 0.2)',
                                    'rgba(153, 102, 255, 0.2)',
                                    'rgba(255, 159, 64, 0.2)'
                                ],
                                borderColor: [
                                    'rgba(255,99,132,1)',
                                    'rgba(54, 162, 235, 1)',
                                    'rgba(255, 206, 86, 1)',
                                    'rgba(75, 192, 192, 1)',
                                    'rgba(153, 102, 255, 1)',
                                    'rgba(255, 159, 64, 1)'
                                ],
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                    ticks: {
                                        beginAtZero: true
                                    }
                                }]
                        }
                    }
                });
                
                vm.stopSpin();
            }, function errorCallback(response) {
                swal("Oops...", "Não foi possível recuperar o ranking de medicamentos enviados!", "error");
                vm.stopSpin();
            });
        };
    }
})();