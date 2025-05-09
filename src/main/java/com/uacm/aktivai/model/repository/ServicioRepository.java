package com.uacm.aktivai.model.repository;

import com.uacm.aktivai.model.Servicio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    @Query(value = "SELECT * \n" +
            "FROM sucursal su\n" +
            "INNER JOIN servicio se\n" +
            "ON su.id_sucursal = se.sucursal_id\n" +
            "WHERE su.id_sucursal = ?1", nativeQuery = true)
    public List<Servicio> obtenerServiciosPorSucursal(Long folioSucursal);

    @Query(value = "SELECT * FROM servicio WHERE usuario_id = ?1", nativeQuery = true)
    public Page<Servicio> obtenerServicosContratados(Long idUsuario, Pageable pageable);

}
