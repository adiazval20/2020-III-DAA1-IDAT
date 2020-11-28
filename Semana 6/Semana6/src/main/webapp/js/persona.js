$(document).ready(function () {
    $('#mdlEliminar').modal({
        show: false
    });

    $('#btnConfirmar').on('click', function () {
        funConfirmar();
    });
});

function validar() {
    var $txtApellidoPaterno = $('#txtApellidoPaterno');
    if ($txtApellidoPaterno.val().trim() == '') {
        toastr.warning('Debe ingresar un apellido paterno', 'Alerta');
        $txtApellidoPaterno.focus();
        return false;
    }

    var $txtApellidoMaterno = $('#txtApellidoMaterno');
    if ($txtApellidoMaterno.val().trim() == '') {
        toastr.warning('Debe ingresar un apellido materno', 'Alerta');
        $txtApellidoMaterno.focus();
        return false;
    }

    var $txtNombres = $('#txtNombres');
    if ($txtNombres.val().trim() == '') {
        toastr.warning('Debe ingresar sus nombres', 'Alerta');
        $txtNombres.focus();
        return false;
    }

    var $txtFechaNacimiento = $('#txtFechaNacimiento');
    if ($txtFechaNacimiento.val().trim() == '') {
        toastr.warning('Debe ingresar su fecha de nacimiento', 'Alerta');
        $txtFechaNacimiento.focus();
        return false;
    }

    return true;
}

var funConfirmar;
function eliminar(e) {
    funConfirmar = function () {
        document.location = e.target.href;
        funConfirmar = undefined;
    }

    $('#mdlEliminar').modal('show');
    return false;
}
