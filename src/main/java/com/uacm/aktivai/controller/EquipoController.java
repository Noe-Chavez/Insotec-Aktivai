package com.uacm.aktivai.controller;

import com.uacm.aktivai.model.Equipo;
import com.uacm.aktivai.model.Servicio;
import com.uacm.aktivai.service.EquipoService;

import com.uacm.aktivai.service.ServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/equipos")
public class EquipoController {
	
	@Autowired
    private EquipoService equipoService;

    @Autowired
    private ServicioService servicioService;

    Logger logger = LoggerFactory.getLogger(EquipoController.class);

    private Long idFolioServicio;

    @GetMapping("/tablaEquipos/{idFolioServicio}")
    public String listaSucursales(@PathVariable("idFolioServicio") Long idFolioServicio, Model model) {
        logger.debug("******** Entrando al metodo tablaEquipos ********");
        logger.debug("idFolioServicio: " + idFolioServicio);

        this.idFolioServicio = idFolioServicio;

        List<Equipo> lista_equipos = equipoService.obtenerEquiposDeServicioConFolio(idFolioServicio);
        logger.debug("Lista de equipos:  " + lista_equipos);

        model.addAttribute("lista_equipos", lista_equipos);

        return "listaEquipos";
    }

    @GetMapping("/crear")
    public String formularioEquipo(Model model) {
        logger.debug("******** Entrando al metodo formularioEquipo ********");
        // Obtener el serivico a sociado.
        Optional<Servicio> servicioOptional = servicioService.obtenerServicioPorFolio(this.idFolioServicio);
        logger.debug("servicioOptional: " + servicioOptional);
        // Recuperar la categoria del sevicio que se va a vincular este equipo.
        String categoria = servicioOptional.get().getCategoria();
        logger.debug("Categoria: " + categoria);
        model.addAttribute("categoria", categoria);
        return "formularioEquipos";
    }

}