package com.uacm.aktivai.service.impl;

import com.uacm.aktivai.model.Servicio;
import com.uacm.aktivai.model.repository.ServicioRepository;
import com.uacm.aktivai.service.ServicioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioServiceImpl implements ServicioService {

    @Autowired
    private ServicioRepository servicioRepository;

    @Override
    public Page<Servicio> obtenerServicosContratados(Long idUsuario, Pageable pageable) {
        return servicioRepository.obtenerServicosContratados(idUsuario, pageable);
    }

    @Override
    public List<Servicio> obtenerServiciosPorSucursal(Long folioSucursal) {
        return servicioRepository.obtenerServiciosPorSucursal(folioSucursal);
    }

    @Override
    public Optional<Servicio> obtenerServicioPorFolio(Long folio) {
        return servicioRepository.findById(folio);
    }

    @Override
	public Servicio guardarServicio(Servicio servicio) {
		
		return servicioRepository.save(servicio);
	}

}
