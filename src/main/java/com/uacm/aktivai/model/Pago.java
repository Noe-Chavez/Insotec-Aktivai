package com.uacm.aktivai.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long referencia;

    private Date fechaPago;

    private Integer numMensualidad;

    private BigDecimal monto;

    private String evidencia;

    @NotNull
    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financiamiento_id")
    private Financiamiento financiamiento;

    public Pago() { }

    @Override
    public String toString() {
        return "Pago{" +
                "referencia=" + referencia +
                ", fechaPago=" + fechaPago +
                ", numMensualidad=" + numMensualidad +
                ", monto=" + monto +
                ", evidencia='" + evidencia + '\'' +
                '}';
    }

}
