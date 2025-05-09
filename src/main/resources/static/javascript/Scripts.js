$(document).ready(function () {
    console.log("javascript y JQuery funcionando.");

    // Quitar el rol de 'Usuario' en el modal de Crear Usuario, que vive dentro de listaUsuario.html
    $("#rollSelect option[value='1']").hide();
    
    /** 
     * Para colocar la palabra activa o inactivo en el formulario de detalles usuario
    */
    let switchDestalleUsuario = $('#flexSwitchCheckDefault');
    let switchDestalleUsuarioText = $('#flexSwitchCheckDefaultText');
    let selectRol = $('#rollSelect');
    let razonSocial = $('#devRazonSocial');
    let inputRazonSocial = $('#inputRazonSocialUsuario');
    let contactoUsuario = $('#devContactoUsuario');
    let inputContactoUsuario = $('#inputContactoUsuario');
    let telefonoPersonal = $('#devTelPersonalUsuario');
    let inputTelefonoPersonal= $('#inputTelPersonalUsuario');
    let telefonoOficina = $('#devTelOficinaUsuario');
    let devTelefonoOficina= $('#inputTelOficinaUsuario');
    let comentarios = $('#devComentarios');
    let textAreaComentarios = $('#comentariosUsuario');

    // obtener el valor selecionado del select en la vista editarUsuario.html.
    let selectRolEditarUusuario = $('#rollSelect option:selected').text();

    // colocar la palabra Activado o Bloqueado como el usaurio actual se encuentra.
    if (switchDestalleUsuario.is(':checked')) {
        switchDestalleUsuarioText.text('Activado');
    } else {
        switchDestalleUsuarioText.text('Bloqueado');
    }

    // cuando se cambia de manera dinámica el valor precionando el switch
    switchDestalleUsuario.change(function (e) {
        if (switchDestalleUsuario.is(':checked')) {
            switchDestalleUsuarioText.text('Activado');
        } else {
            switchDestalleUsuarioText.text('Bloqueado');
        }
    });

    // Ocultar la sección que muestra el id del usuario en detalles Usuario.
    $('#idUsuario').hide();
    
    // Ocultar el inputNameAvatar para que el usuario no pueda modificar el nombre.
    $('#inputNameAvatar').hide();

    // lógica para ocultar campos cuando no se asigna el rol de Cliente, en el modal para crear usuario.
    razonSocial.hide();
    contactoUsuario.hide();
    telefonoPersonal.hide();
    telefonoOficina.hide();
    comentarios.hide();
    selectRol.change(function (e) { 
        // Validando si seleccionó el rol de Cliente (op 5).
        if ($(this).val() == 5) {
            console.log('Cliente');
            razonSocial.show();
            inputRazonSocial.prop('required', true);
            contactoUsuario.show();
            inputContactoUsuario.prop('required', true);
            telefonoPersonal.show();
            inputTelefonoPersonal.prop('required', true);
            telefonoOficina.show();
            devTelefonoOficina.prop('required', true);
            comentarios.show();
            textAreaComentarios.prop('required', true);
        } else {
            console.log('Rol Interno');
            razonSocial.hide();
            inputRazonSocial.prop('required', false);
            contactoUsuario.hide();
            inputContactoUsuario.prop('required', false);
            telefonoPersonal.hide();
            inputTelefonoPersonal.prop('required', false);
            telefonoOficina.hide();
            devTelefonoOficina.prop('required', false);
            comentarios.hide();
            textAreaComentarios.prop('required', false);
        }
        
    });

    /** 
     * Ocultar o mostrar campos al cargar por primera vez la vista de editar usuario.
     * Ya que como arriba está el change se muestran o se ocultan hasta que se detecta un cambio,
     * pero si está en rol cliente apesar que se deben mostrar no se muestran, hasta que se cambia
     * a otro rol y se regresa a este mismo.  
    */
     if (selectRolEditarUusuario == 'Cliente') {
        razonSocial.show();
        inputRazonSocial.prop('required', true);
        contactoUsuario.show();
        inputContactoUsuario.prop('required', true);
        telefonoPersonal.show();
        inputTelefonoPersonal.prop('required', true);
        telefonoOficina.show();
        devTelefonoOficina.prop('required', true);
        comentarios.show();
        textAreaComentarios.prop('required', true);
    } else {
        console.log('Rol Interno');
        razonSocial.hide();
        inputRazonSocial.prop('required', false);
        contactoUsuario.hide();
        inputContactoUsuario.prop('required', false);
        telefonoPersonal.hide();
        inputTelefonoPersonal.prop('required', false);
        telefonoOficina.hide();
        devTelefonoOficina.prop('required', false);
        comentarios.hide();
        textAreaComentarios.prop('required', false);
    }

});

// Pasar a mayúsculas lo que se escribe en los campos.
function mayus(e) {
    e.value = e.value.toUpperCase();
}

// ocultar ids: folio, cliente, sucursales de Sucursal.
let folioSucursal = $('#folio');
let clienteSucursal = $('#cliente');
let serviciosSucursal = ('#servicios');

// ocultar a la vista.
folioSucursal.hide();
clienteSucursal.hide();
serviciosSucursal.hide();