package com.uacm.aktivai.controller;

import com.uacm.aktivai.model.Credencial;
import com.uacm.aktivai.model.Equipo;
import com.uacm.aktivai.model.Servicio;
import com.uacm.aktivai.service.CredencialService;
import com.uacm.aktivai.service.EquipoService;
import com.uacm.aktivai.service.ServicioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(value = "/credenciales")
public class CredencialesController {

    Logger logger = LoggerFactory.getLogger(CredencialesController.class);

    private Long folioServicio;
    private Long idEquipo;

    @Autowired
    CredencialService credencialService;
    @Autowired
    EquipoService equipoService;
    @Autowired
    ServicioService servicioService;

    @GetMapping("/crear/{folioServicio}/{idEquipo}")
    public String crearCredencial(Model model, @PathVariable(value = "folioServicio") Long folioServicio, @PathVariable("idEquipo") Long idEquipo) {

        logger.debug("******** Entrando al metodo crearCredencial ********");
        this.folioServicio = folioServicio;
        this.idEquipo = idEquipo;
        logger.debug("folioServicio = " + folioServicio);
        logger.debug("idEquipo = " + idEquipo);

        return "formularioCredencial";
    }

    @PostMapping("/guardar")
    public String guardarCredencial(Credencial credencial) {

        logger.debug("******** Entrando al metodo guardarCredencial ********");
        logger.debug("folioServicio = " + folioServicio + " | " + "idEquipo = " + idEquipo);

        // Obtener objeto Servicio.
        Optional<Servicio> servicioOptional = servicioService.obtenerServicioPorFolio(folioServicio);
        logger.debug("Servicio: " + servicioOptional.get());
        credencial.setServicio(servicioOptional.get());
        // Obtener objeto Equipo.
        Optional<Equipo> equipoOptional = equipoService.obtenerEquipoPorId(idEquipo);
        logger.debug("Equipo: " + equipoOptional.get());
        credencial.setEquipo(equipoOptional.get());
        logger.debug("Credencial antes de guardar: " + credencial);
        // Guardar credencial.
        Credencial credencialGuardada = credencialService.guardarCredencial(credencial);
        logger.debug("Credencial guardada: " + credencialGuardada);

        return "redirect:/";
    }

}
