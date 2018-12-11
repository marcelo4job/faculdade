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
@WebServlet("/EditarItemServlet")
public class EditarItemServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idItem = request.getParameter("idItem");
        String nomeItem = request.getParameter("nome");
        String precoItem = request.getParameter("preco");
        String observacoesItem = request.getParameter("observacoes");
        String imagemItem = request.getParameter("imagem");
        String Categoria = request.getParameter("categoria");
        String idImagem = request.getParameter("idImagem");

//        int idCategoria = Integer.parseInt(Categoria);
        int id = Integer.parseInt(idItem);
//        int idcategoria = Integer.parseInt(categoriaId);;

        CadastroItem Item = new CadastroItem();
        CadastroItemDao editarItemDao = new CadastroItemDao();

        CadastroImagem novaimagem = new CadastroImagem();
        CadastroImagemDao imagemdao = new CadastroImagemDao();

        Item.setId(id);
        Item.setNome(nomeItem);
        Item.setPreco(precoItem);
        Item.setObservacoes(observacoesItem);
        Item.setIdCategoria(Categoria);

        int resultImagem = 0;


        if (idImagem != null && imagemItem != null && !imagemItem.equals("")) {

            novaimagem.setId(Integer.parseInt(idImagem));
            novaimagem.setImagem(imagemItem);

            resultImagem = 0;

            resultImagem = imagemdao.editarImagem(novaimagem);

            if (resultImagem > 0) {
                Item.setIdImagem(idImagem);
            }

        } else {
            if (idImagem != null && (imagemItem == null || imagemItem.equals(""))) {

                resultImagem = 2;

                Item.setIdImagem(null);

            } else {
                if (imagemItem != null && idImagem == null) {

                    novaimagem.setImagem(imagemItem);

                    resultImagem = 0;

                    resultImagem = imagemdao.inserirImagem(novaimagem);

                    if (resultImagem > 0) {
                        int idNovaImagem = 0;
                        idNovaImagem = imagemdao.consultarImagem();
                        if (idNovaImagem > 0) {
                            Item.setIdImagem(Integer.toString(idNovaImagem));
                        }
                    }
                }

            }
        }

        int result = 0;

        result = editarItemDao.editarItem(Item);

        if (resultImagem == 2) {
            
            novaimagem.setId(Integer.parseInt(idImagem));

            resultImagem = imagemdao.excluirImagem(novaimagem);

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
