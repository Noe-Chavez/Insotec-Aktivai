package com.uacm.aktivai.controller;

import com.uacm.aktivai.model.Sucursal;
import com.uacm.aktivai.model.Usuario;
import com.uacm.aktivai.service.SucursalService;
import com.uacm.aktivai.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/sucursales")
public class SucursalController {

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private SucursalService sucursalService;

    Logger logger = LoggerFactory.getLogger(SucursalController.class);

    private Long idUsuario;

    @PostMapping("/guardar")
    public String guardarSucursalRedireccionServicioNuevo(Sucursal sucursal) {
        logger.debug("******** Entrando al metodo guardarRedireccionServicioNuevo ********");
        logger.debug("Sucursal: " +  sucursal);
        List<Sucursal> sucursalesTemporales = sucursalService.obtenerSucursalTemporal();
        logger.debug("sucursalesTemporales: " +  sucursalesTemporales);
        int cantidadRegistrosTemporales = sucursalesTemporales.size();
        // Validar si la lista de sucursales temporales contienen un único elemento, indica que registro y se dió en el botón guardar.
        if (cantidadRegistrosTemporales == 1) {
            // Actualizar los datos de la sucursal temporal por los recibidos por el formulario, vinculados al cliente.
            sucursalesTemporales.get(0).setNombre(sucursal.getNombre());
            sucursalesTemporales.get(0).setCalle(sucursal.getCalle());
            sucursalesTemporales.get(0).setNumero(sucursal.getNumero());
            sucursalesTemporales.get(0).setColonia(sucursal.getColonia());
            sucursalesTemporales.get(0).setCodigoPostal(sucursal.getCodigoPostal());
            sucursalesTemporales.get(0).setAlcaldia(sucursal.getAlcaldia());
            sucursalesTemporales.get(0).setContacto(sucursal.getContacto());
            sucursalesTemporales.get(0).setTelefono(sucursal.getTelefono());
            sucursalesTemporales.get(0).setCorreo(sucursal.getCorreo());
            Sucursal sucursalGuardada = sucursalService.guardarSucursal(sucursalesTemporales.get(0));
            logger.debug("sucursalGuardada: " +  sucursalGuardada);
        } else {// de lo contrario o refresco el navegador en el formulario de sucursal, registrando nuevamente otro temporal, o dió en cancelar.
            // Actualizar los datos de la sucursal temporal por los recibidos por el formulario, vinculados al cliente.
            sucursalesTemporales.get(0).setNombre(sucursal.getNombre());
            sucursalesTemporales.get(0).setCalle(sucursal.getCalle());
            sucursalesTemporales.get(0).setNumero(sucursal.getNumero());
            sucursalesTemporales.get(0).setColonia(sucursal.getColonia());
            sucursalesTemporales.get(0).setCodigoPostal(sucursal.getCodigoPostal());
            sucursalesTemporales.get(0).setAlcaldia(sucursal.getAlcaldia());
            sucursalesTemporales.get(0).setContacto(sucursal.getContacto());
            sucursalesTemporales.get(0).setTelefono(sucursal.getTelefono());
            sucursalesTemporales.get(0).setCorreo(sucursal.getCorreo());
            Sucursal sucursalGuardada = sucursalService.guardarSucursal(sucursalesTemporales.get(0));
            logger.debug("sucursalGuardada: " +  sucursalGuardada);
            // Quitar la sucursal 0 que ya fue llenada de la lista de sucursales temporales.
            sucursalesTemporales.remove(0);
            logger.debug("sucursalesTemporales a eleminar de la DB: " +  sucursalesTemporales);
            // Eliminar el resto que diga temporal
            sucursalService.eliminarSucursalesTemporales(sucursalesTemporales);
        }

        return "home";
    }

    @GetMapping("/tablaSucursales/{idUsuario}")
    public String listaSucursales(@PathVariable("idUsuario") Long idUsuario, Model model) {
        logger.debug("******** Entrando al metodo listaSucursales ********");
        logger.debug("id del cliente: " + idUsuario);
        this.idUsuario = idUsuario;
        List<Sucursal>sucursales = sucursalService.obtenerTodasLasSucursales(idUsuario);
        logger.debug("sucursales: " + sucursales);
        model.addAttribute("sucursales", sucursales);
        return "listaSucursales";
    }

    @GetMapping("/crear")
    public String crearSucursalNueva(Model model) {
        logger.debug("******** Entrando al metodo crearSucursalNueva ********");
        Usuario cliente = usuarioService.buscarPorId(this.idUsuario);
        logger.debug("Cliente: " + cliente);
        model.addAttribute("cliente", cliente);
        // Creando sucursal temporal, para su llenado.
        Sucursal sucursalTemp = new Sucursal();
        sucursalTemp.setNombre("TEMPORAL");
        sucursalTemp.setCalle("TEMPORAL");
        sucursalTemp.setNumero("TEMPORAL");
        sucursalTemp.setColonia("TEMPORAL");
        sucursalTemp.setCodigoPostal("TEMPORAL");
        sucursalTemp.setAlcaldia("TEMPORAL");
        sucursalTemp.setTelefono("TEMPORAL");
        sucursalTemp.setUsuario(cliente);
        sucursalService.guardarSucursal(sucursalTemp);
        return "formularioSucursalNueva";
    }

    @PostMapping("/actualizarSucursal")
    public String actualizarSucursal(Sucursal sucursal) {
        logger.debug("******** Entrando al metodo actualizarSucursal ********");
        logger.debug("Sucursal: " + sucursal);
        // Buscar datos del cliente por id.
        Usuario cliente = usuarioService.buscarPorId(idUsuario);
        logger.debug("Cliente: " + cliente);
        // Vincular cliente con la sucursal a actualizar.
        sucursal.setUsuario(cliente);
        sucursalService.guardarSucursal(sucursal);
        return "redirect:/sucursales/tablaSucursales/" + idUsuario;
    }

    @GetMapping("/buscarSucursalPorNombre")
    public String buscarSucursalPorNombre(@Param("palabraClave") String palabraClave, Model model) {
        logger.info("Entrando al método buscarSucursalPorNombre");
        List<Sucursal> sucursales = sucursalService.obtenerSucursalesPorNombre(palabraClave);
        logger.info("Sucursales: " + sucursales);
        model.addAttribute("palabraClave", palabraClave);
        model.addAttribute("sucursales", sucursales);
        return "listaSucursales";
    }

}