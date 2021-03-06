/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Lista;

import Dao.CadastroListaDao;
import Dao.CadastroListaItensDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroLista;
import model.CadastroListaItens;

/**
 *
 * @author alexandre.junior
 */
@WebServlet("/ExcluirListaServlet")
public class ExcluirListaServlet extends HttpServlet {
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String idLista = request.getParameter("idLista");
        
        int id = 0;
        if (idLista != null) {
            id = Integer.parseInt(idLista);
        }
        
        CadastroLista lista = new CadastroLista();
        CadastroListaDao listadao = new CadastroListaDao();
        
        CadastroListaItens listaitens = new CadastroListaItens();
        CadastroListaItensDao listaitensdao = new CadastroListaItensDao();
        
        lista.setIdLista(id);
        listaitens.setIdLista(id);
        
        int result = 0;
        
        result = listaitensdao.apagarRegistros(listaitens);
        
        result = listadao.excluirLista(lista);
        
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
