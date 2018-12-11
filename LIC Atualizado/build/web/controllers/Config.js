
var app = angular.module('myApp', ['ngRoute']);
app.config(function ($routeProvider) {

    $routeProvider

            //Listas

            .when('/consultalista', {
                templateUrl: 'pages/Listas/consulta-Listas.html',
                controller: 'ListaController'
            })

            //Itens

            .when('/consultaitem', {
                templateUrl: 'pages/Item/consulta-Item.html',
                controller: 'ItemController'
            })

            //Categorias
            .when('/consultacategoria', {
                templateUrl: 'pages/Categorias/consulta-categoria.html',
                controller: 'CategoriaController'
            })


            //Histórico
            .when('/consultahistorico', {
                templateUrl: 'pages/Historico/historico-compras.html',
                controller: 'HistoricoController'
            })
            //Usuário
            .when('/consultausuario', {
                templateUrl: 'pages/usuario/consulta-usuario.html',
                controller: 'UsuarioController'
            })

            //lista
            .when('/consultalista', {
                templateUrl: 'pages/Listas/consulta-Listas.html',
                controller: 'ListaController'
            })

            //lista
            .when('/cadastrolista', {
                templateUrl: 'pages/Listas/cadastro-Lista.html',
                controller: 'ListaController'
            })

            .when('/adicionaritemlista', {
                templateUrl: 'pages/Listas/gerenciar-Lista.html',
                controller: 'ListaController'
            })

            //Supermercado
            .when('/consultasupermercado', {
                templateUrl: 'pages/Supermercado/consulta-cadastro-Supermercado.html',
                controller: 'SupermercadoController'
            })

 			//Consultar Valores
            .when('/consultavalores', {
                templateUrl: 'pages/Comparar-Preco/consulta-lista-produto.html',
                controller: ''
            })
            .otherwise({
                redirectTo: '/consultalista'});


});

