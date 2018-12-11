/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.item;

import Dao.CadastroImagemDao;
import com.google.gson.Gson;
import Dao.CadastroItemDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroImagem;
import model.CadastroItem;

@WebServlet("/CadastroItemServlet")
public class CadastroItemServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        CadastroItem cadastroitem = new CadastroItem();
        CadastroItemDao itemdao = new CadastroItemDao();

        CadastroImagem novaimagem = new CadastroImagem();
        CadastroImagemDao imagemdao = new CadastroImagemDao();

        String nome = request.getParameter("nome");
        String preco = request.getParameter("preco");
        String observacoes = request.getParameter("observacoes");
        String imagem = request.getParameter("imagem");
        String Categoria = request.getParameter("categoria");

        if (imagem != null) {

            novaimagem.setImagem(imagem);

            int result = 0;

            result = imagemdao.inserirImagem(novaimagem);

            if (result > 0) {
                int idImagem = 0;
                idImagem = imagemdao.consultarImagem();
                if (idImagem > 0) {
                    cadastroitem.setImagem(Integer.toString(idImagem));
                }
            }
        }

        cadastroitem.setNome(nome);
        cadastroitem.setPreco(preco);
        cadastroitem.setObservacoes(observacoes);
        cadastroitem.setIdCategoria(Categoria);

        int result = 0;
        result = itemdao.inserirItem(cadastroitem);

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
