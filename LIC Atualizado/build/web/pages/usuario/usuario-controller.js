
var app = angular.module('myApp').controller('UsuarioController', function ($scope, $http) {

    $scope.itemSelecionado = {};
    $scope.parametrosTabela = {};
    $scope.exibir = true;
    $scope.exibirEndereco = false;
    $scope.botaoClicadoEndereco = '';

    $scope.cancelarCadastroUsuario = function () {
//ele ta considerando a tela de usuario quando deveria ta lendo o index sem o usuario/index
        window.location.href = "../../index.html";
    };

    $scope.cadastrarUsuario = function () {

        if ($scope.itemSelecionado.login &&
                $scope.itemSelecionado.senha &&
                $scope.itemSelecionado.nome &&
                $scope.itemSelecionado.email &&
                $scope.itemSelecionado.telefone &&
                $scope.itemSelecionado.sexo) {

            var params = {};
            params.login = $scope.itemSelecionado.login;
            params.senha = $scope.itemSelecionado.senha;
            params.nome = $scope.itemSelecionado.nome;
            params.email = $scope.itemSelecionado.email;
            params.telefone = $scope.itemSelecionado.telefone;
            params.sexo = $scope.itemSelecionado.sexo;
            $.post('../../CadastroUsuarioServlet', params, function (data) {
                if (data) {

                    swal({
                        title: "Cadastro Realizado com sucesso!",
                        timer: 2000,
                        showConfirmButton: true,
                        type: "success"

                    });
                    setTimeout(function () {
                        window.location.href = "../../index.html";
                    }, 2000);
                } else {
                    swal("Ops!", "Falha ao cadastrar usuário, tente novamente!", "error");
                }
            })

        } else {
            swal("ops!", "Preencha os campos obrigatórios", "warning");
        }
    }

    $scope.consultarUsuarios = function () {
        var params = {};
        $.post('ConsultarUsuarioServlet', params, function (data) {
            if (data) {
                $scope.parametrosTabela = data;
                $("#goAngular").trigger('click');
            } else {
                swal("Ops!", "Nenhum item encontrado!", "Warning");
            }
        })
    }

    $scope.SelecionarUsuario = function (item) {
        $scope.itemSelecionado = item;
    }

    $scope.adicionarUsuario = function () {
        $scope.itemSelecionado = {};
        $scope.exibir = false;
        $scope.botaoClicado = 'incluir';
    }

    $scope.visualizarUsuario = function (item) {
        $scope.itemSelecionado = item;
        $scope.itemSelecionado.tipoUsuario = item.descricaoTipoUsuario;
        $scope.exibir = false;
        $scope.botaoClicado = 'visualizar';
    }

    $scope.cancelarEdicao = function () {
        $scope.itemSelecionado = '';
        $scope.exibir = true;
        $scope.itemSelecionado.editavel = false;
        $scope.botaoClicado = 'cancelar';
    }

    $scope.editarUsuario = function (item) {

        $scope.itemSelecionado = item;
        $scope.itemSelecionado.tipoUsuario = item.descricaoTipoUsuario;
        $scope.botaoClicado = 'alterar';
        $scope.exibir = false;
    }

    $scope.excluirUsuario = function (idUsuario) {
        $scope.itemSelecionado.id = idUsuario;
        swal({
            title: 'Excluir Usuário?',
            text: "você tem certeza que deseja excluir este Usuário?",
            type: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'SIM',
            cancelButtonText: 'NÃO'
        }, function () {
            var params = {};
            params.idUsuario = $scope.itemSelecionado.id;
            $.post('ExcluirUsuarioServlet', params, function (data) {
                if (data) {
                    swal({
                        title: "Exclusão Realizada com sucesso!",
                        timer: 2000,
                        showConfirmButton: true,
                        type: "success"

                    });
                    setTimeout(function () {
                        $scope.exibir = true;
                        $scope.consultarUsuarios();
                    }, 2000);
                } else {
                    swal("Ops!", "Falha ao excluir idUsuario!", "error");
                    $scope.consultarUsuarios();
                }
            })
        });
    }

    $scope.salvarUsuario = function () {
        if ($scope.botaoClicado == 'incluir') {
            if ($scope.itemSelecionado.login &&
                    $scope.itemSelecionado.senha &&
                    $scope.itemSelecionado.confirmarSenha &&
                    $scope.itemSelecionado.nome &&
                    $scope.itemSelecionado.email &&
                    $scope.itemSelecionado.telefone &&
                    $scope.itemSelecionado.sexo &&
                    $scope.itemSelecionado.tipoUsuario) {
                var params = {};
                params.login = $scope.itemSelecionado.login;
                params.senha = $scope.itemSelecionado.senha;
                params.nome = $scope.itemSelecionado.nome;
                params.email = $scope.itemSelecionado.email;
                params.telefone = $scope.itemSelecionado.telefone;
                params.sexo = $scope.itemSelecionado.sexo;
                params.tipoUsuario = $scope.itemSelecionado.tipoUsuario;
                $.post('CadastroUsuarioServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Cadastro Realizado com sucesso!",
                            timer: 2000,
                            showConfirmButton: true,
                            type: "success"

                        });
                        setTimeout(function () {
                            $scope.exibir = true;
                            $scope.consultarUsuarios();
                        }, 2000);
                    } else {
                        swal("Oops", "Ocorreu um erro durante o cadastro do Usuário.", "error");
                    }
                })

            } else {
                swal("Oops", "Campos obrigatórios não foram preenchidos.", "warning");
            }
        } else {
            if ($scope.itemSelecionado.login &&
                    $scope.itemSelecionado.nome &&
                    $scope.itemSelecionado.email &&
                    $scope.itemSelecionado.telefone &&
                    $scope.itemSelecionado.sexo &&
                    $scope.itemSelecionado.senha &&
                    $scope.itemSelecionado.confirmarSenha &&
                    $scope.itemSelecionado.tipoUsuario &&
                    $scope.itemSelecionado.id) {
                var params = {};
                params.login = $scope.itemSelecionado.login;
                params.senha = $scope.itemSelecionado.senha;
                params.nome = $scope.itemSelecionado.nome;
                params.email = $scope.itemSelecionado.email;
                params.telefone = $scope.itemSelecionado.telefone;
                params.sexo = $scope.itemSelecionado.sexo;
                params.tipoUsuario = $scope.itemSelecionado.tipoUsuario;
                params.idUsuario = $scope.itemSelecionado.id;
                $.post('EditarUsuarioServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Cadastro Atualizado com sucesso!",
                            timer: 2000,
                            showConfirmButton: true,
                            type: "success"

                        });
                        setTimeout(function () {
                            $scope.exibir = true;
                            $scope.consultarUsuarios();
                        }, 2000);
                    } else {
                        swal("Oops", "Ocorreu um erro durante a alteração do Usuário.", "error");
                    }
                })

            } else {
                swal("Oops", "Campos obrigatórios não foram preenchidos.", "warning");
            }
        }


    }


