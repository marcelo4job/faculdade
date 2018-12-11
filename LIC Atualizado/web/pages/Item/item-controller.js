
var app = angular.module('myApp').controller('ItemController', function ($scope) {

    $scope.itemSelecionado = {};
    $scope.parametrosTabela = {};
    $scope.categorias = {};
    $scope.exibir = true;


    $scope.consultarCategorias = function () {
        var params = {};
        $.post('ConsultarCategoriaServlet', params, function (data) {
            if (data) {
                $scope.categorias = data;
                $("#goAngular").trigger('click');

            } else {
                swal("Ops!", "Nenhum item encontrado!", "Warning");
            }
        })
    }

    $scope.consultarItens = function () {
        var params = {};
        $.post('ConsultarItemServlet', params, function (data) {
            if (data) {
                $scope.parametrosTabela = data;
                $("#goAngular").trigger('click');

            } else {
                $scope.parametrosTabela = '';
                $("#goAngular").trigger('click');
                swal("Ops!", "Nenhum item encontrado!", "warning");
            }
        })
    }

    $scope.SelecionarItem = function (item) {
        $scope.itemSelecionado = item;
    }

    $scope.adicionarItem = function () {
        $scope.itemSelecionado = {};
//        window.location.href = "#!cadastroitem"
        $scope.exibir = false;
        $scope.botaoClicado = 'incluir';
    }

    $scope.visualizarItem = function (item) {
        $scope.itemSelecionado = item;
        $scope.itemSelecionado.nomeItem = item.nome;
        $scope.itemSelecionado.categoria = item.idCategoria;
        $scope.itemSelecionado.imagem = item.imagem;
        $scope.exibir = false;
        $scope.botaoClicado = 'visualizar';
    }

    $scope.cancelarEdicao = function () {
        $scope.itemSelecionado = '';
//        window.location.href = "#!cadastroitem"
        $scope.exibir = true;
        $scope.botaoClicado = 'cancelar';
    }

    $scope.editarItem = function (item) {

        $scope.itemSelecionado = item;
        $scope.itemSelecionado.nomeItem = item.nome;
        $scope.itemSelecionado.categoria = item.idCategoria;
        $scope.itemSelecionado.imagem = item.imagem;
        $scope.botaoClicado = 'alterar';
        $scope.exibir = false;

    }

    $scope.excluirItem = function (idItem) {
        $scope.itemSelecionado.id = idItem;
        swal({
            title: 'Excluir item?',
            text: "você tem certeza que deseja excluir este item?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'SIM',
            cancelButtonText: 'NÃO'
        }, function () {
            var params = {};
            params.idItem = $scope.itemSelecionado.id;
            params.idImagem = $scope.itemSelecionado.idImagem;

            $.post('ExcluirItemServlet', params, function (data) {
                if (data) {
                    swal({
                        title: "Exclusão Realizada com sucesso!",
                        timer: 3000,
                        showConfirmButton: true,
                        type: "success"

                    });
                    setTimeout(function () {
                        $scope.exibir = true;
                        $scope.consultarItens();
                    }, 3000);
                } else {
                    swal("Ops!", "Falha ao excluir item!", "error");
                    $scope.consultarItens();
                }
            })
        });
    }

    $scope.limparImagem = function () {

        $scope.itemSelecionado.imagem = "";
        $("#goAngular").trigger('click');
    }

    $scope.adicionarArquivo = function (element) {
        var reader = new FileReader();
        reader.readAsDataURL(element.files[0]);

        setTimeout(function () {
            $scope.itemSelecionado.imagem = reader.result;
            $("#goAngular").trigger('click');
        }, 1000);

    };

    $scope.salvarItem = function () {
        if ($scope.botaoClicado == 'incluir') {
            if ($scope.itemSelecionado.nomeItem &&
                    $scope.itemSelecionado.categoria &&
                    $scope.itemSelecionado.preco) {
                var params = {};
                params.nome = $scope.itemSelecionado.nomeItem;
                params.preco = $scope.itemSelecionado.preco;
                params.observacoes = $scope.itemSelecionado.observacoes;
                params.imagem = $scope.itemSelecionado.imagem;
                params.categoria = $scope.itemSelecionado.categoria;
                $.post('CadastroItemServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Cadastro Realizado com sucesso!",
                            timer: 3000,
                            showConfirmButton: true,
                            type: "success"

                        });
                        setTimeout(function () {
                            $scope.exibir = true;
                            $scope.consultarItens();
                        }, 3000);
                    } else {
                        swal("Oops", "Ocorreu um erro durante o cadastro do item.", "error");
                    }
                })

            } else {
                swal("Oops", "Campos obrigatórios não foram preenchidos.", "warning");
            }
        } else {
            if ($scope.itemSelecionado.nomeItem &&
                    $scope.itemSelecionado.categoria &&
                    $scope.itemSelecionado.preco &&
                    $scope.itemSelecionado.id) {
                var params = {};
                params.idItem = $scope.itemSelecionado.id;
                params.nome = $scope.itemSelecionado.nomeItem;
                params.preco = $scope.itemSelecionado.preco;
                params.observacoes = $scope.itemSelecionado.observacoes;
                params.imagem = $scope.itemSelecionado.imagem;
                params.categoria = $scope.itemSelecionado.categoria;
                params.idImagem = $scope.itemSelecionado.idImagem;
                $.post('EditarItemServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Cadastro Atualizado com sucesso!",
                            timer: 2000,
                            showConfirmButton: true,
                            type: "success"

                        });
                        setTimeout(function () {
                            $scope.exibir = true;
                            $scope.consultarItens();
                        }, 2000);
                    } else {
                        swal("Oops", "Ocorreu um erro durante a alteração do item.", "error");
                    }
                })

            } else {
                swal("Oops", "Campos obrigatórios não foram preenchidos.", "warning");
            }
        }


    }

});