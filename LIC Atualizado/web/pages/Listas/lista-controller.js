
var app = angular.module('myApp').controller('ListaController', function ($scope, $rootScope) {

    $scope.itemSelecionado = {};
    $scope.parametrosTabela = {};
    $scope.itens = {};
    $scope.PrecoTotalLista = 0.00;
    $scope.botaoClicado = '';
    $scope.exibir = true;
    $scope.incluirLista = function () {
        $scope.itemSelecionado = {};
        $scope.itemSelecionado.nomeUsuario = localStorage.idUsuario;
        $scope.itemSelecionado.tipoUsuario = localStorage.idTipoUsuario;
        $scope.itemSelecionado.DescricaoTipoUsuario = localStorage.DescricaoTipoUsuario;
        $scope.botaoClicado = 'incluir';
        $scope.exibir = false;
    }

    $scope.consultarItensLista = function () {

        var params = {};
        $.post('ConsultarItemServlet', params, function (data) {
            if (data) {
                $scope.itens = data;
//                for (x in $scope.parametrosTabelaItens) {
//                    for (var i = 0; i < $scope.itens.length; i++) {
//                        if ($scope.parametrosTabelaItens[x].idItem == $scope.itens[i].id) {
//                            $scope.parametrosTabelaItens[x].descricaoProduto = $scope.itens[i].nome;
//                            $scope.parametrosTabelaItens[x].nome = $scope.itens[i].nome;
//                        }
//                    }
//                    $("#goAngular").trigger('click');
//                }
                $("#goAngular").trigger('click');
            }
        })

        var params = {};
        params.idLista = localStorage.lista;
        $.post('ConsultarItensLista', params, function (data) {
            if (data) {
                $scope.parametrosTabelaItens = data;
                $("#goAngular").trigger('click');
                var contador = 1;
                for (x in $scope.parametrosTabelaItens) {
                    $scope.parametrosTabelaItens[x].id = contador;
                    $scope.calcularPrecoItem($scope.parametrosTabelaItens[x])
                    $("#goAngular").trigger('click');
                    contador = contador + 1;
                }

                $scope.consultarPrecoTotal();
            }
        })
    }

    $scope.consultarPrecoTotal = function () {

        var params = {};
        params.idLista = localStorage.lista;
        $.post('ConsultarPrecoTotalLista', params, function (data) {
            if (data) {
                $scope.PrecoTotalLista = data.precoTotalLista;
                $("#goAngular").trigger('click');
            }
        })

    }

    $scope.gerenciarLista = function (Lista) {
        localStorage.lista = Lista.idLista;
//        $scope.consultarItensLista();
    }

    $scope.editarLista = function (item) {
        $scope.itemSelecionado = item;
        $scope.itemSelecionado.nomeUsuario = localStorage.idUsuario;
        $scope.itemSelecionado.tipoUsuario = localStorage.idTipoUsuario;
        $scope.itemSelecionado.DescricaoTipoUsuario = localStorage.DescricaoTipoUsuario;
        $scope.botaoClicado = 'alterar';
        $scope.exibir = false;
    }

    $scope.ConsultaTipoUsuario = function () {
        var params = {};
        params.idTipoUsuario = localStorage.idTipoUsuario;
        $.post('ConsultarDescricaoTipoUsuarioServlet', params, function (data) {
            if (data) {
                localStorage.DescricaoTipoUsuario = data;
                $("#goAngular").trigger('click');
            }
        })
    }

    $scope.consultarSupermercados = function () {
        var params = {};
        $.post('ConsultarSupermercadoServlet', params, function (data) {
            if (data) {
                $scope.supermercados = data;
                $("#goAngular").trigger('click');
            }
        })
    }

    $scope.consultarListas = function () {
        var params = {};
        params.idUsuario = localStorage.idUsuario;

        setTimeout(function () {
            params.DescricaoUsuario = localStorage.DescricaoTipoUsuario;

            $.post('ConsultarListasServlet', params, function (data) {
                if (data) {
                    $scope.parametrosTabela = data;
                    $("#goAngular").trigger('click');
                } else {
                    $scope.parametrosTabela = {};
                    $("#goAngular").trigger('click');
                    swal("Ops!", "Nenhum item encontrado!", "warning");
                }
            })
        }, 0500);

    };
    $scope.parametrosTabelaItens = [];
    $scope.incluirItem = function (produto) {

        if (!produto.nome || !produto.preco || !produto.quantidade) {
            swal("Ops!", "Campos obrigatórios não foram preenchidos!", "warning");
        } else {
            if (produto.preco > 0 && produto.quantidade > 0) {
                if (produto.quantidade.toString().indexOf(".") == -1) {
                    for (var i = 0; i < $scope.itens.length; i++) {
                        if (produto.nome == $scope.itens[i].id) {
                            produto.nome = $scope.itens[i].nome;
                            produto.idItem = $scope.itens[i].id;
                        }
                    }
                    ;
                    $scope.parametrosTabelaItens.push(produto);
                    produto = {};

                    var contador = 1;

                    for (x in $scope.parametrosTabelaItens) {
                        $scope.parametrosTabelaItens[x].id = contador;
                        contador = contador + 1;
                    }

                    $scope.exibirLista = !$scope.exibirLista;
                } else {
                    swal("Ops!", "Informe um valor inteiro em quantidade!", "warning");
                }
            } else {
                swal("Ops!", "Informe números positivos!", "warning");
            }
        }
    }

    $scope.consultarItens = function () {
        var params = {};
        $.post('ConsultarItemServlet', params, function (data) {
            if (data) {
                $scope.parametrosTabelaItem = data;
                $("#goAngular").trigger('click');
                for (var i = 0; i < $scope.parametrosTabelaItem.length; i++) {
                    $('#produtos').append("<option  value='" + $scope.parametrosTabelaItem[i].nome + "'>");
                }
                ;
            } else {
                $scope.parametrosTabelaItem = [];
                $("#goAngular").trigger('click');
                swal("Ops!", "Nenhum item encontrado!", "warning");
            }
        })
    }


    $scope.cancelarEdicao = function () {
        $scope.exibir = true;
    }

    $scope.selecionarLista = function (item) {
        $scope.itemSelecionado = item;
        $scope.exibir = true;
    }

    $scope.salvarLista = function () {

        if ($scope.botaoClicado == 'incluir') {
            if ($scope.itemSelecionado.nome &&
                    $scope.itemSelecionado.nomeUsuario &&
                    $scope.itemSelecionado.tipoUsuario) {

                var params = {};
                params.nome = $scope.itemSelecionado.nome;
                params.idUsuario = $scope.itemSelecionado.nomeUsuario;
                params.idTipoUsuario = $scope.itemSelecionado.tipoUsuario;
                if ($scope.itemSelecionado.idSupermercado) {
                    params.idSupermercado = $scope.itemSelecionado.idSupermercado;
                }
                $.post('CadastroListaServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Cadastro Realizado com sucesso!",
                            timer: 3000,
                            showConfirmButton: true,
                            type: "success"

                        });
                        setTimeout(function () {
                            $scope.exibir = true;
                            $scope.consultarListas();
                        }, 3000);
                    } else {
                        swal("Ops!", "Falha ao cadastrar Lista!", "error");
                    }
                })
            } else {
                swal("ops!", "Preencha os campos obrigatórios", "warning");
            }
        } else {
            if ($scope.itemSelecionado.nome &&
                    $scope.itemSelecionado.nomeUsuario &&
                    $scope.itemSelecionado.tipoUsuario) {

                var params = {};
                params.nome = $scope.itemSelecionado.nome;
                params.idUsuario = $scope.itemSelecionado.nomeUsuario;
                params.idTipoUsuario = $scope.itemSelecionado.tipoUsuario;
                if ($scope.itemSelecionado.idSupermercado) {
                    params.idSupermercado = $scope.itemSelecionado.idSupermercado;
                }
                params.idLista = $scope.itemSelecionado.idLista;
                $.post('EditarListaServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Edição Realizada com sucesso!",
                            timer: 3000,
                            showConfirmButton: true,
                            type: "success"

                        });
                        setTimeout(function () {
                            $scope.exibir = true;
                            $scope.consultarListas();
                        }, 3000);
                    } else {
                        swal("Ops!", "Falha ao editar Lista!", "error");
                    }
                })
            } else {
                swal("Ops!", "Falha ao cadastrar Lista!", "error");
            }
        }


    }

    $scope.salvarListaItem = function () {
        var errorProduto = true;
        var params = {};
        var cont = 0;
        params.idLista = localStorage.lista;
        params.precoTotalLista = $scope.PrecoTotalLista;
        params.nomesItens = [];
        params.qtdItens = [];
        params.precoItens = [];
        params.idItens = [];
        for (x in $scope.parametrosTabelaItens) {
            if (errorProduto) {
                params.nomesItens[x] = $scope.parametrosTabelaItens[x].nome;
                params.qtdItens[x] = ($scope.parametrosTabelaItens[x].quantidade);
                params.precoItens[x] = ($scope.parametrosTabelaItens[x].preco);
                params.idItens[x] = parseInt(($scope.parametrosTabelaItens[x].idItem));
                cont = 0
                for (y in $scope.parametrosTabelaItens) {
                    if ($scope.parametrosTabelaItens[x].nome == $scope.parametrosTabelaItens[y].nome) {
                        cont++;
                        if (cont == 2) {
                            swal("Ops!", "A lista não pode conter o mesmo produto, sendo necessário alterar a quantidade!", "error");
                            errorProduto = false;
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }
        if (errorProduto) {
            $.post('SalvarItensLista', params, function (data) {
                if (data) {
                    swal({
                        title: "Lista atualizada com sucesso!",
                        timer: 3000,
                        showConfirmButton: true,
                        type: "success"

                    });
                    setTimeout(function () {
                        $scope.consultarItensLista();
                    }, 3000);
                } else {
                    swal("Ops!", "Falha ao salvar Lista!", "error");
                }
            })
        }
    }

    $scope.excluirLista = function (id) {
        swal({
            title: 'Excluir Lista?',
            text: "você tem certeza que deseja excluir esta Lista?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'sim',
            cancelButtonText: 'cancelar'
        }, function () {
            var params = {};
            params.idLista = id;
            $.post('ExcluirListaServlet', params, function (data) {
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
                        $scope.consultarListas();
                    }, 3000);
                } else {
                    swal("Ops!", "Falha ao excluir Lista!", "error");
                }
            })
        });
    }


// TELA DE ADICIONAR PRODUTO

//        if (produto.id == 1) {;
//            for (var i = 1; i <= $scope.parametrosTabelaItens.length; i++) {
//                if (i == 1) {
//                    $scope.parametrosTabelaItens.splice(0,1);
//                   break;
//                }
//            }
//        }

    $scope.excluirItemLista = function (produto) {

        for (var i = 0; i < $scope.parametrosTabelaItens.length; i++) {
            if (produto.id == $scope.parametrosTabelaItens[i].id) {
                $scope.parametrosTabelaItens.splice(i, 1);
                $scope.PrecoTotalLista = 0, 00;
                for (var i = 0; i < $scope.parametrosTabelaItens.length; i++) {
                    if ($scope.parametrosTabelaItens[i].selecionado) {
                        $scope.PrecoTotalLista = parseFloat($scope.PrecoTotalLista) + parseFloat($scope.parametrosTabelaItens[i].totalProduto);
                    }
                }
                $scope.PrecoTotalLista = Number(($scope.PrecoTotalLista).toFixed(2));
                //    $scope.PrecoTotalLista = PrecoTotalLista - produto.totalProduto;
                //    $scope.PrecoTotalLista = Number((item.PrecoTotalLista).toFixed(2));
                break;
            }
        }
    }

    $scope.limparProduto = function () {
        if ($scope.produto) {
            $scope.produto = {};
        }
    }

    $scope.exibirLista = true;
    $scope.exibirListaItem = function () {
        $scope.exibirLista = !$scope.exibirLista;
        $scope.produto = {};
    }

    $scope.calcularPrecoItem = function (item) {
        if (item.selecionado) {
            $scope.PrecoTotalLista = parseFloat($scope.PrecoTotalLista) - parseFloat(item.totalProduto);
            if (item.preco && item.quantidade) {
                item.totalProduto = (item.preco * item.quantidade);
                item.totalProduto = Number((item.totalProduto).toFixed(2));
                $scope.PrecoTotalLista = parseFloat($scope.PrecoTotalLista) + parseFloat(item.totalProduto);
            }
            $scope.PrecoTotalLista = Number(($scope.PrecoTotalLista).toFixed(2));
        } else {
            if (item.preco && item.quantidade) {
                item.totalProduto = (item.preco * item.quantidade);
                item.totalProduto = Number((item.totalProduto).toFixed(2));
            }
        }
    }


    $scope.produto = {};
    $scope.calcularValorTotal = function () {
        $scope.PrecoTotalLista = 0, 00;
        for (var i = 0; i < $scope.parametrosTabelaItens.length; i++) {
            if ($scope.parametrosTabelaItens[i].selecionado) {
                $scope.PrecoTotalLista = parseFloat($scope.PrecoTotalLista) + parseFloat($scope.parametrosTabelaItens[i].totalProduto);
            }
        }
        $scope.PrecoTotalLista = Number(($scope.PrecoTotalLista).toFixed(2));
    }

    $scope.calcularValorTotalTodos = function (selecionados) {
        $scope.PrecoTotalLista = 0, 00;
        if (!selecionados) {
            for (var i = 0; i < $scope.parametrosTabelaItens.length; i++) {
                $scope.parametrosTabelaItens[i].selecionado = true
                $scope.PrecoTotalLista = parseFloat($scope.PrecoTotalLista) + parseFloat($scope.parametrosTabelaItens[i].totalProduto);
            }
            $scope.PrecoTotalLista = Number(($scope.PrecoTotalLista).toFixed(2));
        } else {
            for (var i = 0; i < $scope.parametrosTabelaItens.length; i++) {
                $scope.parametrosTabelaItens[i].selecionado = false
            }
        }

    }

});
