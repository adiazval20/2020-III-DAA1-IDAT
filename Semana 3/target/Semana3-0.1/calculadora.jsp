<%-- 
    Document   : calculadora
    Created on : 2 nov. 2020, 19:54:19
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
        <form action="Calculadora" method="POST">
            <h3>Operac&oacute;n suma:</h3>
            <label>N&uacute;mero A:</label>
            <input type="text" name="numeroA"/><br>
            <label>N&uacute;mero B:</label>
            <input type="text" name="numeroB"/><br>
            <input type="submit" value="Procesar"/>
        </form>
        <h4>La suma de ambos n&uacute;meros es: <%= request.getAttribute("resultado") == null ? "" : request.getAttribute("resultado") %></h4>
    </body>
</html>
