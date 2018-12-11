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

public class CadastroListaDao {

    Conexao db = new Conexao();

    public CadastroListaDao() {
        db.getInstacia();
    }

    public ArrayList<CadastroLista> consultarListas(int id) {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Lista";
        if (id > 0) {
            SQL = SQL + " WHERE Usuario_idUsuario =" + id;
        }

        CadastroLista u = null;
        ArrayList listaLista = new ArrayList();

        try {
            PreparedStatement pstm = connection.prepareCall(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new CadastroLista();
                u.setIdLista(rs.getInt("idLista"));
                u.setNome(rs.getString("Nome"));
                u.setIdSupermercado(rs.getString("Supermercado_idSupermercado"));
                u.setIdUsuarioCadastro(rs.getString("Usuario_idUsuario"));
//                u.setIdTipoUsuario(rs.getString("Usuario_TipoUsuario_idTipoUsuario"));

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

    public int inserirLista(CadastroLista novaLista) {
        Connection connection = db.conectar();

        String SQL = "INSERT INTO lista (nome,Usuario_idUsuario,Supermercado_idSupermercado) values";

        if (novaLista.getNome() != null) {
            SQL = SQL + "( '" + novaLista.getNome() + "', ";
        } else {
            SQL = SQL + "( null, ";
        }

        if (novaLista.getIdUsuarioCadastro() != null) {
            SQL = SQL + "'" + novaLista.getIdUsuarioCadastro() + "', ";
        } else {
            SQL = SQL + "null, ";
        }

//        if (novaLista.getIdTipoUsuario() != null) {
//            SQL = SQL + "'" + novaLista.getIdTipoUsuario() + "', ";
//        } else {
//            SQL = SQL + "null, ";
//        }
        if (novaLista.getIdSupermercado() != null) {
            SQL = SQL + "'" + novaLista.getIdSupermercado() + "') ";
        } else {
            SQL = SQL + " null) ";
        }

        int Lista = 0;
        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            Lista = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return Lista;
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
        SQL = SQL +    "where idLista =" + novaLista.getIdLista();

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

    public int excluirLista(CadastroLista novalista) {
        Connection connection = db.conectar();

        String SQL = "DELETE from lista ";

        if (novalista.getIdLista() != 0) {
            SQL = SQL + " WHERE idlista=" + novalista.getIdLista();
        } else {
            SQL = SQL + " WHERE idlista=" + 0;
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
}