//Métodos Endereço

    $scope.incluirEnderecoUsuario = function () {
        $scope.itemSelecionado.endereco = {};
        $scope.botaoClicadoEndereco = 'incluir';
    }

    $scope.editarEnderecoUsuario = function (item) {
        $scope.itemSelecionado.endereco = item;
        $scope.botaoClicadoEndereco = 'alterar';
        $scope.exibir = false;
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
                params.idUsuario = $scope.itemSelecionado.id;
                $.post('CadastroEnderecoUsuarioServlet', params, function (data) {
                    if (data) {
                        swal({
                            title: "Cadastro Realizado com sucesso!",
                            timer: 3000,
                            showConfirmButton: true,
                            type: "success"

                        });

                        var idUsuario = $scope.itemSelecionado.id;
                        $scope.consultarUsuarios();

                        setTimeout(function () {
                            var idnovoEndereco;
                            for (x in $scope.parametrosTabela) {
                                if (idUsuario == $scope.parametrosTabela[x].id) {
                                    idnovoEndereco = $scope.parametrosTabela[x].Endereco_idEndereco;
                                }
                            }
                            if (idnovoEndereco && idnovoEndereco > 0) {
                                $scope.consultarEnderecoUsuario(idnovoEndereco);
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
                    var idUsuario = $scope.itemSelecionado.id;
                    $scope.consultarUsuarios();

                    setTimeout(function () {
                        var idnovoEndereco;
                        for (x in $scope.parametrosTabela) {
                            if (idUsuario == $scope.parametrosTabela[x].id) {
                                idnovoEndereco = $scope.parametrosTabela[x].Endereco_idEndereco;
                            }
                        }
                        if (idnovoEndereco && idnovoEndereco > 0) {
                            $scope.consultarEnderecoUsuario(idnovoEndereco);
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
                $scope.consultarEnderecoUsuario($scope.itemSelecionado.Endereco_idEndereco);

            } else {
                $scope.itemSelecionado.endereco = {};
            }

            $scope.botaoClicadoEndereco = 'visualizar';


        } else {
            $scope.botaoClicadoEndereco = '';
        }
    }

    $scope.consultarEnderecoSupermercado = function (idUsuario) {
        var params = {};
        params.Endereco_idEndereco = idUsuario;

        $.post('ConsultarListaEnderecoUsuarioServlet', params, function (data) {
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

    $scope.excluirEnderecoUsuario = function (id, idUsuario) {
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
            params.idUsuario = idUsuario;
            $.post('ExcluirEnderecoUsuarioServlet', params, function (data) {
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

    $scope.consultarEnderecoUsuario = function (idUsuario) {
        var params = {};
        params.Endereco_idEndereco = idUsuario;

        $.post('ConsultarListaEnderecoUsuarioServlet', params, function (data) {
            if (data) {

                $scope.itemSelecionado.endereco = {};
                $scope.itemSelecionado.endereco.idUsuario = $scope.itemSelecionado.id;
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

});
