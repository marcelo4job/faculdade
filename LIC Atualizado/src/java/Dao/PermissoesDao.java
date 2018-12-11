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
import model.PermissoesTipoUsuario;

/**
 *
 * @author renan
 */
public class PermissoesDao {

    Conexao db = new Conexao();

    public PermissoesDao() {
        db.getInstacia();
    }

    public ArrayList<PermissoesTipoUsuario> consultarPermissoesTipoUsuario(int tipoUsuario) {
        Connection connection = db.conectar();
        PermissoesTipoUsuario p;
        ArrayList lista = new ArrayList<PermissoesTipoUsuario>();

        String SQL = "SELECT * FROM permissoes_usuario";

        try {
            PreparedStatement pstm = connection.prepareCall(SQL);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                p = new PermissoesTipoUsuario();
                p.setPermissoesPermissoes(rs.getInt("Permissoes_idPermissoes"));
                p.setTipoUsuarioPermissoes(rs.getInt("TipoUsuario_idTipoUsuario"));
                lista.add(p);
            }
            pstm.close();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            db.desconectar(connection);
            return lista;
        }
    }

}
