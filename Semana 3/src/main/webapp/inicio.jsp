<%-- 
    Document   : inicio
    Created on : 2 nov. 2020, 19:33:36
    Author     : Usuario
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenido <%= request.getParameter("username")%>!</h1>
        
        <% 
            out.println("Hola");
        %>
    </body>
</html>
