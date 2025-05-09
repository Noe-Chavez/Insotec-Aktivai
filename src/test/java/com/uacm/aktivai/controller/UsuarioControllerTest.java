package com.uacm.aktivai.controller;

import static org.junit.jupiter.api.Assertions.fail;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.uacm.aktivai.AktivaiApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = {AktivaiApplication.class})
@Slf4j
public class UsuarioControllerTest {
	
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;
    
    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }
    
    @WithAnonymousUser
    @Test
    public void debeIniciarSesion() {

        log.debug("Entrando a debeIniciarSesion");

        try {
        	MvcResult resultado = mockMvc.perform(post("/j_spring_security_check")
                    .param("j_username", "aktivai@uacm.edu.mx")
                    .param("j_password", "password")).andReturn();
        	
        	log.debug(resultado.getResponse().getContentAsString());
        	
        } catch (Exception e) {
            fail("Hubo un error al iniciar sesion");
        }

    }

}