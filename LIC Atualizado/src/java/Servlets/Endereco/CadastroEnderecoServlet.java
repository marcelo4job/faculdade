/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets.Endereco;

import Dao.CadastroEnderecoDao;
import Dao.CadastroSupermercadoDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroEndereco;
import model.CadastroSupermercado;

/**
 *
 * @author Renan
 */
@WebServlet("/CadastroEnderecoServlet")
public class CadastroEnderecoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String Logradouro = request.getParameter("Logradouro");
        String Numero = request.getParameter("Numero");
        String Complemento = request.getParameter("Complemento");
        String Bairro = request.getParameter("Bairro");
        String UF = request.getParameter("UF");
        String Pais = request.getParameter("Pais");
        String Cep = request.getParameter("Cep");
        String Cidade = request.getParameter("localidade");

        String idSupermercado = request.getParameter("idSupermercado");

        CadastroSupermercado cadastroSupermercado = new CadastroSupermercado();

        CadastroSupermercadoDao cadastroSupermercadoDao = new CadastroSupermercadoDao();

        CadastroEndereco cadastroEndereco = new CadastroEndereco();
        CadastroEnderecoDao cadastroEnderecoDao = new CadastroEnderecoDao();

        cadastroEndereco.setLogradouro(Logradouro);
        cadastroEndereco.setNumero(Numero);
        cadastroEndereco.setComplemento(Complemento);
        cadastroEndereco.setBairro(Bairro);
        cadastroEndereco.setUF(UF);
        cadastroEndereco.setPais(Pais);
        cadastroEndereco.setCep(Cep);
        cadastroEndereco.setLocalidade(Cidade);

        int result = 0;
        result = cadastroEnderecoDao.inserirEndereco(cadastroEndereco);

        if (result > 0) {
            int idEndereco;
            idEndereco = cadastroEnderecoDao.consultarUltimoEndereco();
            cadastroSupermercado.setEndereco_idEndereco(idEndereco);
            cadastroSupermercado.setId(Integer.parseInt(idSupermercado));

            result = cadastroSupermercadoDao.atualizarEnderecoSupermercado(cadastroSupermercado);
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
