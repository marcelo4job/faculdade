/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Lista;

import Dao.CadastroListaDao;
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
@WebServlet("/CadastroListaServlet")
public class CadastroListaServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String idSupermercado = request.getParameter("idSupermercado");
        String idUsuario = request.getParameter("idUsuario");
//        String idTipoUsuario = request.getParameter("idTipoUsuario");

        CadastroListaDao listadao = new CadastroListaDao();
        CadastroLista lista = new CadastroLista();

        lista.setNome(nome);

        if (idSupermercado != null && !idSupermercado.equals("")) {
            lista.setIdSupermercado(idSupermercado);
        }

//        lista.setIdTipoUsuario(idTipoUsuario);
        lista.setIdUsuarioCadastro(idUsuario);

        int result = 0;

        result = listadao.inserirLista(lista);

        if (result > 0) {
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
