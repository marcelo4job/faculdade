
var app = angular.module('myApp').controller('CategoriaController', function ($scope) {

    $scope.itemSelecionado = {};
    $scope.parametrosTabela = {};
    $scope.botaoClicado = '';
    $scope.exibir = true;

    $scope.incluirCategoria = function () {
        $scope.itemSelecionado = {};
        $scope.botaoClicado = 'incluir';
        $scope.exibir = false;
    }

    $scope.editarCategoria = function (item) {
        $scope.itemSelecionado = item;
        $scope.botaoClicado = 'alterar';
        $scope.exibir = false;
    }

    $scope.visualizarCategoria = function (item) {
        $scope.itemSelecionado = item;
        $scope.exibir = false;
        $scope.botaoClicado = 'visualizar';
    }

    $scope.consultarCategorias = function () {
        var params = {};
        $.post('ConsultarCategoriaServlet', params, function (data) {
            if (data) {
                $scope.parametrosTabela = data;
                $("#goAngular").trigger('click');

            } else {
                swal("Ops!", "Nenhum item encontrado!", "warning");
            }
        })
    }


    $scope.cancelarEdicao = function () {
        $scope.exibir = true;
    }

    $scope.selecionarCategoria = function (item) {
        $scope.itemSelecionado = item;
        $scope.exibir = true;
    }

    $scope.salvarCategoria = function () {

        if ($scope.botaoClicado == 'incluir') {
            if ($scope.itemSelecionado.nomeCategoria) {

                var params = {};
                params.nomeCategoria = $scope.itemSelecionado.nomeCategoria;
                $.post('CadastroCategoriaServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Cadastro Realizado com sucesso!",
                            timer: 3000,
                            showConfirmButton: true,
                            type: "success"

                        });
                        setTimeout(function () {
                            $scope.exibir = true;
                            $scope.consultarCategorias();
                        }, 3000);
                    } else {
                        swal("Ops!", "Falha ao cadastrar Categoria!", "error");
                    }
                })
            } else {
                swal("ops!", "Preencha os campos obrigatórios", "warning");
            }
        } else {
            var params = {};
            params.nomeCategoria = $scope.itemSelecionado.nomeCategoria;
            params.idCategoria = $scope.itemSelecionado.id;
            $.post('EditarCategoriaServlet', params, function (data) {
                if (data) {
                    swal({
                        title: "Edição Realizada com sucesso!",
                        timer: 3000,
                        showConfirmButton: true,
                        type: "success"

                    });
                    setTimeout(function () {
                        $scope.exibir = true;
                        $scope.consultarCategorias();
                    }, 3000);
                } else {
                    swal("Ops!", "Falha ao editar Categoria!", "error");
                }
            })
        }


    }

    $scope.excluirCategoria = function (id) {
        swal({
            title: 'Excluir categoria?',
            text: "você tem certeza que deseja excluir esta categoria?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'sim',
            cancelButtonText: 'cancelar'
        }, function () {
            var params = {};
            params.idCategoria = id;
            $.post('ExcluirCategoriaServlet', params, function (data) {
                if (data) {
                    swal({
                        title: "Exclusão Realizada com sucesso!",
                        timer: 3000,
                        showConfirmButton: true,
                        type: "success"

                    });
                    setTimeout(function () {
                        sessionStorage.clear();
                        $scope.exibir = true;
                        $scope.consultarCategorias();
                    }, 3000);
                } else {
                    swal("Ops!", "Falha ao excluir Categoria!", "error");
                }
            })
        });
    }

}
);

