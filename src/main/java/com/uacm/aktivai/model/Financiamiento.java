package com.uacm.aktivai.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Financiamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFinanciamiento;

    @NotNull
    @NotEmpty
    private Double porcentajeRetraso;

    @NotNull
    @NotEmpty
    private Date fechaPago;

    private BigDecimal mensualidades;

    private BigDecimal moentoMensual;

    @NotNull
    @NotEmpty
    @OneToOne(mappedBy = "financiamiento")
    private Servicio servicio;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "financiamiento", cascade = CascadeType.ALL)
    private List<Pago> pagos;

    public Financiamiento() { }

    @Override
    public String toString() {
        return "Financiamiento{" +
                "idFinanciamiento=" + idFinanciamiento +
                ", porcentajeRetraso=" + porcentajeRetraso +
                ", fechaPago=" + fechaPago +
                ", mensualidades=" + mensualidades +
                ", moentoMensual=" + moentoMensual +
                ", servicio=" + servicio +
                ", pagos=" + pagos +
                '}';
    }

}
