/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Usuario;

import Dao.CadastroTipoUsuarioDao;
import Dao.CadastroUsuarioDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroUsuario;

/**
 *
 * @author Merel
 */
@WebServlet("/ExcluirUsuarioServlet")
public class ExcluirUsuarioServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idUsuario = request.getParameter("idUsuario");

        int id = Integer.parseInt(idUsuario);

        CadastroUsuarioDao novoUsuarioDao = new CadastroUsuarioDao();
        CadastroUsuario novoUsuario = new CadastroUsuario();  

        novoUsuario.setId(id);
        
        int result = 0;
        result = novoUsuarioDao.excluirUsuario(novoUsuario);

        if (result != 0) {
            Gson gson = new Gson();
            String json = gson.toJson(result);
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
