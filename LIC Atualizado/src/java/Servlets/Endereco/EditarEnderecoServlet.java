package Servlets.Endereco;

import Dao.CadastroEnderecoDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroEndereco;

/**
 *
 * @author Renan
 */
@WebServlet("/EditarEnderecoServlet")
public class EditarEnderecoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idEndereco = request.getParameter("idEndereco");
        String Logradouro = request.getParameter("Logradouro");
        String Numero = request.getParameter("Numero");
        String Complemento = request.getParameter("Complemento"); 
        String Bairro = request.getParameter("Bairro");
        String UF = request.getParameter("UF");
        String Pais = request.getParameter("Pais");
        String Cep = request.getParameter("Cep");
        String Cidade = request.getParameter("Localidade");
        
        int id = Integer.parseInt(idEndereco);

        CadastroEndereco Endereco = new CadastroEndereco();
        CadastroEnderecoDao editarEnderecoDao = new CadastroEnderecoDao();

        Endereco.setId(id);
        Endereco.setLogradouro(Logradouro);
        Endereco.setNumero(Numero);
        Endereco.setComplemento(Complemento);
        Endereco.setBairro(Bairro);
        Endereco.setUF(UF);
        Endereco.setPais(Pais);
        Endereco.setCep(Cep);
        Endereco.setLocalidade(Cidade);
        
        int result = 0;

        result = editarEnderecoDao.editarEndereco(Endereco);

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
