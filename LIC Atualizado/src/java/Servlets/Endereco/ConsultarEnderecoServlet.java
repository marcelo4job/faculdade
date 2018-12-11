/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Endereco;

import Dao.CadastroEnderecoDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroEndereco;

/**
 *
 * @author Renan
 */
@WebServlet("/ConsultarEnderecoServlet")
public class ConsultarEnderecoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList listaEndereco = new ArrayList();
        CadastroEnderecoDao cadastroEnderecoDao = new CadastroEnderecoDao();

        listaEndereco = cadastroEnderecoDao.consultarListaEndereco();

        if (listaEndereco.size() > 0) {
            Gson gson = new Gson();
            String json = gson.toJson(listaEndereco);
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
