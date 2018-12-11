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
 * @author Estudos
 */
public class CadastroSupermercadoDao {

    Conexao db = new Conexao();

    public CadastroSupermercadoDao() {
        db.getInstacia();
    }

    public int inserirSupermercado(CadastroSupermercado novaSupermercado) {
        Connection connection = db.conectar();

        String SQL = "INSERT INTO Supermercado (NomeFantasia,CNPJ,Endereco_idEndereco,RazaoSocial) values";

        if (novaSupermercado.getNomeFantasiaSupermercado() != null) {
            SQL = SQL + "('" + novaSupermercado.getNomeFantasiaSupermercado() + "',";
        } else {
            SQL = SQL + "('',";
        }

        if (novaSupermercado.getCnpjSupermercado() != null) {
            SQL = SQL + "'" + novaSupermercado.getCnpjSupermercado() + "',";
        }

        if (novaSupermercado.getEndereco_idEndereco() > 0) {
            SQL = SQL + "'" + novaSupermercado.getEndereco_idEndereco() + "',";
        } else {
            SQL = SQL + "null,";
        }

        if (novaSupermercado.getRazaoSocialSupermercado() != null) {
            SQL = SQL + "'" + novaSupermercado.getRazaoSocialSupermercado() + "')";
        }

        int supermercado = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            supermercado = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return supermercado;
        }
    }

    public ArrayList<CadastroSupermercado> consultarListaSupermercado() {
        Connection connection = db.conectar();

        String SQL = "SELECT * FROM Supermercado";
        SQL = SQL + " WHERE idSupermercado != 0 ";

        CadastroSupermercado u = null;
        ArrayList listaSupermercado = new ArrayList();

        try {
            java.sql.PreparedStatement pstm = connection.prepareStatement(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                u = new CadastroSupermercado();
                u.setId(rs.getInt("idSupermercado"));
                u.setEndereco_idEndereco(rs.getInt("Endereco_idEndereco"));
                u.setNomeFantasiaSupermercado(rs.getString("NomeFantasia"));
                u.setCnpjSupermercado(rs.getString("CNPJ"));
                u.setRazaoSocialSupermercado(rs.getString("RazaoSocial"));
                listaSupermercado.add(u);
            }

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return listaSupermercado;
        }

    }

    public int excluirSupermercadoEndereco(CadastroSupermercado novaSupermercado) {
        Connection connection = db.conectar();

        String SQL = "UPDATE Supermercado ";

        if (novaSupermercado.getId() != 0) {
            SQL = SQL + " SET Endereco_idEndereco = NULL WHERE idSupermercado = " + novaSupermercado.getId();

        }

        int supermercado = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            supermercado = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return supermercado;
        }
    }

    public int editarSupermercado(CadastroSupermercado novaSupermercado) {
        Connection connection = db.conectar();

        String SQL = "UPDATE Supermercado set NomeFantasia";

        if (novaSupermercado.getNomeFantasiaSupermercado() != null) {
            SQL = SQL + "='" + novaSupermercado.getNomeFantasiaSupermercado() + "',";
        }

        if (novaSupermercado.getCnpjSupermercado() != null) {
            SQL = SQL + " CNPJ = '" + novaSupermercado.getCnpjSupermercado() + "',";
        }

        if (novaSupermercado.getRazaoSocialSupermercado() != null) {
            SQL = SQL + " RazaoSocial = '" + novaSupermercado.getRazaoSocialSupermercado() + "' ";
        }

        if (novaSupermercado.getId() != 0) {
            SQL = SQL + "WHERE idSupermercado=" + novaSupermercado.getId();
        }
        int supermercado = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            supermercado = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return supermercado;
        }
    }

    public int excluirSupermercado(CadastroSupermercado novaSupermercado) {
        Connection connection = db.conectar();

        String SQL = "DELETE from Supermercado";

        if (novaSupermercado.getId() != 0) {
            SQL = SQL + " WHERE idSupermercado=" + novaSupermercado.getId();
        }
        int supermercado = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            supermercado = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return supermercado;
        }
    }

    public int atualizarEnderecoSupermercado(CadastroSupermercado novaSupermercado) {
        Connection connection = db.conectar();

        String SQL = "Update supermercado set Endereco_idEndereco = ";

        if (novaSupermercado.getEndereco_idEndereco() != 0) {
            SQL = SQL + novaSupermercado.getEndereco_idEndereco();
        } else {
            SQL = SQL + null;
        }

        if (novaSupermercado.getId() != 0) {
            SQL = SQL + " WHERE idSupermercado=" + novaSupermercado.getId();
        } else {
            SQL = SQL + " WHERE idSupermercado=" + 0;

        }

        int supermercado = 0;

        try {
            PreparedStatement pstm = connection.prepareStatement(SQL);
            supermercado = pstm.executeUpdate();

            pstm.close();
            db.desconectar(connection);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            return supermercado;
        }
    }

}
