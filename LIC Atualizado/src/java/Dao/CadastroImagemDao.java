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
import model.CadastroImagem;

/**
 *
 * @author Estudos
 */
public class CadastroImagemDao {

    Conexao db = new Conexao();

    public CadastroImagemDao() {
        db.getInstacia();
    }

    public int inserirImagem(CadastroImagem novaImagem) {
        Connection connection = db.conectar();

        String SQL = "INSERT INTO imagens (imagem) values";

        if (novaImagem.getImagem()!= null) {
            SQL = SQL + "('" + novaImagem.getImagem() + "')";
        }

        int imagem = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            imagem = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return imagem;
        }
    }

    public int consultarImagem() {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Imagens order by idImagens desc limit 1";
       
        int idImagem = 0;
        
        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
               
                idImagem = rs.getInt("idImagens");
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return idImagem;
        }

    }

    public int editarImagem(CadastroImagem novaimagem) {
        Connection connection = db.conectar();

        String SQL = "UPDATE imagens set imagem";

        if (novaimagem.getImagem()!= null) {
            SQL = SQL + "='" + novaimagem.getImagem() + "'";
        }
        
        if (novaimagem.getId()!= 0) {
            SQL = SQL + "WHERE idImagens=" + novaimagem.getId() ;
        }
        int imagem = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            imagem = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return imagem;
        }
    }

   public int excluirImagem(CadastroImagem novaimagem) {
        Connection connection = db.conectar();

        String SQL = "";

        if (novaimagem.getId() != 0) {
            SQL = "DELETE from imagens WHERE idImagens =" + novaimagem.getId();
        }
        int imagem = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            imagem = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return imagem;
        }
    }

}