<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="Login" method="POST">
            <label for="username">Usuario:</label>
            <input type="text" name="username" id="username" placeholder="Usuario"/>
            <br><br>
            <label for="password">Contraseña:</label>
            <input type="password" name="password" id="password" placeholder="Contraseña"/>
            <br><br>
            <input type="submit" value="Ingresar"/>
        </form>
    </body>
</html>
