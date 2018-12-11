/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Endereco;

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
import model.CadastroUsuario;

/**
 *
 * @author Renan
 */
@WebServlet("/ExcluirEnderecoUsuarioServlet")
public class ExcluirEnderecoUsuarioServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idEndereco = request.getParameter("idEndereco");
        String idUsuario = request.getParameter("idUsuario");

        int id = Integer.parseInt(idEndereco);
        int idUsua = Integer.parseInt(idUsuario);

        CadastroEndereco Endereco = new CadastroEndereco();
        CadastroUsuario Usuario = new CadastroUsuario();
        CadastroEnderecoDao excluirEndereco = new CadastroEnderecoDao();
        CadastroUsuarioDao excluirUsuario = new CadastroUsuarioDao();

        Endereco.setId(id);
        Usuario.setId(idUsua);

        int resultEndereco = 0;
        int resultUsuario = 0;

        resultUsuario = excluirUsuario.excluirUsuarioEndereco(Usuario);
        resultEndereco = excluirEndereco.excluirEndereco(Endereco);

        if (resultEndereco != 0 && resultUsuario != 0) {
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
