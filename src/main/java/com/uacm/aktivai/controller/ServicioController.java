package com.uacm.aktivai.controller;

import com.uacm.aktivai.model.*;
import com.uacm.aktivai.service.ServicioService;
import com.uacm.aktivai.service.SucursalService;
import com.uacm.aktivai.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/servicios")
public class ServicioController {

    @Autowired
    ServicioService servicioService;

    @Autowired
    public SucursalService sucursalService;

    @Autowired
    UsuarioService usuarioService;

    Logger logger = LoggerFactory.getLogger(ServicioController.class);

    private Long idSucursal;

    @GetMapping("/tablaServicios/{idSucursal}")
    public String listaServicios(@PathVariable("idSucursal") Long idSucursal, Model model) {
        logger.debug("******** Entrando al metodo listaServicios ********");
        this.idSucursal = idSucursal;
        logger.debug("id de la sucursal: " + this.idSucursal);
        List<Servicio> servicios = servicioService.obtenerServiciosPorSucursal(this.idSucursal);
        model.addAttribute("servicios", servicios);
        return "listaServicios";
    }

    @GetMapping("/detalle/{folio}")
    public String mostrarDetalleServicio(@PathVariable("folio") Long folio, Model model) {
        logger.debug("******** Entrando al metodo mostrarDetalleServicio ********");
        logger.debug("******** folio =  " + folio);
        Optional<Servicio> servicio = servicioService.obtenerServicioPorFolio(folio);
        Servicio servicioSonoptional = new Servicio();
        if (servicio.isPresent()) {
            servicioSonoptional = servicio.get();
            model.addAttribute("servicio", servicioSonoptional);
        }
        logger.debug("Detalles del Servicio " + servicio);
        return "detallesServicio";
    }

    @GetMapping("/crear")
    public String mostraFormularioServicioNuevo(Model model) {
        logger.debug("******** Entrando al metodo mostraFormularioServicioNuevo ********");

        // Servicios
        List<String> servicios = new ArrayList<>(4);
        servicios.add("Sistema de CCTV");
        servicios.add("Alarma");
        servicios.add("Control de Accesos");
        servicios.add("Botón de pánico");

        // Categorias
        List<String> categorias = new ArrayList<>(3);
        categorias.add("Financiado");
        categorias.add("De Contado");
        categorias.add("Póliza de Mantenimiento");

        model.addAttribute("servicios", servicios);
        model.addAttribute("categorias", categorias);

        return "formularioServicioNuevo";
    }

    @PostMapping("/guardar/nuevo")
    public String guardarServicioNuevo(Servicio servicio, Model model) {
        logger.debug("******** Entrando al metodo guardarServicio ********");
        logger.debug("idSucursal = " + this.idSucursal);

        // Obtener datos de la sucursal.
        Sucursal sucursal = sucursalService.buscarPorId(this.idSucursal);
        logger.debug("sucursal:  = " + sucursal);
        // Obtner fecha de instalación
        Date fechaContratacion = new Date();
        servicio.setFechaContratacion(fechaContratacion);
        logger.debug("fecha tentativa de instalación:  = " + fechaContratacion);
        servicio.setSucursal(sucursal);
        logger.debug("Servicio: " + servicio);
        Servicio servicioGuardaro = servicioService.guardarServicio(servicio);
        logger.debug("Servicio guardado: " + servicioGuardaro);

        //***********************************************************************************************
        // ********************************* OBTENER EL USUARIO LOGEADO *********************************
        //***********************************************************************************************

        // obetner usuario logeado.
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = null;
        if (principal instanceof UserDetails) {
            userDetails = (UserDetails) principal;
        }

        logger.debug("userDetails : " + userDetails);

        String userName = userDetails.getUsername();
        logger.debug("userName : " + userName);

        //***********************************************************************************************

        return "redirect:/servicios/tablaServicios/" + idSucursal;

    }

}