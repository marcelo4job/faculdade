
var app = angular.module('myApp').controller('CarrinhoController', function ($scope) {

    $scope.itemSelecionado = [{
            caminhoImagem: '../../images/icons/18.png',
            Nome: 'feijão',
            Quantidade: 2,
            Preço: 2.75
        }];

    $scope.itemSelecionado.push({
        caminhoImagem: '../../images/icons/18.png',
        Nome: 'arroz',
        Quantidade: 1,
        Preço: 5.75
    });





});

