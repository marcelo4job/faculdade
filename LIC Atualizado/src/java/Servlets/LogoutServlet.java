/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

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
 * @author renan
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet  extends HttpServlet{
    
     @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        if (session.getAttribute("login") != null || session.getAttribute("id") != null) {
            session.removeAttribute("login");
            session.removeAttribute("id");
            
             Gson gson = new Gson();
            String json = gson.toJson(session.getAttribute("login"));
            response.setContentType("text/plain");
            response.setContentType("application/json");
            response.getWriter().write(json);
            
        }
    
    }
    
    
}
