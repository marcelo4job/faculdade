/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import com.google.gson.Gson;
import Dao.LoginDao;
import Dao.PermissoesDao;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.LoginUsuario;
import model.PermissoesTipoUsuario;

/**
 *
 * @author Merel
 */
@WebServlet("/LoginValidacao")
public class LoginValidacao extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        LoginUsuario loginusuario = new LoginUsuario();
        LoginDao logindao = new LoginDao();
        PermissoesDao p = new PermissoesDao();
        ArrayList lista = new ArrayList<PermissoesTipoUsuario>();
        
        ArrayList arraylist = new ArrayList();
        
        HttpSession session = request.getSession();

        loginusuario.setLogin(login);
        loginusuario.setSenha(senha);

        loginusuario = logindao.consultarUsuario(loginusuario);

        if (loginusuario != null) {
            
            lista = p.consultarPermissoesTipoUsuario(loginusuario.getTipoUsuario());
            arraylist.add(loginusuario);
            arraylist.add(lista);
            
            session.setAttribute("login", loginusuario.getLogin());
            session.setAttribute("id", loginusuario.getId());
            session.setAttribute("tipoUsuario", loginusuario.getTipoUsuario());
            session.setMaxInactiveInterval(30000);
            
            // session.getAttribute("login");
            
            Gson gson = new Gson();
            String json = gson.toJson(arraylist);
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
