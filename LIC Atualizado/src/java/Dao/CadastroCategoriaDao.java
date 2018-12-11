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
import model.CadastroCategoria;

/**
 *
 * @author Estudos
 */
public class CadastroCategoriaDao {

    Conexao db = new Conexao();

    public CadastroCategoriaDao() {
        db.getInstacia();
    }

    public int inserirCategoria(CadastroCategoria novaCategoria) {
        Connection connection = db.conectar();

        String SQL = "INSERT INTO Categoria (nome) values";

        if (novaCategoria.getNomeCategoria() != null) {
            SQL = SQL + "('" + novaCategoria.getNomeCategoria() + "')";
        }

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

    public ArrayList<CadastroCategoria> consultarListaCategoria() {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Categoria";
        SQL = SQL + " WHERE idCategoria != 0 ";

        CadastroCategoria u = null;
        ArrayList listaCategoria = new ArrayList();

        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new CadastroCategoria();
                u.setId(rs.getInt("idCategoria"));
                u.setNomeCategoria(rs.getString("nome"));
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

    public int editarCategoria(CadastroCategoria novaCategoria) {
        Connection connection = db.conectar();

        String SQL = "UPDATE Categoria set nome";

        if (novaCategoria.getNomeCategoria() != null) {
            SQL = SQL + "='" + novaCategoria.getNomeCategoria() + "'";
        }
        
        if (novaCategoria.getId()!= 0) {
            SQL = SQL + "WHERE idCategoria=" + novaCategoria.getId() ;
        }
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

   public int excluirCategoria(CadastroCategoria novaCategoria) {
        Connection connection = db.conectar();

        String SQL = "DELETE from Categoria ";

        if (novaCategoria.getId() != 0) {
            SQL = SQL + " WHERE idCategoria=" + novaCategoria.getId();
        }
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