/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.item;

import Dao.CadastroImagemDao;
import Dao.CadastroItemDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroImagem;
import model.CadastroItem;

/**
 *
 * @author alexandre.junior
 */
@WebServlet("/ExcluirItemServlet")
public class ExcluirItemServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idItem = request.getParameter("idItem");
        String idImagem = request.getParameter("idImagem");

        int id = Integer.parseInt(idItem);
        int idIma = 0;
        if (idImagem != null && !idImagem.equals("")) {
            idIma = Integer.parseInt(idImagem);
        }

        CadastroItem Item = new CadastroItem();
        CadastroItemDao excluirItemDao = new CadastroItemDao();

        Item.setId(id);

        int result = 0;

        result = excluirItemDao.excluirItem(Item);

        if (idIma > 0 && result > 0) {

            CadastroImagem Imagem = new CadastroImagem();
            CadastroImagemDao ImagemDao = new CadastroImagemDao();

            Imagem.setId(idIma);
            int resultImagem = 0;

            resultImagem = ImagemDao.excluirImagem(Imagem);

        }

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
