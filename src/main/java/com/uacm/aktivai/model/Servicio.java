package com.uacm.aktivai.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Entity
@Data
public class Servicio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long folio;

	@NotNull
	@NotEmpty
	private String tipoServicio;

	@NotNull
	@NotEmpty
	private String categoria;

	private Date fechaContratacion;

	private Double costo;

	@NotNull
	@NotEmpty
	private String descripcion;

	//@NotNull
	//@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sucursal_id")
	private Sucursal sucursal;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "servicio", cascade = CascadeType.ALL)
	private List<Soporte> soportes;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "poliza_id")
	private Poliza poliza;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "financiamiento_id")
	private Financiamiento financiamiento;

	/*@NotEmpty
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;*/

	//@NotNull
	//@NotEmpty
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "servicio", cascade = CascadeType.ALL)
	private List<Credencial> credenciales;

	public Servicio() { }

	@Override
	public String toString() {
		return "Servicio{" +
				"folio=" + folio +
				", tipoServicio='" + tipoServicio + '\'' +
				", categoria='" + categoria + '\'' +
				", fechaContratacion=" + fechaContratacion +
				", costo=" + costo +
				", descripcion='" + descripcion + '\'' +
				'}';
	}
	
}