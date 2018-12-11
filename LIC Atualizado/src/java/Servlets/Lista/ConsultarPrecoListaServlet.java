/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Lista;

import Dao.CadastroListaItensDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroLista;

/**
 *
 * @author Merel
 */
@WebServlet("/ConsultarPrecoTotalLista")
public class ConsultarPrecoListaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idLista = request.getParameter("idLista");

        CadastroListaItensDao listaDao = new CadastroListaItensDao();

        CadastroLista PrecoTotalLista = listaDao.consultarPrecoLista(idLista);

        if (PrecoTotalLista != null) {
            Gson gson = new Gson();
            String json = gson.toJson(PrecoTotalLista);
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
