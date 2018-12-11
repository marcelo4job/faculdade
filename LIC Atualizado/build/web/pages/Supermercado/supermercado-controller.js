
var app = angular.module('myApp').controller('SupermercadoController', function ($scope, $http) {

    $scope.itemSelecionado = {};
    $scope.parametrosTabela = {};
    $scope.botaoClicado = undefined;
    $scope.exibir = true;
    $scope.exibirEndereco = false;
    $scope.botaoClicadoEndereco = '';

    $scope.ativaDesativaEndereco = function (supermercado) {
        $scope.exibirEndereco = !$scope.exibirEndereco;
        $scope.exibir = !$scope.exibir;
        if (supermercado) {
            $scope.itemSelecionado = supermercado;
        } else {
            $scope.itemSelecionado = {};
        }
        if ($scope.exibirEndereco) {

            if ($scope.itemSelecionado.Endereco_idEndereco && $scope.itemSelecionado.Endereco_idEndereco > 0) {
                $scope.consultarEnderecoSupermercado($scope.itemSelecionado.Endereco_idEndereco);

            } else {
                $scope.itemSelecionado.endereco = {};
            }

            $scope.botaoClicadoEndereco = 'visualizar';


        } else {
            $scope.botaoClicadoEndereco = '';
        }
    }

    $scope.salvarEndereco = function () {

        if ($scope.botaoClicadoEndereco == 'incluir') {
            if ($scope.itemSelecionado.endereco.logradouro ||
                    $scope.itemSelecionado.endereco.bairro ||
                    $scope.itemSelecionado.endereco.cep) {

                var params = {};
                params.Bairro = $scope.itemSelecionado.endereco.bairro;
                params.Cep = $scope.itemSelecionado.endereco.cep;
                params.Complemento = $scope.itemSelecionado.endereco.complemento;
                params.Pais = 'Brasil';
                params.Logradouro = $scope.itemSelecionado.endereco.logradouro;
                params.Numero = $scope.itemSelecionado.endereco.numero;
                params.UF = $scope.itemSelecionado.endereco.uf;
                params.localidade = $scope.itemSelecionado.endereco.localidade;
                params.idSupermercado = $scope.itemSelecionado.id;
                $.post('CadastroEnderecoServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Cadastro Realizado com sucesso!",
                            timer: 3000,
                            showConfirmButton: true,
                            type: "success"

                        });
                        var idSupermercado = $scope.itemSelecionado.id;
                        $scope.consultarSupermercados();
                        setTimeout(function () {
                            var idnovoEndereco;
                            for (x in $scope.parametrosTabela) {
                                if (idSupermercado == $scope.parametrosTabela[x].id) {
                                    idnovoEndereco = $scope.parametrosTabela[x].Endereco_idEndereco;
                                }
                            }
                            if (idnovoEndereco && idnovoEndereco > 0) {
                                $scope.consultarEnderecoSupermercado(idnovoEndereco);
                            }
                            $scope.botaoClicadoEndereco = 'visualizar';
                            $("#goAngular").trigger('click');
                        }, 3000);
                    } else {
                        swal("Ops!", "Falha ao cadastrar Endereço!", "error");
                    }
                })
            } else {
                swal("ops!", "Preencha os campos obrigatórios", "warning");
            }
        } else {
            var params = {};
            if ($scope.itemSelecionado.endereco.idEndereco) {
                params.idEndereco = $scope.itemSelecionado.endereco.idEndereco;
            } else {
                params.idEndereco = $scope.itemSelecionado.Endereco_idEndereco;
            }
            params.Bairro = $scope.itemSelecionado.endereco.bairro;
            params.Cep = $scope.itemSelecionado.endereco.cep;
            params.Complemento = $scope.itemSelecionado.endereco.complemento;
            params.Localidade = $scope.itemSelecionado.endereco.localidade;
            params.Logradouro = $scope.itemSelecionado.endereco.logradouro;
            params.Numero = $scope.itemSelecionado.endereco.numero;
            params.UF = $scope.itemSelecionado.endereco.uf;
            $.post('EditarEnderecoServlet', params, function (data) {
                if (data) {
                    swal({
                        title: "Edição Realizada com sucesso!",
                        timer: 3000,
                        showConfirmButton: true,
                        type: "success"

                    });
                    var idSupermercado = $scope.itemSelecionado.id;
                    $scope.consultarSupermercados();

                    setTimeout(function () {
                        var idnovoEndereco;
                        for (x in $scope.parametrosTabela) {
                            if (idSupermercado == $scope.parametrosTabela[x].id) {
                                idnovoEndereco = $scope.parametrosTabela[x].Endereco_idEndereco;
                            }
                        }
                        if (idnovoEndereco && idnovoEndereco > 0) {
                            $scope.consultarEnderecoSupermercado(idnovoEndereco);
                        }
                        $scope.botaoClicadoEndereco = 'visualizar';
                        $("#goAngular").trigger('click');
                    }, 3000);
                } else {
                    swal("Ops!", "Falha ao editar Endereço!", "error");
                }
            })
        }
    };

    $scope.consultarEnderecoSupermercado = function (idSupermercado) {
        var params = {};
        params.Endereco_idEndereco = idSupermercado;

        $.post('ConsultarListaEnderecoSupermercadoServlet', params, function (data) {
            if (data) {

                $scope.itemSelecionado.endereco = {};
                $scope.itemSelecionado.endereco.idSupermercado = $scope.itemSelecionado.id;
                $scope.itemSelecionado.endereco.idEndereco = data.id;
                $scope.itemSelecionado.endereco.cep = data.Cep;
                $scope.itemSelecionado.endereco.logradouro = data.Logradouro;
                $scope.itemSelecionado.endereco.complemento = data.Complemento;
                $scope.itemSelecionado.endereco.numero = data.Numero;
                $scope.itemSelecionado.endereco.bairro = data.Bairro;
                $scope.itemSelecionado.endereco.localidade = data.Localidade;
                $scope.itemSelecionado.endereco.uf = data.UF;
                $("#goAngular").trigger('click');

            } else {
                swal("Ops!", "Nenhum item encontrado!", "Warning");
            }
        });
    }

    $scope.incluirSupermercado = function () {
        $scope.itemSelecionado = {};
        $scope.itemSelecionado.editavel = true;
        $scope.botaoClicado = 'incluir';
        $scope.exibir = false;
    }

    $scope.editarSupermercado = function (item) {
        $scope.itemSelecionado = item;
        $scope.itemSelecionado.editavel = true;
        $scope.botaoClicado = 'alterar';
        $scope.exibir = false;
    }

    $scope.visualizarSupermercado = function (item) {
        $scope.itemSelecionado = item;
        $scope.exibir = false;
        $scope.botaoClicado = 'visualizar';
    }


    $scope.consultarSupermercados = function () {
        var params = {};
        $.post('ConsultarSupermercadoServlet', params, function (data) {
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
        $scope.itemSelecionado.editavel = false;
    }

    $scope.selecionarSupermercado = function (item) {
        $scope.itemSelecionado = item;
        $scope.exibir = true;
    }

    $scope.salvarSupermercado = function () {

        if ($scope.botaoClicado == 'incluir') {
            if ($scope.itemSelecionado.RazaoSocialSupermercado && $scope.itemSelecionado.cnpjSupermercado) {

                var params = {};
                params.RazaoSocialSupermercado = $scope.itemSelecionado.RazaoSocialSupermercado;
                params.NomeFantasiaSupermercado = $scope.itemSelecionado.NomeFantasiaSupermercado;
                params.cnpjSupermercado = $scope.itemSelecionado.cnpjSupermercado;
                if ($scope.itemSelecionado.selecionado) {

                    if (!$scope.itemSelecionado.endereco.cep ||
                            !$scope.itemSelecionado.endereco.logradouro ||
                            !$scope.itemSelecionado.endereco.bairro) {

                        swal("Ops!", "O endereco está incorreto!", "error");
                    } else {
                        params.cep = $scope.itemSelecionado.endereco.cep.replace('.', '').replace('.', '').replace('-', '');
                        params.complemento = $scope.itemSelecionado.endereco.complemento;
                        params.logradouro = $scope.itemSelecionado.endereco.logradouro;
                        params.bairro = $scope.itemSelecionado.endereco.bairro;
                        params.localidade = $scope.itemSelecionado.endereco.localidade;
                        params.uf = $scope.itemSelecionado.endereco.uf;
                        params.numero = $scope.itemSelecionado.endereco.numero;
                        params.selecionado = $scope.itemSelecionado.selecionado;
                    }
                }

// Cadastro de Supermercado
                $.post('CadastroSupermercadoServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Cadastro Realizado com sucesso!",
                            timer: 3000,
                            showConfirmButton: true,
                            type: "success"

                        });
                        setTimeout(function () {
                            $scope.exibir = true;
                            $scope.consultarSupermercados();
                        }, 3000);
                    } else {
                        swal("Ops!", "Falha ao cadastrar Supermercado!", "error");
                    }
                })
            } else {
                swal("ops!", "Preencha os campos obrigatórios", "warning");
            }
        } else {

            var params = {};
            params.idSupermercado = $scope.itemSelecionado.id;
            params.RazaoSocialSupermercado = $scope.itemSelecionado.RazaoSocialSupermercado;
            params.NomeFantasiaSupermercado = $scope.itemSelecionado.NomeFantasiaSupermercado;
            params.cnpjSupermercado = $scope.itemSelecionado.cnpjSupermercado;
            $.post('EditarSupermercadoServlet', params, function (data) {
                if (data) {
                    swal({
                        title: "Edição Realizada com sucesso!",
                        timer: 3000,
                        showConfirmButton: true,
                        type: "success"

                    });
                    setTimeout(function () {
                        $scope.exibir = true;
                        $scope.consultarSupermercados();
                    }, 3000);
                } else {
                    swal("Ops!", "Falha ao editar Supermercado!", "error");
                }
            })
        }


    }

    $scope.excluirSupermercado = function (id) {
        swal({
            title: 'Excluir supermercado?',
            text: "você tem certeza que deseja excluir esta supermercado?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'sim',
            cancelButtonText: 'cancelar'
        }, function () {
            var params = {};
            params.idSupermercado = id;
            $.post('ExcluirSupermercadoServlet', params, function (data) {
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
                        $scope.consultarSupermercados();
                    }, 3000);
                } else {
                    swal("Ops!", "Falha ao excluir Supermercado!", "error");
                }
            })
        });
    }

