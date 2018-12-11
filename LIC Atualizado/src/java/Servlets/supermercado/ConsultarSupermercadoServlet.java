/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.supermercado;

import Dao.CadastroSupermercadoDao;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet("/ConsultarSupermercadoServlet")
public class ConsultarSupermercadoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ArrayList arrayList = new ArrayList();
        CadastroSupermercadoDao cadastroSupermercadoDao = new CadastroSupermercadoDao();

        arrayList = cadastroSupermercadoDao.consultarListaSupermercado();

        if (arrayList.size() > 0) {
            Gson gson = new Gson();
            String json = gson.toJson(arrayList);
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
