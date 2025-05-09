package com.uacm.aktivai.service;

import com.uacm.aktivai.model.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {

    public List<Equipo> obtenerEquiposDeServicioConFolio(Long folio);

    public Equipo guardarEquipo(Equipo equipo);

    public Optional<Equipo> obtenerEquipoPorId(Long idEquipo);

}
