package pe.edu.idat.semana4;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    private Persona persona = new Persona();

    @Override
    public void init() throws ServletException {
        personas.add(new Persona(1, "Diaz", "Valdiviezo", "Andy", "01/01/1990"));
        personas.add(new Persona(2, "Perez", "Sanchez", "Maria", "01/01/1995"));

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        persona = new Persona();

        if (req.getParameter("id") != null) {
            final int personaId = Integer.parseInt(req.getParameter("id"));

            Optional<Persona> optPersona = personas.stream()
                    .filter(p -> p.getId() == personaId)
                    .reduce((p, v) -> {
                        throw new IllegalStateException("No se ha encontrado un solo elemento");
                    });
            if (optPersona.isPresent()) {
                persona = optPersona.get();
            }
        }

        String apellidoPaterno = req.getParameter("apellidoPaterno");
        String apellidoMaterno = req.getParameter("apellidoMaterno");
        String nombres = req.getParameter("nombres");
        String fechaNacimiento = req.getParameter("fechaNacimiento");
        
        persona.setApellidoPaterno(apellidoPaterno);
        persona.setApellidoMaterno(apellidoMaterno);
        persona.setNombres(nombres);
        persona.setFechaNacimiento(fechaNacimiento);
        
        if(persona.getId() == 0) {
            persona.setId(personas.size() + 1);
            personas.add(persona);
        } else {
            personas.stream().forEach(p -> {
                if(p.getId() == persona.getId()) {
                    p = persona;
                }
            });
        }
        
        persona = new Persona();

        forwardPage(req, resp, "persona.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id;
        persona = new Persona();

        String accion = req.getParameter("accion");
        accion = (accion == null ? "" : accion);

        switch (accion) {
            case "editar":
                id = Integer.parseInt(req.getParameter("id"));
                /*persona = personas.stream()
                        .filter(p -> p.getId() == id)
                        .collect(Collectors.toList()).get(0);*/

                Optional<Persona> optPersona = personas.stream()
                        .filter(p -> p.getId() == id)
                        .reduce((p, v) -> {
                            throw new IllegalStateException("No se ha encontrado un solo elemento");
                        });
                if (optPersona.isPresent()) {
                    persona = optPersona.get();
                }

                break;
            case "eliminar":
                break;
        }

        forwardPage(req, resp, "persona.jsp");
    }

    private void forwardPage(HttpServletRequest req, HttpServletResponse resp, String page) throws ServletException, IOException {
        resp.setContentType("text/html");

        req.setAttribute("personas", this.personas);
        req.setAttribute("persona", this.persona);

        RequestDispatcher rd = req.getRequestDispatcher(page);
        rd.forward(req, resp);
    }
}
