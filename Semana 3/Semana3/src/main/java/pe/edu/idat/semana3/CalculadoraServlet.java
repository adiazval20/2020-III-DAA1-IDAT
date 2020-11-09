package pe.edu.idat.semana3;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CalculadoraServlet", urlPatterns = "/Calculadora")
public class CalculadoraServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("calculadora.jsp");
        rd.include(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int numeroA = Integer.parseInt(req.getParameter("numeroA"));        
        int numeroB = Integer.parseInt(req.getParameter("numeroB"));
        int resultado = numeroA + numeroB;
        
        req.setAttribute("resultado", resultado);
        
        RequestDispatcher rd = req.getRequestDispatcher("calculadora.jsp");
        rd.include(req, resp);
    }
    
    // // Completar el formulario calculadora.jsp para que se puedan desarrollar 
    // // todas las operaciones de la calculadora que trabajamos en las sesiones anteriores
    // // Utilizar las clases Calculadora y CalculadoraCientifica

    // <input type="submit" name="sumar" value="Sumar">

    // if(request.getParameter("sumar") != null) {

    // }
}
