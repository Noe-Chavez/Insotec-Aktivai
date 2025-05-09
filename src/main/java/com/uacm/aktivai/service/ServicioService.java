package com.uacm.aktivai.service;

import com.uacm.aktivai.model.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ServicioService {

    public Page<Servicio> obtenerServicosContratados(Long idUsuario, Pageable pageable);

    public List<Servicio> obtenerServiciosPorSucursal(Long folioSucursal);
	
    public Optional<Servicio> obtenerServicioPorFolio(Long folio);
    
    public Servicio guardarServicio(Servicio servicio);

}
