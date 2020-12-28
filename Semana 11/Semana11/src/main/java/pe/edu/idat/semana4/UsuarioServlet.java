package pe.edu.idat.semana4;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private UsuarioDao dao;

    @Override
    public void init() throws ServletException {
        repo = UsuarioRepository.getInstance();
        dao = UsuarioDao.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        configResponse(resp);

        Map<String, Object> response = new HashMap<>();
        response.put("rpta", 1);
        response.put("msg", "ok");

        int id = Integer.parseInt(req.getParameter("id"));
        String apellidoPaterno = req.getParameter("apellidoPaterno");
        String apellidoMaterno = req.getParameter("apellidoMaterno");
        String nombres = req.getParameter("nombres");
        String fechaNacimiento = req.getParameter("fechaNacimiento");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Usuario usuario;
        try {
            usuario = dao.find(id);
            usuario.setApellidoPaterno(apellidoPaterno);
            usuario.setApellidoMaterno(apellidoMaterno);
            usuario.setNombres(nombres);
            usuario.setFechaNacimiento(fechaNacimiento);
            usuario.setUsername(username);
            usuario.setPassword(password);
            usuario = dao.save(usuario);
            response.put("data", usuario);
        } catch (SQLException ex) {
            response.put("msg", ex.getMessage());
        }

        Gson gson = new Gson();
        String json = gson.toJson(response);
        PrintWriter pw = resp.getWriter();
        pw.println(json);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        configResponse(resp);
        
        Gson gson = new Gson();
        Map<String, Object> response = new HashMap<>();
        response.put("rpta", 1);
        response.put("msg", "ok");

        try {
            if (req.getParameter("id") != null) {
                int id = Integer.parseInt(req.getParameter("id"));
                Usuario usuario = dao.find(id);
                response.put("data", usuario);
            } else {
                List<Usuario> usuarios = dao.list();
                response.put("data", usuarios);
            }
        } catch (SQLException ex) {
            response.put("rpta", -1);
            response.put("msg", ex.getMessage());
        }

        String json = gson.toJson(response);
        PrintWriter pw = resp.getWriter();
        pw.println(json);
    }

    private void configResponse(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.addHeader("Access-Control-Allow-Origin", "*");
        resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
        resp.addHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}
