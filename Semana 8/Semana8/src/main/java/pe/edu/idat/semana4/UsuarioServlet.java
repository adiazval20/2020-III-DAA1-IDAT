package pe.edu.idat.semana4;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.edu.idat.semana4.entity.Persona;
import pe.edu.idat.semana4.entity.Usuario;

@WebServlet(name = "UsuarioServlet", urlPatterns = "/usuario")
public class UsuarioServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");

        Gson gson = new Gson();
        
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario(1, "Perez", "Rojas", "Miguel", "01/01/2000", "jrojas", "123456"));
        usuarios.add(new Usuario(2, "Díaz", "Cabrejos", "Alejandra", "01/01/1998", "dcabrejos", "admin"));

        String json = gson.toJson(usuarios);
        PrintWriter pw = resp.getWriter();
        pw.println(json);
    }
}
