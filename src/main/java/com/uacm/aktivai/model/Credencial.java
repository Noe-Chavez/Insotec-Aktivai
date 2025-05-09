package com.uacm.aktivai.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Credencial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String tipo;

    @NotNull
    @NotEmpty
    private String usuario;

    @NotNull
    @NotEmpty
    private String password;

    private String plataforma;
    																																																																																									@NotNull
    //@NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipo_id")
    private Equipo equipo;

    //@NotNull
    //@NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servicio_folio")
    private Servicio servicio;

    public Credencial() { }

    public Credencial(@NotNull @NotEmpty String tipo, @NotNull @NotEmpty String usuario,
			@NotNull @NotEmpty String password) {
		super();
		this.tipo = tipo;
		this.usuario = usuario;
		this.password = password;
	}

	@Override
    public String toString() {
        return "Credencial{" +
                "id=" + id +
                ", tipo='" + tipo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", plataforma='" + plataforma + '\'' +
                '}';
    }

}
