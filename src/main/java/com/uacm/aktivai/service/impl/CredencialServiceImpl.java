package com.uacm.aktivai.service.impl;

import com.uacm.aktivai.model.Credencial;
import com.uacm.aktivai.model.repository.CredencialRepository;
import com.uacm.aktivai.service.CredencialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CredencialServiceImpl implements CredencialService {

    @Autowired
    CredencialRepository credencialRepository;

    @Override
    public Credencial guardarCredencial(Credencial credencial) {
        return credencialRepository.save(credencial);
    }

}