//Métodos Endereço

    $scope.incluirEnderecoSupermercado = function () {
        $scope.itemSelecionado.endereco = {};
        $scope.botaoClicadoEndereco = 'incluir';
    }

    $scope.editarEnderecoSupermercado = function (item) {
        $scope.itemSelecionado.endereco = item;
        $scope.botaoClicadoEndereco = 'alterar';
        $scope.exibir = false;
    }

    $scope.cancelarEdicaoEndereco = function () {

        $scope.botaoClicadoEndereco = 'visualizar';
        $scope.itemSelecionado.endereco = {};
        $scope.exibirEndereco = false;
        $scope.exibir = true;

    }

    $scope.consultarEndereco = function () {
        var params = {};
        $.post('ConsultarEnderecoServlet', params, function (data) {
            if (data) {
                $scope.parametrosTabela = data;
                $("#goAngular").trigger('click');
            } else {
                swal("Ops!", "Nenhum item encontrado!", "Warning");
            }
        })
    }

    $scope.excluirEnderecoSupermercado = function (id, idSupermercado) {
        swal({
            title: 'Excluir endereço?',
            text: "você tem certeza que deseja excluir esta endereço?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'sim',
            cancelButtonText: 'cancelar'
        }, function () {
            var params = {};
            params.idEndereco = id;
            params.idSupermercado = idSupermercado;
            $.post('ExcluirEnderecoServlet', params, function (data) {
                if (data) {
                    swal({
                        title: "Exclusão Realizada com sucesso!",
                        timer: 3000,
                        showConfirmButton: true,
                        type: "success"

                    });
                    $scope.itemSelecionado.endereco = {};
                    $("#goAngular").trigger('click');
                } else {
                    swal("Ops!", "Falha ao excluir endereço!", "error");
                }
            })
        });
    }

    $scope.buscarCep = function (cep) {
        if (!cep)
            return false;
        $scope.itemSelecionado.endereco = {};
        $scope.itemSelecionado.endereco.cep = cep;
        if (cep.length != 8) {
            swal("Oops", "Informe um CEP válido!", "warning");
        } else {
            $http.get('https://viacep.com.br/ws/' + cep + '/json/').then(function (response) {

                if (response && response.status == 200) {
                    $scope.itemSelecionado.endereco.complemento = response.data.complemento;
                    $scope.itemSelecionado.endereco.logradouro = response.data.logradouro;
                    $scope.itemSelecionado.endereco.bairro = response.data.bairro;
                    $scope.itemSelecionado.endereco.localidade = response.data.localidade;
                    $scope.itemSelecionado.endereco.uf = response.data.uf;
                } else {
                    swal("Oops", "Falha ao consultar CEP!", "warning");
                }
            }, function (err) {
                console.log(err);
            });
        }
    };
    $scope.buscarCnpj = function (cnpjReplace) {
        if (!cnpjReplace)
            return false;
//        cnpj.replace(".","").replace(".","").replace(".","").replace("/","").replace("-","");
        var cnpj = cnpjReplace.replace('.', '').replace('.', '').replace('.', '').replace('/', '').replace('-', '');
        $scope.itemSelecionado.cnpjSupermercado = cnpj;
        $scope.itemSelecionado.endereco = {};
        if (!cnpj) {
            swal("Oops", "Informe um CNPJ válido!", "warning");
        } else {
            $.ajax({
                url: 'https://receitaws.com.br/v1/cnpj/' + cnpj,
                data: cnpj,
                type: 'GET',
                crossDomain: true,
                dataType: 'jsonp',
                success: function (data) {
                    if (data) {
                        $scope.itemSelecionado.cnpjSupermercado = data.cnpj;
                        $scope.itemSelecionado.RazaoSocialSupermercado = data.nome;
                        $scope.itemSelecionado.endereco.cep = data.cep;
                        $scope.itemSelecionado.endereco.numero = data.numero;
                        $("#goAngular").trigger('click');
//                        $scope.itemSelecionado.endereco.logradouro = data.logradouro;
//                        $scope.itemSelecionado.endereco.complemento = data.complemento;
//                        $scope.itemSelecionado.endereco.numero = data.numero;
//                        $scope.itemSelecionado.endereco.bairro = data.bairro;
//                        $scope.itemSelecionado.endereco.localidade = data.municipio;
//                        $scope.itemSelecionado.endereco.uf = data.uf;

                        var cep = $scope.itemSelecionado.endereco.cep.replace('.', '').replace('.', '').replace('-', '');
                        if (cep) {

                            $http.get('https://viacep.com.br/ws/' + cep + '/json/').then(function (response) {

                                if (response && response.status === 200) {
                                    $scope.itemSelecionado.endereco.complemento = response.data.complemento;
                                    $scope.itemSelecionado.endereco.logradouro = response.data.logradouro;
                                    $scope.itemSelecionado.endereco.bairro = response.data.bairro;
                                    $scope.itemSelecionado.endereco.localidade = response.data.localidade;
                                    $scope.itemSelecionado.endereco.uf = response.data.uf;
                                } else {
                                    swal("Oops", "Falha ao consultar CEP!", "warning");
                                }
                            }, function (err) {
                                console.log(err);
                            });
                        }


                    }
                },
                error: function () {
                    swal("Oops", "Excesso de consultas por minuto!", "warning");
                },
            });
        }
    };
    $scope.voltarEndereco = function () {
        $scope.exibirEndereco = !$scope.exibirEndereco;
        $scope.editavelendereco = false;
//        $scope.exibir = !$scope.exibir;
        if ($scope.exibirEndereco) {
            $scope.botaoClicadoEndereco = 'visualizar';
        } else {
            $scope.botaoClicadoEndereco = '';
        }
    };
}
);

