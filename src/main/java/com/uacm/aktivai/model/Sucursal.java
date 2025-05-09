package com.uacm.aktivai.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.util.List;

@Entity
@Data
public class Sucursal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idSucursal;

	@NotNull
	@NotEmpty
	private String nombre;

	@NotNull
	@NotEmpty
	private String calle;
	
	private String numero;
	
	@NotNull
	@NotEmpty
	private String colonia;
	
	@NotNull
	@NotEmpty
	private String codigoPostal;
	
	@NotNull
	@NotEmpty
	private String alcaldia;
	
	private String contacto;
	
	@NotNull
	@NotEmpty
	private String telefono;
	
	private String correo;

	private String estado;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sucursal", cascade = CascadeType.ALL)
	private List<Servicio> servicios;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_usuario")
	private Usuario usuario;

	public Sucursal() {

	}

	@Override
	public String toString() {
		return "Sucursal{" +
				"idSucursal=" + idSucursal +
				", nombre='" + nombre + '\'' +
				", calle='" + calle + '\'' +
				", numero='" + numero + '\'' +
				", colonia='" + colonia + '\'' +
				", codigoPostal='" + codigoPostal + '\'' +
				", alcaldia='" + alcaldia + '\'' +
				", contacto='" + contacto + '\'' +
				", telefono='" + telefono + '\'' +
				", correo='" + correo + '\'' +
				", estado='" + estado + '\'' +
				'}';
	}

}