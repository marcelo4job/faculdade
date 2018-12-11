/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.ComparaPrecos;

import Servlets.Endereco.*;
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
@WebServlet("/ConsultarEnderecoProdutoServlet")
public class ConsultarEnderecoProdutoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        String idProduto = request.getParameter("id");
        
        ArrayList listaEndereco = new ArrayList();
        CadastroEnderecoDao cadastroEnderecoDao = new CadastroEnderecoDao();

        listaEndereco = cadastroEnderecoDao.consultarEnderecoProduto(Integer.parseInt(idProduto));

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
