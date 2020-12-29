var path = "http://localhost:8080/Semana4/usuario";

$(document).ready(function () {
    listar();

    $('#btnGuardar').on('click', function (e) {
        guardar();
    });

    $('body').on('click', 'button.btn', function (e) {
        var id = $(this).data('id');
        editar(id);
    });
});

function listar() {
    $.ajax({
        type: "GET",
        url: path,
        success: function (response) {
            $tbody = $('#tblUsuarios tbody');
            $tbody.empty();

            console.log(response);

            response.data.forEach((u, i) => {
                var tr = '<tr>';
                tr += '<td>' + (i + 1) + '</td>';
                tr += '<td>' + u.apellidoPaterno + '</td>';
                tr += '<td>' + u.apellidoMaterno + '</td>';
                tr += '<td>' + u.nombres + '</td>';
                tr += '<td>' + u.fechaNacimiento + '</td>';
                tr += '<td>' + u.username + '</td>';
                tr += '<td>';
                tr += '<button class="btn btn-sm btn-primary" data-id="' + u.id + '">Editar</button>';
                tr += '<button class="btn btn-sm btn-danger ml-2" data-id="' + u.id + '">Eliminar</button>';
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

function editar(valId) {
    $.ajax({
        type: "GET",
        url: path,
        data: {
            id: valId
        },
        success: function (response) {
            $tbody = $(' #card-body');

            var u = response.data;
            $('#txtApellidoPaterno').val(u.apellidoPaterno);
            $('#txtApellidoMaterno').val(u.apellidoMaterno);
            $('#txtNombres').val(u.nombres);
            $('#txtFechaNacimiento').val(u.fechaNacimiento);
            $('#txtUsername').val(u.username);
            $('#txtPassword').val(u.password);
        }
    });
}