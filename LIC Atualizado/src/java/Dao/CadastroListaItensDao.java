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
import model.CadastroLista;
import model.CadastroListaItens;

public class CadastroListaItensDao {

    Conexao db = new Conexao();

    public CadastroListaItensDao() {
        db.getInstacia();
    }

    public ArrayList<CadastroListaItens> consultarItensLista(int id) {
        Connection connection = db.conectar();

        String SQL = "select * from lista\n"
                + "inner join lista_has_item on lista_has_item.Lista_idLista = lista.idLista";
        if (id > 0) {
            SQL = SQL + " WHERE Lista_idLista =" + id;
        } else {
            SQL = SQL + " WHERE Lista_idLista =" + 0;
        }

        CadastroListaItens u = null;
        ArrayList listaLista = new ArrayList();

        try {
            PreparedStatement pstm = connection.prepareCall(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                u = new CadastroListaItens();
                u.setPreco(rs.getDouble("PrecoItem"));
                u.setNome(rs.getString("nomeItem"));
                u.setQuantidade(rs.getInt("Quantidade"));
                u.setIdLista(rs.getInt("Lista_idLista"));
                u.setIdItem(rs.getInt("idItem"));
                listaLista.add(u);
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return listaLista;
        }

    }

    public CadastroLista consultarPrecoLista(String idLista) {
        Connection connection = db.conectar();

        String SQL = "SELECT Lista.PrecoTotalLista FROM Lista Where idLista =" + idLista;

        CadastroLista u = null;

        try {
            PreparedStatement pstm = connection.prepareCall(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {

                u = new CadastroLista();
                u.setPrecoTotalLista(rs.getString("PrecoTotalLista"));
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return u;
        }

    }

    public CadastroLista consultarLista(CadastroLista item) {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Item";
        SQL = SQL + " WHERE id <> 0";

        if (item.getNome() != null) {
            SQL = SQL + " AND nome ='" + item.getNome() + "' ";
        }

//        if (item.getPreco() != null) {
//            SQL = SQL + " AND preco ='" + item.getPreco() + "' ";
//        }
//
//        if (item.getObservacoes() != null) {
//            SQL = SQL + " AND observacoes ='" + item.getObservacoes() + ") ";
//        }
        CadastroLista u = null;

        try {
            PreparedStatement pstm = connection.prepareCall(SQL);
            ResultSet rs = pstm.executeQuery();

            if (rs.next()) {
                u = new CadastroLista();
                u.setIdLista(rs.getInt("id"));
                u.setNome(rs.getString("nome"));

            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return u;
        }
    }

    public int editarLista(CadastroLista novaLista) {
        Connection connection = db.conectar();

        String SQL = "UPDATE Lista SET nome =  ";

        if (novaLista.getNome() != null) {
            SQL = SQL + "'" + novaLista.getNome() + "', ";
        } else {
            SQL = SQL + " null, ";
        }

        if (novaLista.getIdUsuarioCadastro() != null) {
            SQL = SQL + "Usuario_idUsuario='" + novaLista.getIdUsuarioCadastro() + "', ";
        } else {
            SQL = SQL + "Usuario_idUsuario = null, ";
        }

        if (novaLista.getIdSupermercado() != null) {
            SQL = SQL + "Supermercado_idSupermercado='" + novaLista.getIdSupermercado() + "'";
        } else {
            SQL = SQL + " Supermercado_idSupermercado = null ";
        }
        SQL = SQL + "where idLista =" + novaLista.getIdLista();

        int lista = 0;
        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            lista = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return lista;
        }
    }

    public int apagarRegistros(CadastroListaItens novalista) {
        Connection connection = db.conectar();

        String SQL = "DELETE from lista_has_item ";

        if (novalista.getIdLista() != 0) {
            SQL = SQL + " WHERE Lista_idLista=" + novalista.getIdLista();
        } else {
            SQL = SQL + " WHERE Lista_idLista=" + 0;
        }
        int lista = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            lista = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return lista;
        }
    }

    public int atualizarValorTotal(CadastroListaItens novalista) {
        Connection connection = db.conectar();
        String SQL = "";
        if (novalista.getPrecoTotalLista() != null) {
            SQL = "Update lista set PrecoTotalLista =" + novalista.getPrecoTotalLista();
            if (novalista.getIdLista() != 0) {
                SQL = SQL + " WHERE idlista=" + novalista.getIdLista();
            } else {
                SQL = SQL + " WHERE idlista=" + 0;
            }
        }

        int lista = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            lista = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return lista;
        }
    }

    public int inserirItem(CadastroListaItens novoItem) {
        Connection connection = db.conectar();

        String SQL = "INSERT INTO lista_has_item(Lista_idLista,PrecoItem,nomeItem,idItem,Quantidade) values";

        if (novoItem.getIdLista() > 0) {
            SQL = SQL + "( " + novoItem.getIdLista() + ",";
        } else {
            SQL = SQL + "( " + null + ",";
        }

        if (novoItem.getPreco() != null) {
            SQL = SQL + "'" + novoItem.getPreco() + "',";
        } else {
            SQL = SQL + "null,";
        }

        if (novoItem.getNome() != null) {
            SQL = SQL + "'" + novoItem.getNome() + "',";
        } else {
            SQL = SQL + null + ",";
        }
        
        if (novoItem.getIdItem() > 0) {
            SQL = SQL + "'" + novoItem.getIdItem()+ "',";
        } else {
            SQL = SQL + null + ",";
        }

        if (novoItem.getQuantidade() > 0) {
            SQL = SQL + novoItem.getQuantidade() + ") ";
        } else {
            SQL = SQL + null + ")";
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

}
