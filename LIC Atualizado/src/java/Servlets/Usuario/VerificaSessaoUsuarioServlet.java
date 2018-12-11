/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Usuario;

import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Merel
 */
@WebServlet("/VerificaSessaoUsuarioServlet")
public class VerificaSessaoUsuarioServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();

        if (session.getAttribute("login") != null) {
            Gson gson = new Gson();
            String json = gson.toJson(session.getAttribute("login"));
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
