
var app = angular.module('myApp').controller('HistoricoController', function ($scope) {

    $scope.itemSelecionado = {};

    $scope.voltar = function () {
        window.location.href = "../Listas/consulta-Listas.html";
    }
  
});

