/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Lista;

import Dao.CadastroListaItensDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroListaItens;

/**
 *
 * @author Merel
 */
@WebServlet("/ConsultarItensLista")
public class ConsultarItensLista extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idLista = request.getParameter("idLista");
        ArrayList listaItens = new ArrayList();
        CadastroListaItensDao listaDao = new CadastroListaItensDao();

        listaItens = listaDao.consultarItensLista(Integer.parseInt(idLista));

        if (listaItens.size() > 0) {
            Gson gson = new Gson();
            String json = gson.toJson(listaItens);
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
