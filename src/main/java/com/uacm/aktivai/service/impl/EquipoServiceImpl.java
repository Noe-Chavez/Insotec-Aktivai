package com.uacm.aktivai.service.impl;

import com.uacm.aktivai.model.Equipo;
import com.uacm.aktivai.model.repository.EquipoRepository;
import com.uacm.aktivai.service.EquipoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    EquipoRepository equipoRepository;

    @Override
    public List<Equipo> obtenerEquiposDeServicioConFolio(Long folio) {
        return equipoRepository.obtenerEquiposDeServicioConFolio(folio);
    }

    @Override
    public Equipo guardarEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    public Optional<Equipo> obtenerEquipoPorId(Long idEquipo) {
        return equipoRepository.findById(idEquipo);
    }

}
