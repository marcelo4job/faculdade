/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.CadastroUsuario;

/**
 *
 * @author Merel
 */
public class CadastroUsuarioDao {

    Conexao db = new Conexao();

    public CadastroUsuarioDao() {
        db.getInstacia();
    }

    public int inserirUsuario(CadastroUsuario novoUsuario) {
        Connection connection = db.conectar();

        String SQL = "INSERT INTO usuario (nome,Login,Senha,Email,telefone,sexo,TipoUsuario_idTipoUsuario) values";

        if (novoUsuario.getNome() != null) {
            SQL = SQL + "('" + novoUsuario.getNome() + "', ";
        }

        if (novoUsuario.getLogin() != null) {
            SQL = SQL + "'" + novoUsuario.getLogin() + "', ";
        }

        if (novoUsuario.getSenha() != null) {
            SQL = SQL + "'" + novoUsuario.getSenha() + "', ";
        }

        if (novoUsuario.getEmail() != null) {
            SQL = SQL + "'" + novoUsuario.getEmail() + "', ";
        }

        if (novoUsuario.getTelefone() != null) {
            SQL = SQL + "'" + novoUsuario.getTelefone() + "', ";
        }

        if (novoUsuario.getSexo() != null) {
            SQL = SQL + "'" + novoUsuario.getSexo() + "', ";
        }

        if (novoUsuario.getTipoUsuario() != 0) {
            SQL = SQL + novoUsuario.getTipoUsuario() + ") ";
        }

        int u = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            u = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return u;
        }
    }

    public ArrayList<CadastroUsuario> consultarUsuarios() {
        Connection connection = db.conectar();

        String SQL = "select usuario.*, tipousuario.Descricao  from usuario\n"
                + "inner join tipousuario on tipousuario.idTipoUsuario = usuario.TipoUsuario_idTipoUsuario";
        SQL = SQL + " WHERE idUsuario != 0 ";

        CadastroUsuario u = null;
        ArrayList listaUsuarios = new ArrayList();

        try {
            PreparedStatement pstm = connection.prepareCall(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new CadastroUsuario();
                u.setId(rs.getInt("idUsuario"));
                u.setTipoUsuario(rs.getInt("TipoUsuario_idTipoUsuario"));
                u.setNome(rs.getString("Nome"));
                u.setEmail(rs.getString("Email"));
                u.setLogin(rs.getString("Login"));
                u.setSexo(rs.getString("Sexo"));
                u.setTelefone(rs.getString("Telefone"));
                u.setDescricaoTipoUsuario(rs.getString("tipousuario.Descricao"));
                u.setEndereco_idEndereco(rs.getInt("Endereco_idEndereco"));
                listaUsuarios.add(u);
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return listaUsuarios;
        }

    }

    public int excluirUsuario(CadastroUsuario novoUsuario) {
        Connection connection = db.conectar();

        String SQL = "DELETE from Usuario";

        if (novoUsuario.getId() != 0) {
            SQL = SQL + " WHERE idUsuario=" + novoUsuario.getId();
        }
        int usuario = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            usuario = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return usuario;
        }
    }

    public int editarUsuario(CadastroUsuario novoUsuario) {
        Connection connection = db.conectar();

        String SQL = "Update usuario set ";

        if (novoUsuario.getNome() != null) {
            SQL = SQL + "nome ='" + novoUsuario.getNome() + "', ";
        }

        if (novoUsuario.getLogin() != null) {
            SQL = SQL + "login ='" + novoUsuario.getLogin() + "', ";
        }

        if (novoUsuario.getSenha() != null) {
            SQL = SQL + "senha='" + novoUsuario.getSenha() + "', ";
        }

        if (novoUsuario.getEmail() != null) {
            SQL = SQL + "email='" + novoUsuario.getEmail() + "', ";
        }

        if (novoUsuario.getTelefone() != null) {
            SQL = SQL + "telefone='" + novoUsuario.getTelefone() + "', ";
        }

        if (novoUsuario.getSexo() != null) {
            SQL = SQL + "sexo='" + novoUsuario.getSexo() + "', ";
        }

        if (novoUsuario.getTipoUsuario() != 0) {
            SQL = SQL + "TipoUsuario_idTipoUsuario='" + novoUsuario.getTipoUsuario() + "'";
        }

        SQL = SQL + " WHERE idUsuario = " + novoUsuario.getId();

        int u = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            u = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return u;
        }
    }

    public int atualizarEnderecoUsuario(CadastroUsuario novaUsuario) {
        Connection connection = db.conectar();

        String SQL = "Update Usuario set Endereco_idEndereco = ";

        if (novaUsuario.getEndereco_idEndereco() != 0) {
            SQL = SQL + novaUsuario.getEndereco_idEndereco();
        } else {
            SQL = SQL + null;
        }

        if (novaUsuario.getId() != 0) {
            SQL = SQL + " WHERE idUsuario=" + novaUsuario.getId();
        } else {
            SQL = SQL + " WHERE idUsuario=" + 0;

        }

        int usuario = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            usuario = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return usuario;
        }
    }
    
        public int excluirUsuarioEndereco(CadastroUsuario novaUsuario) {
        Connection connection = db.conectar();

        String SQL = "UPDATE Usuario ";

        if (novaUsuario.getId() != 0) {
            SQL = SQL + " SET Endereco_idEndereco = NULL WHERE idUsuario = " + novaUsuario.getId();

        }

        int usuario = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            usuario = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return usuario;
        }
    }

}
