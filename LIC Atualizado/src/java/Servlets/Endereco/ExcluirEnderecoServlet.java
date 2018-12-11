/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Endereco;

import Dao.CadastroEnderecoDao;
import Dao.CadastroSupermercadoDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroEndereco;
import model.CadastroSupermercado;

/**
 *
 * @author Renan
 */
@WebServlet("/ExcluirEnderecoServlet")
public class ExcluirEnderecoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idEndereco = request.getParameter("idEndereco");
        String idSupermercado = request.getParameter("idSupermercado");

        int id = Integer.parseInt(idEndereco);
        int idSuper = Integer.parseInt(idSupermercado);

        CadastroEndereco Endereco = new CadastroEndereco();
        CadastroSupermercado Supermercado = new CadastroSupermercado();
        CadastroEnderecoDao excluirEndereco = new CadastroEnderecoDao();
        CadastroSupermercadoDao excluirSupermercado = new CadastroSupermercadoDao();

        Endereco.setId(id);
        Supermercado.setId(idSuper);

        int resultEndereco = 0;
        int resultSupermercado = 0;

        resultSupermercado = excluirSupermercado.excluirSupermercadoEndereco(Supermercado);
        resultEndereco = excluirEndereco.excluirEndereco(Endereco);

        if (resultEndereco != 0 && resultSupermercado != 0) {
            Gson gson = new Gson();
            String json = gson.toJson(resultEndereco);
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
