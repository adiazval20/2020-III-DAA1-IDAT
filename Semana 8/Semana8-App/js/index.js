$(document).ready(function () {
    listar();
});

function listar() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/Semana4/usuario",
        success: function (response) {
            console.log(response);

            $tbody = $('#tblUsuarios tbody');
            response.forEach((p, i) => {
                var tr = '<tr>';
                tr += '<td>' + (i + 1) + '</td>';
                tr += '<td>' + p.apellidoPaterno + '</td>';
                tr += '<td>' + p.apellidoMaterno + '</td>';
                tr += '<td>' + p.nombres + '</td>';
                tr += '<td>' + p.fechaNacimiento + '</td>';
                tr += '<td>' + p.username + '</td>';
                tr += '<td>';
                tr += '<button class="btn btn-sm btn-primary" data-id="' + p.id + '">Editar</button>';
                tr += '<button class="btn btn-sm btn-danger ml-2" data-id="' + p.id + '">Eliminar</button>';
                tr += '</td>';
                tr += '</tr>';
                $tbody.append(tr);
            });
        }
    });
}