package com.uacm.aktivai.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Data
public class Soporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolicitud;

    @NotNull
    @NotEmpty
    private Date fechaSolicitud;

    @NotNull
    @NotEmpty
    private Boolean estatus;

    private String fechaResolucion;

    @NotNull
    @NotEmpty
    private String descripcionSolicitud;

    private String descripcionResolucion;

    //@NotNull
    //@NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "soporte_folio")
    private Servicio servicio;

    public Soporte() {}

    @Override
    public String toString() {
        return "Soporte{" +
                "idSolicitud=" + idSolicitud +
                ", fechaSolicitud=" + fechaSolicitud +
                ", estatus=" + estatus +
                ", fechaResolucion='" + fechaResolucion + '\'' +
                ", descripcionSolicitud='" + descripcionSolicitud + '\'' +
                ", descripcionResolucion='" + descripcionResolucion + '\'' +
                '}';
    }

}
