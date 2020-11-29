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
    <link rel="stylesheet" href="assets/bootstrap-4.5.3/css/bootstrap.css" />
    <link rel="stylesheet" href="assets/toastr/toastr.css" />
    <!-- Estilos propios -->
    <link rel="stylesheet" href="css/persona.css">
</head>

<body>
    <div class="container">
        <br>
        <div class="row">
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header">Mantenimiento de Personas</div>
                    <div class="card-body">
                        <form action="persona" method="POST">
                            <input type="hidden" id="hddId" name="id" value="${persona.getId()}" />
                            <div class="row form-group">
                                <label for="txtApellidoPaterno" class="col-form-label col-sm-3">Apellido
                                    Paterno:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="apellidoPaterno" id="txtApellidoPaterno"
                                        class="form-control" value="${persona.apellidoPaterno}">
                                </div>
                            </div>
                            <div class="row form-group">
                                <label for="txtApellidoMaterno" class="col-form-label col-sm-3">Apellido
                                    Materno:</label>
                                <div class="col-sm-6">
                                    <input type="text" name="apellidoMaterno" id="txtApellidoMaterno"
                                        class="form-control" value="${persona.apellidoPaterno}">
                                </div>
                            </div>
                            <div class="row form-group">
                                <label for="txtNombres" class="col-form-label col-sm-3">Nombres:</label>
                                <div class="col-sm-8">
                                    <input type="text" name="nombres" id="txtNombres" class="form-control"
                                        value="${persona.nombres}">
                                </div>
                            </div>
                            <div class="row form-group">
                                <label for="txtFechaNacimiento" class="col-form-label col-sm-3">Fecha de
                                    Nacimiento:</label>
                                <div class="col-sm-4">
                                    <input type="text" name="fechaNacimiento" id="txtFechaNacimiento"
                                        class="form-control" value="${persona.fechaNacimiento}">
                                </div>
                            </div>
                            <div class="row form-group">
                                <div class="col-sm-9 offset-sm-3">
                                    <button type="submit" class="btn btn-sm btn-primary"
                                        onClick="return validar();">Guardar</button>
                                    <a href="${pageContext.request.contextPath}/persona?accion=cancelar"
                                        class="btn btn-sm btn-light">Cancelar</a>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-body text-right">
                        <a href="${pageContext.request.contextPath}/persona?accion=eliminarTodos"
                            class="btn btn-sm btn-warning" onClick="return eliminarTodos(event);">Eliminar Todos</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-sm-12">
                <div class="card">
                    <div class="card-header">Lista de Personas</div>
                    <div class="card-body">
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
                                        <a href="${pageContext.request.contextPath}/persona?accion=editar&id=<%=persona.getId()%>"
                                            class="btn btn-sm btn-primary">Editar</a>
                                        <a href="${pageContext.request.contextPath}/persona?accion=eliminar&id=<%=persona.getId()%>"
                                            class="btn btn-sm btn-danger" onClick='return eliminar(event);'>Eliminar</a>
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
        </div>
    </div>
    <div class="modal" tabindex="-1" id="mdlConfirmar">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title">Confirmar Operación</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="alert alert-warning">
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="chkConfirmar">
                            <label class="custom-control-label" for="chkConfirmar">¿Estas seguro de realizar esta
                                acción?</label>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-light" data-dismiss="modal">Cancelar</button>
                    <button id="btnConfirmar" type="button" class="btn btn-primary">Confirmar</button>
                </div>
            </div>
        </div>
    </div>
    <!--Scripts externos-->
    <script src="assets/jquery/jquery-3.5.1.js"></script>
    <script src="assets/bootstrap-4.5.3/js/bootstrap.js"></script>
    <script src="assets/toastr/toastr.js"></script>
    <!--Scripts propios-->
    <script src="js/persona.js"></script>
</body>

</html>