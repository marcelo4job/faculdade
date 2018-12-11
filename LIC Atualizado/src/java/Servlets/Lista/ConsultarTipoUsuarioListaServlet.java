/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Lista;

import Dao.CadastroTipoUsuarioDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Merel
 */
@WebServlet("/ConsultarDescricaoTipoUsuarioServlet")
public class ConsultarTipoUsuarioListaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CadastroTipoUsuarioDao tipousuariodao = new CadastroTipoUsuarioDao();

        String tipoUsuario = request.getParameter("idTipoUsuario");

        String descricaoTipoUsuario = null;

        descricaoTipoUsuario = tipousuariodao.consultarDescricaoTipoUsuario(Integer.parseInt(tipoUsuario));

        if (descricaoTipoUsuario != null) {
            Gson gson = new Gson();
            String json = gson.toJson(descricaoTipoUsuario);
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
