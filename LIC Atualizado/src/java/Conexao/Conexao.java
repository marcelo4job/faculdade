/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Vital
 */
public class Conexao {

    private final String driver;
    private final String banco;
    private final String url;
    private final String usuario;
    private final String senha;

    private Conexao instacia;

    /*
     CONSTRUTOR PADRÃO DA CLASSE ConnectionBD
     */
    //localhost: 127.0.0.1
    public Conexao() {
        this.driver = "com.mysql.jdbc.Driver";
        this.banco = "mydb";
        this.url = "jdbc:mysql://localhost:3306/" + banco;
        this.usuario = "root";
        this.senha = "pw123";
    }

    /*
     METODO QUE INSTACIA UMA NOVA CONEXÃO COM O BANCO DE DADOS
     QUANDO ELE NÃO ESTA CONECTADO. 
    
     Retorna a instacia com o banco
     @return instacia;
        
     */
    public Conexao getInstacia() {
        if (instacia == null) {
            instacia = new Conexao();
            return instacia;
        } else {
            return instacia;
        }

    }

    public Connection conectar() {
        Connection c = null;
        try {
            Class.forName(this.driver);
            c = DriverManager.getConnection(url, usuario, senha);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;

    }

    /*
     METODO SIMPLES PARA DESCONECTAR O BANCO DE DADOS
     */
    public void desconectar(Connection c) {
        try {
            c.close();
            // System.err.println("desconectado"); 
        } catch (SQLException e) {
            System.err.println("SQL Exception: " + e.getMessage());
        }
    }

}
