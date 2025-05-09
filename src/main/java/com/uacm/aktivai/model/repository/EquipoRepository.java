package com.uacm.aktivai.model.repository;

import com.uacm.aktivai.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    @Query(value = "SELECT *\n" +
            "FROM equipo e\n" +
            "INNER JOIN credencial c ON c.equipo_id = e.id\n" +
            "INNER JOIN servicio s ON c.servicio_folio = s.folio\n" +
            "WHERE s.folio = ?1", nativeQuery = true)
    public List<Equipo> obtenerEquiposDeServicioConFolio(Long folio);

}
