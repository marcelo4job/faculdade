/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Usuario;

import Dao.CadastroEnderecoDao;
import Dao.CadastroUsuarioDao;
import com.google.gson.Gson;
import java.io.IOException;
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
@WebServlet("/ConsultarListaEnderecoUsuarioServlet")
public class ConsultarListaEnderecoUsuarioServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CadastroUsuarioDao cadastroUsuarioDao = new CadastroUsuarioDao();

        
        String Endereco_idEndereco = request.getParameter("Endereco_idEndereco");

        int id = Integer.parseInt(Endereco_idEndereco);

        CadastroEnderecoDao enderecoDao = new CadastroEnderecoDao();
        CadastroEndereco endereco = new CadastroEndereco();
        
        endereco = enderecoDao.consultarEndereco(id);

        if (endereco.getId() > 0) {
            Gson gson = new Gson();
            String json = gson.toJson(endereco);
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
