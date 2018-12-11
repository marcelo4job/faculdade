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
import model.CadastroEndereco;
import model.CadastroSupermercado;

/**
 *
 * @author Renan
 */
public class CadastroEnderecoDao {

    Conexao db = new Conexao();

    public CadastroEnderecoDao() {
        db.getInstacia();
    }

    public CadastroEndereco consultarEndereco(int idEndereco) {
        Connection connection = db.conectar();

        String SQL = "SELECT * from Endereco WHERE idEndereco =" + idEndereco;

        CadastroEndereco u = null;

        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new CadastroEndereco();
                u.setId(rs.getInt("idEndereco"));
                u.setLogradouro(rs.getString("Logradouro"));
                u.setNumero(rs.getString("Numero"));
                u.setComplemento(rs.getString("Complemento"));
                u.setBairro(rs.getString("Bairro"));
                u.setLocalidade(rs.getString("Cidade"));
                u.setUF(rs.getString("UF"));
                u.setPais(rs.getString("Pais"));
                u.setCep(rs.getString("Cep"));
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return u;
        }

    }

    public ArrayList<CadastroEndereco> consultarEnderecoProduto(int idProduto) {
        Connection connection = db.conectar();

        String SQL = "select * from endereco where idEndereco in (select Endereco_idEndereco from supermercado \n"
                + "where idSupermercado in (select lista.Supermercado_idSupermercado from lista where lista.idLista in \n"
                + "(select lista_has_item.Lista_idLista from lista_has_item\n"
                + "Where lista_has_item.PrecoItem = (select  lista_has_item.PrecoItem from lista_has_item \n"
                + "inner join lista on lista.idLista = lista_has_item.Lista_idLista\n"
                + "where lista.Supermercado_idSupermercado > 0 and lista_has_item.idItem =" + idProduto + "\n"
                + "order by lista_has_item.PrecoItem\n"
                + "limit 1))))";

        CadastroEndereco u = null;
        ArrayList listaEndereco = new ArrayList();

        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new CadastroEndereco();
                u.setId(rs.getInt("idEndereco"));
                u.setLogradouro(rs.getString("Logradouro"));
                u.setNumero(rs.getString("Numero"));
                u.setLocalidade(rs.getString("Cidade"));
                u.setComplemento(rs.getString("Complemento"));
                u.setBairro(rs.getString("Bairro"));
                u.setUF(rs.getString("UF"));
                u.setPais(rs.getString("Pais"));
                u.setCep(rs.getString("Cep"));
                listaEndereco.add(u);
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return listaEndereco;
        }

    }

    public int inserirEndereco(CadastroEndereco novaEndereco) {
        Connection connection = db.conectar();

        String SQL = "INSERT INTO Endereco(Logradouro,Numero,Complemento,Bairro,UF,Cidade,Cep) values";

        if (novaEndereco.getLogradouro() != null) {
            SQL = SQL + "('" + novaEndereco.getLogradouro() + "',";
        }

        if (novaEndereco.getNumero() != null) {
            SQL = SQL + "'" + novaEndereco.getNumero() + "',";
        } else {
            SQL = SQL + "'',";
        }

        if (novaEndereco.getComplemento() != null) {
            SQL = SQL + "'" + novaEndereco.getComplemento() + "',";
        } else {
            SQL = SQL + "'',";
        }

        if (novaEndereco.getBairro() != null) {
            SQL = SQL + "'" + novaEndereco.getBairro() + "',";
        }

        if (novaEndereco.getUF() != null) {
            SQL = SQL + "'" + novaEndereco.getUF() + "',";
        } else {
            SQL = SQL + "'',";
        }

        if (novaEndereco.getLocalidade() != null) {
            SQL = SQL + "'" + novaEndereco.getLocalidade() + "',";
        } else {
            SQL = SQL + "'',";
        }

        if (novaEndereco.getCep() != null) {
            SQL = SQL + "'" + novaEndereco.getCep() + "')";
        }

        int endereco = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            endereco = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return endereco;
        }
    }

    public ArrayList<CadastroEndereco> consultarListaEndereco() {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Endereco";
        SQL = SQL + " WHERE idEndereco != 0 ";

        CadastroEndereco u = null;
        ArrayList listaEndereco = new ArrayList();

        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new CadastroEndereco();
                u.setId(rs.getInt("idEndereco"));
                u.setLogradouro(rs.getString("Logradouro"));
                u.setNumero(rs.getString("Numero"));
                u.setComplemento(rs.getString("Complemento"));
                u.setLocalidade(rs.getString("Cidade"));
                u.setBairro(rs.getString("Bairro"));
                u.setUF(rs.getString("UF"));
                u.setPais(rs.getString("Pais"));
                u.setCep(rs.getString("Cep"));
                listaEndereco.add(u);
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return listaEndereco;
        }

    }

    public int editarEndereco(CadastroEndereco novaEndereco) {
        Connection connection = db.conectar();

        String SQL = "UPDATE Endereco set Logradouro";

        if (novaEndereco.getLogradouro() != null) {
            SQL = SQL + "='" + novaEndereco.getLogradouro() + "',";
        } else {
            SQL = SQL + "Logradouro = '',";
        }

        if (novaEndereco.getNumero() != null) {
            SQL = SQL + " Numero = '" + novaEndereco.getNumero() + "',";
        } else {
            SQL = SQL + " Numero ='',";
        }

        if (novaEndereco.getComplemento() != null) {
            SQL = SQL + " Complemento = '" + novaEndereco.getComplemento() + "', ";
        } else {
            SQL = SQL + " Complemento = '',";
        }

        if (novaEndereco.getBairro() != null) {
            SQL = SQL + " Bairro = '" + novaEndereco.getBairro() + "', ";
        } else {
            SQL = SQL + " Bairro = '',";
        }

        if (novaEndereco.getUF() != null) {
            SQL = SQL + " UF = '" + novaEndereco.getUF() + "', ";
        } else {
            SQL = SQL + " UF = '',";
        }

        if (novaEndereco.getLocalidade() != null) {
            SQL = SQL + " Cidade = '" + novaEndereco.getLocalidade() + "', ";
        } else {
            SQL = SQL + " Cidade = '',";
        }

        if (novaEndereco.getPais() != null) {
            SQL = SQL + " Pais= '" + novaEndereco.getPais() + "', ";
        } else {
            SQL = SQL + " Pais = '',";
        }

        if (novaEndereco.getCep() != null) {
            SQL = SQL + " Cep = '" + novaEndereco.getCep() + "' ";
        } else {
            SQL = SQL + " Cep = '',";
        }

        if (novaEndereco.getId() != 0) {
            SQL = SQL + "WHERE idEndereco=" + novaEndereco.getId();
        }

        int endereco = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            endereco = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return endereco;
        }
    }

    public int excluirEndereco(CadastroEndereco novaEndereco) {
        Connection connection = db.conectar();

        String SQL = "DELETE from Endereco ";

        if (novaEndereco.getId() != 0) {
            SQL = SQL + " WHERE idEndereco=" + novaEndereco.getId();
        }
        int endereco = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            endereco = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return endereco;
        }
    }

    public int consultarUltimoEndereco() {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Endereco ORDER BY idEndereco desc limit 1";

        CadastroEndereco endereco = null;

        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                endereco = new CadastroEndereco();
                endereco.setId(rs.getInt("idEndereco"));
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return endereco.getId();
        }

    }

}
