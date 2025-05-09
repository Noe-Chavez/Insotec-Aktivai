package com.uacm.aktivai.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@NotEmpty
	private String nombre;

	private String paterno;

	private String materno;

	private String razonSocial;

	private String rfc;

	private String contacto;

	private String telPersonal;

	private String telOficina;
	
	private String avatar;
	
	private Date fechaCreacion;

	@NotNull
	@NotEmpty
	private String mail;

	@NotNull
	@NotEmpty
	private String password;
	
    @NotNull
    private boolean habilitado;

	private String comentarios;

	// Este atributo hace referencia si es persona moral (2) o física (1).
	private Integer tipoDePersona;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Rol> roles = new ArrayList<Rol>();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
	List<Sucursal> sucursales;

	/*@NotNull
	@NotEmpty
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario", cascade = CascadeType.ALL)
	List<Servicio> servicios;*/

    public Usuario() {
    	
    }

	@Override
	public String toString() {
		return "Usuario{" +
				"id=" + id +
				", nombre='" + nombre + '\'' +
				", paterno='" + paterno + '\'' +
				", materno='" + materno + '\'' +
				", razonSocial='" + razonSocial + '\'' +
				", rfc='" + rfc + '\'' +
				", contacto='" + contacto + '\'' +
				", telPersonal='" + telPersonal + '\'' +
				", telOficina='" + telOficina + '\'' +
				", avatar='" + avatar + '\'' +
				", fechaCreacion=" + fechaCreacion +
				", mail='" + mail + '\'' +
				", password='" + password + '\'' +
				", habilitado=" + habilitado +
				", comentarios='" + comentarios + '\'' +
				", tipoDePersona=" + tipoDePersona +
				'}';
	}

}