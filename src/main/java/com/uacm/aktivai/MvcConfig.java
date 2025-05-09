package com.uacm.aktivai;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
	
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/home.html");
		registry.addViewController("/index.html");
		registry.addViewController("listaUsuarios").setViewName("listaUsuarios");
		registry.addViewController("detallesUsuario").setViewName("detallesUsuario");
		registry.addViewController("editarUsuario").setViewName("editarUsuario");
		registry.addViewController("formularioUsuarios").setViewName("formularioUsuarios");
		registry.addViewController("menuNuevoServicio").setViewName("menuNuevoServicio");
		registry.addViewController("formularioSucursalNueva").setViewName("formularioSucursalNueva");
		registry.addViewController("listaServicios").setViewName("listaServicios");
		registry.addViewController("contraseniasServicio").setViewName("contraseniasServicio");
		registry.addViewController("formularioEquipos").setViewName("formularioEquipos");
		registry.addViewController("formularioServicioNuevo").setViewName("formularioServicioNuevo");

		

	}

}
