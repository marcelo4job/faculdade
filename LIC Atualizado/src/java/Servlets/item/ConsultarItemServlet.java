/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.item;

import Dao.CadastroItemDao;
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
@WebServlet("/ConsultarItemServlet")
public class ConsultarItemServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList listaItem = new ArrayList();
        CadastroItemDao cadastroItemDao = new CadastroItemDao();

        listaItem = cadastroItemDao.consultarListaItem();

        if (listaItem.size() > 0) {
            Gson gson = new Gson();
            String json = gson.toJson(listaItem);
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
