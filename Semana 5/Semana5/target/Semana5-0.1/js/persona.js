function validar() {
    var txtApellidoPaterno = document.getElementById('txtApellidoPaterno');
    if (txtApellidoPaterno.value.trim() == '') {
        toastr.warning('Debe ingresar un apellido paterno', 'Alerta');
        txtApellidoPaterno.focus();
        return false;
    }

    return true;
}