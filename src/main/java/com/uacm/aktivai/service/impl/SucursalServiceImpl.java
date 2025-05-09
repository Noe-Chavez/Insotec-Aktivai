package com.uacm.aktivai.service.impl;

import com.uacm.aktivai.model.Sucursal;
import com.uacm.aktivai.model.repository.SucursalRepository;
import com.uacm.aktivai.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SucursalServiceImpl implements SucursalService {

    @Autowired
    SucursalRepository sucursalRepository;

    @Override
    public Sucursal buscarPorId(Long id) {
        Optional<Sucursal> sucursal = sucursalRepository.findById(id);
        if(sucursal.isPresent()) {
            Sucursal sucursalEncontrada = sucursal.get();
            return sucursalEncontrada;
        }else{
            return null;
        }
    }

    @Override
    public Sucursal guardarSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    @Override
    public List<Sucursal> obtenerTodasLasSucursales(Long idCliente) {
        return sucursalRepository.obtenerTodasLasSucursales(idCliente);
    }

    @Override
    public Optional<Sucursal> obtenerSucursalPorId(Long idSucursal) {
        return sucursalRepository.findById(idSucursal);
    }

    public List<Sucursal> obtenerSucursalTemporal() {
        return sucursalRepository.obtenerSucursalTemporal();
    }

    public void eliminarSucursalesTemporales(List<Sucursal> sucursalesTemporales) {
        sucursalRepository.deleteAll(sucursalesTemporales);
    }

    @Override
    public List<Sucursal> obtenerSucursalesPorNombre(String palabraClave) {
        return sucursalRepository.obtenerSucursalesPorNombre(palabraClave);
    }

}
