var path = "http://localhost:8080/Semana4/usuario";

$(document).ready(function () {
    listar();

    $('#btnGuardar').on('click', function (e) {
        guardar();
    });

    $('body').on('click', 'button.btn', function (e) {
        var id = $(this).data('id');
    });
});

function listar() {
    $.ajax({
        type: "GET",
        url: path,
        success: function (response) {
            $tbody = $('#tblUsuarios tbody');
            $tbody.empty();

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

function guardar() {
    var id = 0;
    var usuario = {
        id: id,
        apellidoPaterno: $('#txtApellidoPaterno').val(),
        apellidoMaterno: $('#txtApellidoMaterno').val(),
        nombres: $('#txtNombres').val(),
        fechaNacimiento: $('#txtFechaNacimiento').val(),
        username: $('#txtUsername').val(),
        password: $('#txtPassword').val(),
    }

    $.ajax({
        type: "POST",
        url: path,
        data: usuario,
        dataType: "json",
        success: function (response) {
            toastr.success("Registro guardado correctamente");
            listar();
            limpiarForm();
        }
    });
}

function limpiarForm() {
    $('#txtApellidoPaterno').val('');
    $('#txtApellidoMaterno').val('');
    $('#txtNombres').val('');
    $('#txtFechaNacimiento').val('');
    $('#txtUsername').val('');
    $('#txtPassword').val('');
}