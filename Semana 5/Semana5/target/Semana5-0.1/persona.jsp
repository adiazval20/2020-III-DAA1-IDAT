<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="pe.edu.idat.semana4.entity.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Personas</title>
        <!--Estilos externos-->
        <link rel="stylesheet" href="bootstrap-4.5.3/css/bootstrap.css"/>
    </head>
    <body>
        <div class="container">
            <br>
            <h2>Mantenimiento de Personas</h2>
            <br>
            <form action="persona" method="POST">
                <input type="hidden" id="hddId" name="id" value="${persona.getId()}"/>
                <div class="row form-group">
                    <label for="txtApellidoPaterno" class="col-form-label col-sm-3">Apellido Paterno:</label>
                    <div class="col-sm-6">
                        <input type="text" name="apellidoPaterno" id="txtApellidoPaterno" class="form-control" value="${persona.apellidoPaterno}">
                    </div>
                </div>
                <div class="row form-group">
                    <label for="txtApellidoMaterno" class="col-form-label col-sm-3">Apellido Materno:</label>
                    <div class="col-sm-6">
                        <input type="text" name="apellidoMaterno" id="txtApellidoMaterno" class="form-control" value="${persona.apellidoPaterno}">
                    </div>
                </div>
                <div class="row form-group">
                    <label for="txtNombres" class="col-form-label col-sm-3">Nombres:</label>
                    <div class="col-sm-8">
                        <input type="text" name="nombres" id="txtNombres" class="form-control" value="${persona.nombres}">
                    </div>
                </div>
                <div class="row form-group">
                    <label for="txtFechaNacimiento" class="col-form-label col-sm-3">Fecha de Nacimiento:</label>
                    <div class="col-sm-4">
                        <input type="text" name="fechaNacimiento" id="txtFechaNacimiento" class="form-control" value="${persona.fechaNacimiento}">
                    </div>
                </div>
                <div class="row form-group">
                    <div class="col-sm-9 offset-sm-3">
                        <button type="submit" class="btn btn-primary">Guardar</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="col-sm-12">
                    <table class="table">
                        <thead>
                            <tr>
                                <th>Nro</th>
                                <th>Apellido Paterno</th>
                                <th>Apellido Materno</th>
                                <th>Nombres</th>
                                <th>Fecha de Nacimiento</th> 
                                <th>Opciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% 
                                ArrayList<Persona> personas = (ArrayList<Persona>)request.getAttribute("personas");
                                int index = 0;
                                for(Persona persona : personas) {
                                    index++;
                                    %>
                                    <tr>
                                        <td><%=index %></td>
                                        <td><%=persona.getApellidoPaterno()%></td>
                                        <td><%=persona.getApellidoMaterno() %></td>
                                        <td><%=persona.getNombres() %></td>
                                        <td><%=persona.getFechaNacimiento() %></td> 
                                        <td>
                                            <a href="${pageContext.request.contextPath}/persona?accion=editar&id=<%=persona.getId()%>" class="btn btn-sm btn-primary">Editar</a>
                                            <a href="${pageContext.request.contextPath}/persona?accion=eliminar&id=<%=persona.getId()%>" class="btn btn-sm btn-danger">Eliminar</a>
                                        </td>
                                    </tr>
                                    <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>