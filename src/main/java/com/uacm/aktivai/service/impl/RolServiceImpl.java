package com.uacm.aktivai.service.impl;

import com.uacm.aktivai.model.Rol;
import com.uacm.aktivai.model.repository.RolRepository;
import com.uacm.aktivai.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> obtenerTodos() {
        return rolRepository.findAll();
    }

}
