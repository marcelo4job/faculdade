package Servlets.supermercado;

import Dao.CadastroSupermercadoDao;
import com.google.gson.Gson;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CadastroSupermercado;

/**
 *
 * @author Renan
 */
@WebServlet("/EditarSupermercadoServlet")
public class EditarSupermercadoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String idSupermercado = request.getParameter("idSupermercado");
        String RazaoSocialSupermercado = request.getParameter("RazaoSocialSupermercado");
        String NomeFantasiaSupermercado = request.getParameter("NomeFantasiaSupermercado");
        String cnpjSupermercado = request.getParameter("cnpjSupermercado"); 
        
        int id = Integer.parseInt(idSupermercado);

        CadastroSupermercado Supermercado = new CadastroSupermercado();
        CadastroSupermercadoDao editarSupermercadoDao = new CadastroSupermercadoDao();

        Supermercado.setId(id);
        Supermercado.setRazaoSocialSupermercado(RazaoSocialSupermercado);
        Supermercado.setNomeFantasiaSupermercado(NomeFantasiaSupermercado);
        Supermercado.setCnpjSupermercado(cnpjSupermercado);
        
        int result = 0;

        result = editarSupermercadoDao.editarSupermercado(Supermercado);

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
