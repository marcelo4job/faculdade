/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Lista;

import Dao.CadastroListaDao;
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
@WebServlet("/ConsultarListasServlet")
public class ConsultarListaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String descricaoTipoUsuario = request.getParameter("DescricaoUsuario");
        String idUsuario = request.getParameter("idUsuario");

        ArrayList listas = new ArrayList();
        CadastroListaDao cadastroItemDao = new CadastroListaDao();

        if (descricaoTipoUsuario.equals("Administrador")) {
            listas = cadastroItemDao.consultarListas(0);
        } else {
            listas = cadastroItemDao.consultarListas(Integer.parseInt(idUsuario));
        }

        if (listas.size() > 0) {
            Gson gson = new Gson();
            String json = gson.toJson(listas);
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
