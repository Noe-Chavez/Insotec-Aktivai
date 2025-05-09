package com.uacm.aktivai.model.repository;

import com.uacm.aktivai.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {
    @Query(value = "SELECT s.*\n" +
            "FROM usuario u\n" +
            "INNER JOIN sucursal s\n" +
            "ON u.id = s.fk_id_usuario\n" +
            "WHERE u.id = ?1 AND s.alcaldia NOT LIKE '%TEMPORAL%'", nativeQuery = true)
    public List<Sucursal> obtenerTodasLasSucursales(Long idCliente);

    @Query(value = "SELECT *\n" +
            "FROM sucursal\n" +
            "WHERE nombre LIKE '%TEMPORAL%'", nativeQuery = true)
    public List<Sucursal> obtenerSucursalTemporal();

    @Query(value = "SELECT * \n" +
            "FROM sucursal \n" +
            "WHERE nombre LIKE %?1%" , nativeQuery = true)
    public List<Sucursal> obtenerSucursalesPorNombre(String palabraClave);

}