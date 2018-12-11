
var app = angular.module('myApp').controller('CompararValoresController', function ($scope) {

    $scope.listas = {};
    $scope.exibir = false;
    $scope.exibirProduto = false;
    $scope.exibirLista = false;
    $scope.pesquisarProduto = function () {
        $scope.exibirProduto = !$scope.exibirProduto;
    }

    $scope.cancelarEdicaoProduto = function () {
        $scope.exibirProduto = !$scope.exibirProduto;
    }

    $scope.pesquisarLista = function () {
        $scope.exibirLista = !$scope.exibirLista;
    }

    $scope.cancelarEdicaoLista = function () {
        $scope.exibirLista = !$scope.exibirLista;
    }

// -----------------------------------------------------------------------------


    $scope.consultarListas = function () {
        var params = {};
        params.idUsuario = localStorage.idUsuario;
        params.DescricaoUsuario = localStorage.DescricaoTipoUsuario;
        $.post('ConsultarListasServlet', params, function (data) {
            if (data) {
                $scope.listas = data;
                $("#goAngular").trigger('click');
            } else {
                swal("Ops!", "Nenhum item encontrado!", "warning");
            }
        })
    };
    $scope.consultarItensLista = function () {

        var params = {};
        $.post('ConsultarItemServlet', params, function (data) {
            if (data) {
                $scope.itens = data;

                $("#goAngular").trigger('click');
            }
        })
    }


    var map = undefined;
    $scope.iniciarMapa = function () {

        unwired.key = mapboxgl.accessToken = '12cdf4cfc31040';
        map = new mapboxgl.Map({
            container: 'map',
            attributionControl: false, //need this to show a compact attribution icon (i) instead of the whole text
            style: unwired.getLayer("streets"), //get Unwired's style template
            zoom: 11,
            center: [-34.8323382, -7.8921494]
        });
        var layers = ["streets", "earth", "hybrid"];
        map.addControl(new unwiredLayerControl({
            key: unwired.key,
            layers: layers
        }), 'top-left');
        var nav = new mapboxgl.NavigationControl();
        map.addControl(nav, 'top-right');
        map.addControl(new mapboxgl.FullscreenControl());
        map.addControl(new mapboxgl.ScaleControl({
            maxWidth: 80,
            unit: 'metric' //imperial for miles
        }));
        map.addControl(new mapboxgl.GeolocateControl({
            positionOptions: {
                enableHighAccuracy: true
            },
            trackUserLocation: true
        }));
    }

    $scope.pesquisarmelhorProduto = function (produto) {

        var params = {};
        if (produto) {
            params.id = produto;
            $.post('ConsultarEnderecoProdutoServlet', params, function (data) {
                if (data) {
                    $scope.enderecosProduto = data;


                    for (x in $scope.enderecosProduto) {
                        var endereco = $scope.enderecosProduto[x].Logradouro + " " + $scope.enderecosProduto[x].Numero + " " + $scope.enderecosProduto[x].Bairro + " " + $scope.enderecosProduto[x].Localidade + " " + $scope.enderecosProduto[x].UF;
//               var geocoding =     $scope.pesquisarlatitude(endereco);


                        var el = document.createElement('div');
                        el.id = 'markerWithExternalCss';
                        // finally, create the marker
                        var markerWithExternalCss = new mapboxgl.Marker(el).setLngLat([-34.8372598, -7.9055712]).addTo(map);

                        $("#goAngular").trigger('click');
                    }

                }
            })
        } else {
            swal("Oops", "É necessário escolher um produto para pesquisar!", "warning");
        }
    }
}
);

