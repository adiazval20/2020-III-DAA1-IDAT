package pe.edu.idat.semana4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pe.edu.idat.semana4.entity.Persona;

@WebServlet(name = "PersonaServlet", urlPatterns = "/persona")
public class PersonaServlet extends HttpServlet {

    private List<Persona> personas = new ArrayList<>();

    @Override
    public void init() throws ServletException {
        personas.add(new Persona(1, "Diaz", "Valdiviezo", "Andy", "01/01/1990"));
        personas.add(new Persona(1, "Perez", "Sanchez", "Maria", "01/01/1995"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String apellidoPaterno = req.getParameter("apellidoPaterno");
        String apellidoMaterno = req.getParameter("apellidoMaterno");
        String nombres = req.getParameter("nombres");
        String fechaNacimiento = req.getParameter("fechaNacimiento");
        int id = personas.size() + 1;
        
        Persona persona = new Persona(id, apellidoPaterno, apellidoMaterno, nombres, fechaNacimiento);
        this.personas.add(persona);
        
        req.setAttribute("personas", this.personas);
        RequestDispatcher rd = req.getRequestDispatcher("persona.jsp");
        rd.include(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("personas", this.personas);

        RequestDispatcher rd = req.getRequestDispatcher("persona.jsp");
        rd.include(req, resp);
    }

}
