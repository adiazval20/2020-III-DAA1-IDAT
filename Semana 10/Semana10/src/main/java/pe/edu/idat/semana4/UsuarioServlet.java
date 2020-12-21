package pe.edu.idat.semana4;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.edu.idat.semana4.dao.DbConnection;
import pe.edu.idat.semana4.dao.UsuarioDao;
import pe.edu.idat.semana4.entity.Persona;
import pe.edu.idat.semana4.entity.Usuario;
import pe.edu.idat.semana4.repository.UsuarioRepository;

@WebServlet(name = "UsuarioServlet", urlPatterns = "/usuario")
public class UsuarioServlet extends HttpServlet {

    private UsuarioRepository repo;

    @Override
    public void init() throws ServletException {
        repo = UsuarioRepository.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        configResponse(resp);

        int id = Integer.parseInt(req.getParameter("id"));
        Usuario usuario = repo.find(id);
        
        String apellidoPaterno = req.getParameter("apellidoPaterno");
        String apellidoMaterno = req.getParameter("apellidoMaterno");
        String nombres = req.getParameter("nombres");
        String fechaNacimiento = req.getParameter("fechaNacimiento");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        usuario.setApellidoPaterno(apellidoPaterno);
        usuario.setApellidoMaterno(apellidoMaterno);
        usuario.setNombres(nombres);
        usuario.setFechaNacimiento(fechaNacimiento);
        usuario.setUsername(username);
        usuario.setPassword(password);
        
        usuario = repo.save(usuario);
        
        Gson gson = new Gson();
        String json = gson.toJson(usuario);
        PrintWriter pw = resp.getWriter();
        pw.println(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            configResponse(resp);
            
            UsuarioDao dao = UsuarioDao.getInstance();
            
            Gson gson = new Gson();
            String json = gson.toJson(dao.list());
            PrintWriter pw = resp.getWriter();
            pw.println(json);
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void configResponse(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
