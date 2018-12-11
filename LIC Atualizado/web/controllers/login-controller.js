
var app = angular.module('myApp').controller('LoginController', function ($scope, $rootScope) {

    $scope.itemSelecionado = {};

    $scope.validarLogin = function () {
        if ($scope.itemSelecionado.login && $scope.itemSelecionado.senha) {
            var params = {};

            params.login = $scope.itemSelecionado.login;
            params.senha = $scope.itemSelecionado.senha;

            $.post('LoginValidacao', params, function (data) {
                if (data) {
                    var usuario = data[0];

                    localStorage.idUsuario = usuario.id;
                    localStorage.idTipoUsuario = usuario.tipoUsuario;
                    localStorage.loginUsuario = usuario.login;
                    localStorage.nome = usuario.nome;
                    localStorage.transacoes = data[1];

                    window.location.href = "dashboard.html";
                } else {
                    swal("Ops!", "Login Inválido!", "error");
                    setTimeout(function () {
                        window.location.href = "index.html";
                    }, 3000);

                }
            })

        } else {
            swal("Ops!", "Preencha os campos obrigatórios!", "warning");
        }
    }

    $scope.logout = function () {
        var params = {};
        $.post('LogoutServlet', params, function (data) {
            if (!data) {
                window.location.href = "index.html";
            }
        });
    }

});

