(function () {

    let selectTipoPersona = $('#SelectTipoPersona');
    let tipoPersona = '';

    let contenedorPersonaFisica = $('#ContenedorPersonaFisica');
    let contenedorPersonaMoral = $('#ContenedorPersonaMoral');
    let contenedorRFC = $('#ContenedorRFC');
    let inputNombreCliente = $('#inputNombreCliente');
    let inputPaternoCliente = $('#inputPaternoCliente');
    let inputRazonSocialCliente = $('#inputRazonSocialCliente');
    let contenedorContactoCliente = $('#ContenedorContactoCliente');
    let inputContactoCliente = $('#inputContactoCliente');

    // Ocultar los campos de nom,bres tanto de persona física como moral.
    contenedorPersonaFisica.hide();
    contenedorPersonaMoral.hide();
    contenedorRFC.hide();
    contenedorContactoCliente.hide();

    selectTipoPersona.on('change', function () {

        //tipoPersona = $('#SelectTipoPersona option:selected').text()// regresa la cadena del valor seleccionado (PERSONA FÍSICA/PERSONA MORAL)
        tipoPersona = $('#SelectTipoPersona option:selected').val() // Regresa el valor que se le asigna a la propiedad value (1 - PERSONA FÍSICA/ 2 - PERSONA MORAL)
        console.log('tipoPersona = ' + tipoPersona);
        // Si es de tipo Persona física 
        if (tipoPersona == 1) {
            console.log('Seleccionó tipo de persona física');
            // mostrar campo de nombres para la persona física.
            contenedorPersonaFisica.show();
            // ocultar el campo de contacto para la persona moral
            contenedorPersonaMoral.hide();
            // ocultar el campo de RFC
            contenedorRFC.hide();
            // agregra propiedad name="nombre" al campo de nombres
            inputNombreCliente.prop("name","nombre");
            // agregar que sea campo obligatorio el de los nombres
            inputNombreCliente.prop("required", true);
            // agregar que sea campo obligatorio el de apellido paterno
            inputPaternoCliente.prop("required", true);
            // ocultar campo de contacto
            contenedorContactoCliente.hide();
            // quitar que el campo de contacto sea obligatorio
            inputRazonSocialCliente.removeAttr("required");
            // quitar que sea obligatorio el campo de contacto cliente
            inputContactoCliente.removeAttr("required");
        } else {
            console.log('Seleccionó tipo de persona moral');
            // mostrar campo de contacto para la persona moral.
            contenedorPersonaMoral.show();
            // ocultar el campo de nombres para la persona física
            contenedorPersonaFisica.hide();
            // mostrar el campo de RFC
            contenedorRFC.show();
            // quitar la propiedad name="nombre" al campo de nombres
            inputNombreCliente.removeAttr("name");
            // quitar la que sea campo obligatorio el de los nombres
            inputNombreCliente.removeAttr("required");
            // quitar la que sea campo obligatorio el de apellido paterno
            inputPaternoCliente.removeAttr("required");
            // mostrar campo de contacto
            contenedorContactoCliente.show();
            // agergar propiedad name="nombre" 
            inputRazonSocialCliente.prop("name", 'nombre');
            // agregar que el campo de razón social sea obligatorio
            inputRazonSocialCliente.prop("required", true);
            // agregra que el campo de contacto sea obligatorio
            inputContactoCliente.prop("required", true);
        }
        
    });
    

})()