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
import model.LoginUsuario;

/**
 *
 * @author Vitalç
 */
public class LoginDao {

    Conexao db = new Conexao();

    public LoginDao() {
        db.getInstacia();
    }

    public ArrayList<LoginUsuario> consultarListaUsuario(LoginUsuario usuario) {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Usuario";
        SQL = SQL + " WHERE id != 0 ";

        LoginUsuario u = null;
        ArrayList listaUsuario = new ArrayList();

        if (usuario.getLogin() != null) {
            SQL = SQL + " AND login ='" + usuario.getLogin() + "'";
        }

        if (usuario.getSenha() != null) {
            SQL = SQL + " AND senha = ('" + usuario.getSenha() + "')";
        }

        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new LoginUsuario();
                u.setId(rs.getInt("id"));
                u.setLogin(rs.getString("login"));
                u.setSenha(rs.getString("senha"));
                listaUsuario.add(u);
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return listaUsuario;
        }

    }

    public LoginUsuario consultarUsuario(LoginUsuario usuario) {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM usuario";
//        SQL = SQL + " WHERE idUsuario <> 0";

        if (usuario.getLogin() != null) {
            SQL = SQL + " WHERE Login ='" + usuario.getLogin() + "' ";
        }

        if (usuario.getSenha() != null) {
            SQL = SQL + " AND Senha ='" + usuario.getSenha() + "' ";
        }

        LoginUsuario u = null;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                u = new LoginUsuario();
                u.setId(rs.getInt("idUsuario"));
                u.setLogin(rs.getString("Login"));
                u.setSenha(rs.getString("Senha"));
                u.setTipoUsuario(rs.getInt("TipoUsuario_idTipoUsuario"));
                u.setNome(rs.getString("Nome"));

            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return u;
        }
    }

//    public boolean alterarUsuario(LoginUsuario usuario) {
//        Connection connection = db.conectar();
//
//        String SQL = "UPDATE usuario SET "
//                + "login ='" + usuario.getLogin() + "', "
//                + "senha =md5('" + usuario.getSenha() + "'), "
//               
//                + " WHERE id=" + usuario.getId();
//
//        try {
//            PreparedStatement pstm = connection.prepareCall(SQL);
//            pstm.executeUpdate();
//            pstm.close();
//            db.desconectar(connection);
//
//            return true;
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            return false;
//        }
//        
//    }
}
