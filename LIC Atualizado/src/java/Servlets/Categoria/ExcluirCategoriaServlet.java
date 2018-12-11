/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Categoria;

import Dao.CadastroCategoriaDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroCategoria;

/**
 *
 * @author alexandre.junior
 */
@WebServlet("/ExcluirCategoriaServlet")
public class ExcluirCategoriaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idCategoria = request.getParameter("idCategoria");

        int id = Integer.parseInt(idCategoria);

        CadastroCategoria Categoria = new CadastroCategoria();
        CadastroCategoriaDao excluirCategoria = new CadastroCategoriaDao();

        Categoria.setId(id);

        int result = 0;

        result = excluirCategoria.excluirCategoria(Categoria);

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
