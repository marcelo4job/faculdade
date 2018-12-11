/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Usuario;

import Dao.CadastroTipoUsuarioDao;
import Dao.CadastroUsuarioDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroUsuario;

/**
 *
 * @author Merel
 */
@WebServlet("/EditarUsuarioServlet")
public class EditarUsuarioServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        String tipo = request.getParameter("tipoUsuario");
        String id = request.getParameter("idUsuario");
        int tipoUsuario;

        CadastroTipoUsuarioDao tipoUsuariodao = new CadastroTipoUsuarioDao();
        if (tipo != null) {
            tipoUsuario = tipoUsuariodao.consultarTipoUsuario(tipo);
        } else {
            tipoUsuario = tipoUsuariodao.consultarTipoUsuario("Cliente");
        }

        CadastroUsuarioDao novoUsuarioDao = new CadastroUsuarioDao();
        CadastroUsuario novoUsuario = new CadastroUsuario();

        novoUsuario.setEmail(email);
        novoUsuario.setLogin(login);
        novoUsuario.setNome(nome);
        novoUsuario.setSenha(senha);
        novoUsuario.setSexo(sexo);
        novoUsuario.setTelefone(telefone);
        novoUsuario.setTipoUsuario(tipoUsuario);
        novoUsuario.setId(Integer.parseInt(id));

        int result = 0;
        result = novoUsuarioDao.editarUsuario(novoUsuario);

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
