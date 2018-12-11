/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Lista;

import Dao.CadastroListaItensDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroListaItens;

/**
 *
 * @author Merel
 */
@WebServlet("/SalvarItensLista")
public class SalvarItensLista extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idLista = request.getParameter("idLista");
        String precoTotalLista = request.getParameter("precoTotalLista");

        String[] nomesItens = {};
        String[] qtdItens = {};
        String[] precoItens = {};
        String[] idItens = {};

        nomesItens = request.getParameterValues("nomesItens[]");
        qtdItens = request.getParameterValues("qtdItens[]");
        precoItens = request.getParameterValues("precoItens[]");
        idItens = request.getParameterValues("idItens[]");

        CadastroListaItensDao listadao = new CadastroListaItensDao();
        CadastroListaItens lista = new CadastroListaItens();

        lista.setIdLista(Integer.parseInt(idLista));
        if (precoTotalLista != null) {
            lista.setPrecoTotalLista(Double.parseDouble(precoTotalLista));
        } else {
            lista.setPrecoTotalLista(0.0);
        }

        int atualizaPreco = 0;

        atualizaPreco = listadao.atualizarValorTotal(lista);

        if (atualizaPreco > 0) {

            // após atualizar o preço deleta todos os registros antes de salvar os novos
            atualizaPreco = listadao.apagarRegistros(lista);

            int contador = 0;

            for (int cont = 0; cont < nomesItens.length; cont++) {

                lista.setNome(nomesItens[cont]);
                lista.setPreco(Double.parseDouble(precoItens[cont]));
                lista.setQuantidade(Integer.parseInt(qtdItens[cont]));
                lista.setIdItem(Integer.parseInt(idItens[cont]));

                contador = listadao.inserirItem(lista);

                if (contador == 0) {
                    break;
                }

            }

            if (contador > 0) {
                Gson gson = new Gson();
                String json = gson.toJson(contador);
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

        } else {
            Gson gson = new Gson();
            String json = gson.toJson("");
            response.setContentType("text/plain");
            response.setContentType("application/json");
            response.getWriter().write(json);
        }

    }
}
