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
import model.CadastroItem;

public class CadastroItemDao {

    Conexao db = new Conexao();

    public CadastroItemDao() {
        db.getInstacia();
    }

    public ArrayList<CadastroItem> consultarListaItem() {
        Connection connection = db.conectar();

        String SQL = "SELECT Item.*, imagens.imagem FROM Item "
                + "left outer join imagens on item.imagens_idImagens = imagens.idImagens ";
        SQL = SQL + " WHERE idItem != 0 ";

        CadastroItem u = null;
        ArrayList listaItem = new ArrayList();

        try {
            PreparedStatement pstm = connection.prepareCall(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new CadastroItem();
                u.setId(rs.getInt("idItem"));
                u.setNome(rs.getString("Nome"));
                u.setPreco(rs.getString("Preco"));
                u.setImagem(rs.getString("imagens.imagem"));
                u.setObservacoes(rs.getString("Observacoes"));
                u.setIdImagem(rs.getString("imagens_idImagens"));
                u.setIdCategoria(rs.getString("Categoria_idCategoria"));
                listaItem.add(u);
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return listaItem;
        }

    }

    public CadastroItem consultarItem(CadastroItem item) {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Item";
        SQL = SQL + " WHERE id <> 0";

        if (item.getNome() != null) {
            SQL = SQL + " AND nome ='" + item.getNome() + "' ";
        }

        if (item.getPreco() != null) {
            SQL = SQL + " AND preco ='" + item.getPreco() + "' ";
        }

        if (item.getObservacoes() != null) {
            SQL = SQL + " AND observacoes ='" + item.getObservacoes() + ") ";
        }

        CadastroItem u = null;

        try {
            PreparedStatement pstm = connection.prepareCall(SQL);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                u = new CadastroItem();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setPreco(rs.getString("preco"));
                u.setObservacoes(rs.getString("observacoes"));

            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return u;
        }
    }

    public int inserirItem(CadastroItem novoItem) {
        Connection connection = db.conectar();

        String SQL = "INSERT INTO item (nome,preco,observacoes,imagens_idImagens,Categoria_idCategoria) values";

        if (novoItem.getNome() != null) {
            SQL = SQL + "( '" + novoItem.getNome() + "','";
        }

        if (novoItem.getPreco() != null) {
            SQL = SQL + novoItem.getPreco() + "','";
        }

        if (novoItem.getObservacoes() != null) {
            SQL = SQL + novoItem.getObservacoes() + "',";
        }

        if (novoItem.getImagem() != null) {
            SQL = SQL + novoItem.getImagem() + ",";
        } else {
            SQL = SQL + null + ",";
        }

        if (novoItem.getIdCategoria() != null) {
            SQL = SQL + novoItem.getIdCategoria() + ") ";
        }

        int item = 0;
        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            item = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return item;
        }
    }

    public int editarItem(CadastroItem novoItem) {
        Connection connection = db.conectar();

        String SQL = "UPDATE Item SET";

        if (novoItem.getNome() != null) {
            SQL = SQL +" nome = '" +novoItem.getNome() + "'";
        }

        if (novoItem.getPreco() != null) {
            SQL = SQL + ", preco ='"+ novoItem.getPreco() + "'";
        }

        if (novoItem.getObservacoes() != null) {
            SQL = SQL +",observacoes='"+ novoItem.getObservacoes() + "'";
        }

        if (novoItem.getIdImagem() != null) {
            SQL = SQL + ",imagens_idImagens= " + novoItem.getIdImagem();
        } else {
            SQL = SQL + ", imagens_idImagens = null";
        }

        if (novoItem.getIdCategoria() != null) {
            SQL = SQL + ", Categoria_IdCategoria="+novoItem.getIdCategoria();
        }

        if (novoItem.getId() != 0) {
            SQL = SQL + " where idItem =" + novoItem.getId();
        }

        int item = 0;
        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            item = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return item;
        }
    }

    public int excluirItem(CadastroItem novoItem) {
        Connection connection = db.conectar();

        String SQL = "DELETE from Item ";

        if (novoItem.getId() != 0) {
            SQL = SQL + "WHERE idItem=" + novoItem.getId();
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
