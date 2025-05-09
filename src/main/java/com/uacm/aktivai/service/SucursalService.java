package com.uacm.aktivai.service;

import com.uacm.aktivai.model.Servicio;
import com.uacm.aktivai.model.Sucursal;

import java.util.List;
import java.util.Optional;

public interface SucursalService {

	public Sucursal buscarPorId(Long id);

    public Sucursal guardarSucursal(Sucursal sucursal);

    public List<Sucursal> obtenerTodasLasSucursales(Long idCliente);

    public Optional<Sucursal> obtenerSucursalPorId(Long idSucursal);

    public List<Sucursal> obtenerSucursalTemporal();

    public void eliminarSucursalesTemporales(List<Sucursal> sucursalesTemporales);

    public List<Sucursal> obtenerSucursalesPorNombre(String palabraClave);

}
