$(document).ready(function () {
    console.log("javascript y jequey para formulario de equipos listo.");

    // Declarar todos los atributos.
    let selectMarca = $('#SelectMarca');
    let divCategoria = $('#categoria');
    let valorCategoriaParrafo = $('#valorCategoria');
    let ipLabel = $('#ip');    
    let codigoInstaladorLabel = $('#codigoInstalador');   
    let codigoMaestroLabel = $('#codigoMaestro');   
    let codigoVerificacionLabel = $('#codigoVerificacion');   
    let esclavoLabel = $('#esclavo');   
    let fechaUltimoMantenimientoLabel = $('#fechaUltimoMantenimiento');   
    let ipFrenteCalleLabel = $('#ipFrenteCalle');   
    let ipMonitorFijaLabel = $('#ipMonitorFija');   
    let ipPanelCentralLabel = $('#ipPanelCentral');   
    let ipStandaloneLabel = $('#ipStandalone');   
    let macLabel = $('#mac');   
    let macComunicadorLabel = $('#macComunicador');   
    let maestroLabel = $('#maestro');   
    let modeloLabel = $('#modelo');   
    let modeloBotonLiberadorLabel = $('#modeloBotonLiberador');   
    let modeloChapaAreaLabel = $('#modeloChapaArea');   
    let modeloChapaModoAccesolabel = $('#modeloChapaModoAcceso');   
    let modeloChapaPuertaLabel = $('#modeloChapaPuerta');   
    let modeloControladorLabel = $('#modeloControlador');   
    let inputModeloFrenteCalleLabel = $('#inputModeloFrenteCalle');   
    let modeloLlaveroLabel = $('#modeloLlavero');   
    let modeloMonitorLabel = $('#modeloMonitor');   
    let modeloPanelLabel = $('#modeloPanel');   
    let modeloPanelCentralLabel = $('#modeloPanelCentral');   
    let inputModeloPuntoAccesoLabel = $('#inputModeloPuntoAcceso');   
    let modeloSirenasLabel = $('#modeloSirenas');   
    let modeloTecladoLabel = $('#modeloTeclado');   
    let numeroCamaraLabel = $('#numeroCamara');   
    let numeroDepartamentoLabel = $('#numeroDepartamento');   
    let numeroLlaverosLabel = $('#numeroLlaveros');   
    let numeroMonitoresLabel = $('#numeroMonitores');   
    let numeroPanelLabel = $('#numeroPanel');   
    let numeroPanelStandaloneLabel = $('#numeroPanelStandalone');   
    let numeroPuertosPanelLabel = $('#numeroPuertosPanel');   
    let numeroPuntosAccesoInstaladorLabel = $('#numeroPuntosAccesoInstalador');   
    let numeroSensoresTotalLabel = $('#numeroSensoresTotal');   
    let numeroSerieLabel = $('#numeroSerie');   
    let numeroSerieMonitorLabel = $('#numeroSerieMonitor');   
    let numeroSeriePanelLabel = $('#numeroSeriePanel');   
    let numeroSeriePanelStandaloneLabel = $('#numeroSeriePanelStandalone');   
    let numeroSerieSensorCableadoLabel = $('#numeroSerieSensorCableado');   
    let numeroSerieSensorInalambricoLabel = $('#numeroSerieSensorInalambrico');   
    let numeroTecladosLabel = $('#numeroTeclados');   
    let numeroZonaCableadasLabel = $('#numeroZonaCableadas');   
    let numeroZonaInalambricasLabel = $('#numeroZonaInalambricas');   
    let serieComunicadorLabel = $('#serieComunicador');   
    let serieDvrLabel = $('#serieDvr');   
    let serieFrenteCalleLabel = $('#serieFrenteCalle');   
    let serieP2pLabel = $('#serieP2p');   
    let tipoSensorCableadoLabel = $('#tipoSensorCableado');   
    let tipoSensorInalambricoLabel = $('#tipoSensorInalambrico');   
    let versionFirewareLabel = $('#versionFireware');   
    let zonaCuidarCableadoLabel = $('#zonaCuidarCableado');   
    let zonaCuidarInalambricoLabel = $('#zonaCuidarInalambrico');
    let modeloComunicadorLabel = $('#modeloComunicador');   
    let numeroSirenasLabel = $('#numeroSirenas'); 
    
    // Ocultar todos los atributos.
    divCategoria.hide();
    ipLabel.hide();    
    codigoInstaladorLabel.hide();   
    codigoMaestroLabel.hide(); 
    codigoVerificacionLabel.hide();  
    esclavoLabel.hide();
    fechaUltimoMantenimientoLabel.hide();   
    ipFrenteCalleLabel.hide(); 
    ipMonitorFijaLabel.hide();
    ipPanelCentralLabel.hide();
    ipStandaloneLabel.hide(); 
    macLabel.hide();
    macComunicadorLabel.hide();
    maestroLabel.hide();
    modeloLabel.hide();
    modeloBotonLiberadorLabel.hide();
    modeloChapaAreaLabel.hide();
    modeloChapaModoAccesolabel.hide();
    modeloChapaPuertaLabel.hide();
    modeloControladorLabel.hide();
    inputModeloFrenteCalleLabel.hide();
    modeloLlaveroLabel.hide();
    modeloMonitorLabel.hide();
    modeloPanelLabel.hide(); 
    modeloPanelCentralLabel.hide();   
    inputModeloPuntoAccesoLabel.hide(); 
    modeloSirenasLabel.hide();
    modeloTecladoLabel.hide();  
    numeroCamaraLabel.hide(); 
    numeroDepartamentoLabel.hide();
    numeroLlaverosLabel.hide();
    numeroMonitoresLabel.hide(); 
    numeroPanelLabel.hide();
    numeroPanelStandaloneLabel.hide(); 
    numeroPuertosPanelLabel.hide();  
    numeroPuntosAccesoInstaladorLabel.hide();   
    numeroSensoresTotalLabel.hide();
    numeroSerieLabel.hide();  
    numeroSerieMonitorLabel.hide();   
    numeroSeriePanelLabel.hide(); 
    numeroSeriePanelStandaloneLabel.hide();  
    numeroSerieSensorCableadoLabel.hide(); 
    numeroSerieSensorInalambricoLabel.hide(); 
    numeroTecladosLabel.hide(); 
    numeroZonaCableadasLabel.hide();   
    numeroZonaInalambricasLabel.hide();  
    serieComunicadorLabel.hide(); 
    serieDvrLabel.hide();   
    serieFrenteCalleLabel.hide();  
    serieP2pLabel.hide();  
    tipoSensorCableadoLabel.hide();
    tipoSensorInalambricoLabel.hide();  
    versionFirewareLabel.hide(); 
    zonaCuidarCableadoLabel.hide(); 
    zonaCuidarInalambricoLabel.hide();
    modeloComunicadorLabel.hide(); 
    numeroSirenasLabel.hide();

    // Leer el nímero de categoria correspondiente.
    let categoria = valorCategoriaParrafo.text();
    console.log('Numero de categoria text: ' + categoria);


    selectMarca.on('change', function () {

        tipoMarca = $('#SelectMarca option:selected').text()// regresa la cadena del valor seleccionado.
        console.log('tipoMarca con text = ' + tipoMarca);
        //tipoMarca = $('#SelectMarca option:selected').val() // Regresa el valor que se le asigna a la propiedad value con numero según el option.
        //console.log('tipoMarca con val = ' + tipoMarca);

        // Cuando se selecciona la marca Dahua
        if (tipoMarca == 'Dahua') {
            console.log('Dahua');
            modeloLabel.show();
            numeroCamaraLabel.show();
            serieDvrLabel.show();
            fechaUltimoMantenimientoLabel.show();
        } else {
            //modeloLabel.hide();
            //numeroCamaraLabel.hide();
            //serieDvrLabel.hide();
            //fechaUltimoMantenimientoLabel.hide();
            codigoVerificacionLabel.hide();
        }

        // Cuando se selecciona la marca: Hikvision/Epcom/Hiklook
        if (tipoMarca == 'Hikvision/Epcom/Hiklook') {
            console.log('Hikvision/Epcom/Hiklook');
            //modeloLabel.show();
            //numeroCamaraLabel.show();
            //serieDvrLabel.show();
            //fechaUltimoMantenimientoLabel.show();
            codigoVerificacionLabel.show();
        } else {
            //modeloLabel.hide();
            //numeroCamaraLabel.hide();
            //serieDvrLabel.hide();
            //fechaUltimoMantenimientoLabel.hide();
            codigoVerificacionLabel.hide();
        }
        
    });


    if (categoria == '1') {
        console.log('Sistema CCTV');

        selectMarca.on('change', function () {

            tipoMarca = $('#SelectMarca option:selected').text()// regresa la cadena del valor seleccionado Ej. Dahua.
            console.log('tipoMarca con text = ' + tipoMarca);
            //tipoMarca = $('#SelectMarca option:selected').val() // Regresa el valor que se le asigna a la propiedad value con numero según el option EJ. 1.
            //console.log('tipoMarca con val = ' + tipoMarca);
            
            // Cuando se selecciona la marca Dahua
            if (tipoMarca == 'Dahua') {
                console.log('Dahua');
                modeloLabel.show();
                numeroCamaraLabel.show();
                serieDvrLabel.show();
                fechaUltimoMantenimientoLabel.show();
            } else {
                //modeloLabel.hide();
                //numeroCamaraLabel.hide();
                //serieDvrLabel.hide();
                //fechaUltimoMantenimientoLabel.hide();
                codigoVerificacionLabel.hide();
            }
    
            // Cuando se selecciona la marca: Hikvision/Epcom/Hiklook
            if (tipoMarca == 'Hikvision/Epcom/Hiklook') {
                console.log('Hikvision/Epcom/Hiklook');
                //modeloLabel.show();
                //numeroCamaraLabel.show();
                //serieDvrLabel.show();
                //fechaUltimoMantenimientoLabel.show();
                codigoVerificacionLabel.show();
            } else {
                //modeloLabel.hide();
                //numeroCamaraLabel.hide();
                //serieDvrLabel.hide();
                //fechaUltimoMantenimientoLabel.hide();
                codigoVerificacionLabel.hide();
            }

        
        }); 

    }
    
    // Activar todos los campos correspondientes a opc = 2 (Sistema de Alarmas)
    if (categoria == '2') {
        console.log('Sistema de Alarmas');
    
    } 


});