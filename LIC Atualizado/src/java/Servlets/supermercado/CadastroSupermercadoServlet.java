package Servlets.supermercado;

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
@WebServlet("/CadastroSupermercadoServlet")
public class CadastroSupermercadoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CadastroEndereco endereco = new CadastroEndereco();
        CadastroEnderecoDao enderecodao = new CadastroEnderecoDao();
        CadastroSupermercado cadastroSupermercado = new CadastroSupermercado();
        CadastroSupermercadoDao cadastroSupermercadoDao = new CadastroSupermercadoDao();

        String selecionado = request.getParameter("selecionado");
        String NomeFantasiaSupermercado = request.getParameter("NomeFantasiaSupermercado");
        String cnpjSupermercado = request.getParameter("cnpjSupermercado");
        String RazaoSocialSupermercado = request.getParameter("RazaoSocialSupermercado");
        String Logradouro = request.getParameter("logradouro");
        String Numero = request.getParameter("numero");
        String Complemento = request.getParameter("complemento");
        String Bairro = request.getParameter("bairro");
        String UF = request.getParameter("uf");
        String Pais = request.getParameter("pais");
        String Cep = request.getParameter("cep");

        int result = 0;

        if (selecionado != null) {

            endereco.setLogradouro(Logradouro);
            endereco.setNumero(Numero);
            endereco.setComplemento(Complemento);
            endereco.setBairro(Bairro);
            endereco.setUF(UF);
            endereco.setPais(Pais);
            endereco.setCep(Cep);

            result = enderecodao.inserirEndereco(endereco);

            if (result > 0) {
                int idEndereco;
                idEndereco = enderecodao.consultarUltimoEndereco();
                cadastroSupermercado.setEndereco_idEndereco(idEndereco);
            }
        }

        cadastroSupermercado.setNomeFantasiaSupermercado(NomeFantasiaSupermercado);
        cadastroSupermercado.setCnpjSupermercado(cnpjSupermercado);
        cadastroSupermercado.setRazaoSocialSupermercado(RazaoSocialSupermercado);

        result = cadastroSupermercadoDao.inserirSupermercado(cadastroSupermercado);

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
