/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Usuario;

import Dao.CadastroUsuarioDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Merel
 */
@WebServlet("/ConsultarUsuarioServlet")
public class ConsultarUsuarioServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList listaUsuario = new ArrayList();
        CadastroUsuarioDao cadastroUsuarioDao = new CadastroUsuarioDao();

        listaUsuario = cadastroUsuarioDao.consultarUsuarios();
        
        
        if (listaUsuario.size() > 0) {
            Gson gson = new Gson();
            String json = gson.toJson(listaUsuario);
            response.setContentType("text/plain");
            response.setContentType("application/json");
            response.getWriter().write(json);
        } else {
            Gson gson = new Gson();
            String json = gson.toJson("");
            response.setContentType("text/plain");
            response.setContentType("application/json");
            response.getWriter().write(json);
        }
    }

}
