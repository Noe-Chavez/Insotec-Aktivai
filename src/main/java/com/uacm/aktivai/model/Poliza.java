package com.uacm.aktivai.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Poliza {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPoliza;

    private String descrpcion;

    @NotNull
    @NotEmpty
    private Date fechaInicio;

    @NotNull
    @NotEmpty
    private Date fechaFin;

    private BigDecimal costo;

    @NotNull
    @NotEmpty
    @OneToOne(mappedBy = "poliza")
    private Servicio servicio;

    @Override
    public String toString() {
        return "Poliza{" +
                "idPoliza=" + idPoliza +
                ", descrpcion='" + descrpcion + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", costo=" + costo +
                '}';
    }

}
