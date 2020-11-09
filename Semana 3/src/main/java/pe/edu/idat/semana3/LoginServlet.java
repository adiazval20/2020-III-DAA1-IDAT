package pe.edu.idat.semana3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = "/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();

        String username = req.getParameter("username").trim();
        String password = req.getParameter("password").trim();
        boolean conErrores = false;

        if (username.equals("")) {
            pw.println("Debe ingresar un nombre de usuario");
            conErrores = true;
        }

        if (password.equals("")) {
            pw.println("Debe ingresar una contraseña");
            conErrores = true;
        }

        if (!username.equals("admin")|| !password.equals("1234")) {
            pw.println("Las credenciales son incorrectas");
            conErrores = true;
        }
        
        pw.println(conErrores);

        if (conErrores) {
            RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
            rd.include(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("inicio.jsp");
            rd.forward(req, resp);
        }
    }

}
