/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Categoria;

import Dao.CadastroCategoriaDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroCategoria;

/**
 *
 * @author Merel
 */
@WebServlet("/ConsultarCategoriaServlet")
public class ConsultarCategoriaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList listaCategoria = new ArrayList();
        CadastroCategoriaDao cadastroCategoriaDao = new CadastroCategoriaDao();

        listaCategoria = cadastroCategoriaDao.consultarListaCategoria();

        if (listaCategoria.size() > 0) {
            Gson gson = new Gson();
            String json = gson.toJson(listaCategoria);
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
