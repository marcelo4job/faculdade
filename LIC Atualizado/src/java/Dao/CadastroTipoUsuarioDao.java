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
import model.CadastroTipoUsuario;

/**
 *
 * @author Estudos
 */
public class CadastroTipoUsuarioDao {

    Conexao db = new Conexao();

    public CadastroTipoUsuarioDao() {
        db.getInstacia();
    }

    public int inserirCategoria(CadastroTipoUsuario novaCategoria) {
        Connection connection = db.conectar();

        String SQL = "INSERT INTO Categoria (nome) values";

        int categoria = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            categoria = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return categoria;
        }
    }

    public ArrayList<CadastroTipoUsuario> consultarListaCategoria() {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Categoria";
        SQL = SQL + " WHERE idCategoria != 0 ";

        CadastroTipoUsuario u = null;
        ArrayList listaCategoria = new ArrayList();

        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new CadastroTipoUsuario();

                listaCategoria.add(u);
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return listaCategoria;
        }

    }

    public int consultarTipoUsuario(String NomeTipo) {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM tipousuario";
        if (NomeTipo != null) {
            SQL = SQL + " WHERE Descricao = '" + NomeTipo + "' ";
        }

        int idTipo = 0;
        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                idTipo = rs.getInt("idTipoUsuario");
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return idTipo;
        }

    }
    
    public String consultarDescricaoTipoUsuario(int id) {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM tipousuario";
        if (id > 0) {
            SQL = SQL + " WHERE idTipoUsuario = '" + id + "' ";
        }else{
            SQL = SQL + " WHERE idTipoUsuario = 0" ;
        }

        String descricao = "";
        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                descricao = rs.getString("Descricao");
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return descricao;
        }

    }
    
    
    
    public int excluirCategoria(CadastroTipoUsuario novaCategoria) {
        Connection connection = db.conectar();

        String SQL = "DELETE from Categoria ";

        int categoria = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            categoria = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return categoria;
        }
    }

}
