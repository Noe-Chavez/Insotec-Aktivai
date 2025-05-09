package com.uacm.aktivai.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotEmpty
    private String marca;

    @NotNull
    @NotEmpty
    private String modelo;

    private Number numeroCamara;

    private String serieDvr;

    private Date fechaUltimoMantenimiento;

    private String notas;

    private String codigoVerificacion;

    private String modeloPanel;

    private Number numeroTeclados;

    private String modeloTeclado;

    private String codigoInstalador;

    private String codigoMaestro;

    private Number numeroZonasCableadas;

    private String tipoSensorCableado;

    private String tipoSensorInalambrico;

    private String numeroSerieSensorCableado;

    private String numeroSerieSensorInalambrico;

    private String zonaCuidarCableado;

    private String zonaCuidarInalambrico;

    private Number numeroZonaCableadas;

    private Number numeroZonaInalambricas;

    private Number numeroLlaveros;

    private String modeloLlavero;

    private Number numeroSirenas;

    private String modeloSirenas;

    private Number numeroSensoresTotal;

    private String modeloComunicador;

    private String serieComunicador;

    private String macComunicador;

    private String modeloFrenteCalle;

    private String serieFrenteCalle;

    private Number numeroMonitores;

    private String modeloMonitor;

    private String numeroDepartamento;

    private String numeroSerieMonitor;

    private String ipFrenteCalle;

    private String ipMonitorFija;

    private String serieP2p;

    private Number numeroPanel;

    private String modeloPanelCentral;

    private String numeroSeriePanel;

    private String ipPanelCentral;

    private String numeroPanelStandalone;

    private String numeroSeriePanelStandalone;

    private String ipStandalone;

    private Number numeroPuertosPanel;

    private String modeloChapaPuerta;

    private String modeloChapaArea;

    private String modeloChapaModoAcceso;

    private String modeloBotonLiberador;

    private String modeloControlador;

    private String modeloPuntoAcceso;

    private String versionFireware;

    private Number numeroPuntosAccesoInstalador;

    private String mac;

    private String numeroSerie;

    private String Ip;

    private String esclavo;

    private String maestro;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipo", cascade = CascadeType.ALL)
    private List<Credencial> credenciales;

    public Equipo() {}

    @Override
    public String toString() {
        return "Equipo{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", numeroCamara=" + numeroCamara +
                ", serieDvr='" + serieDvr + '\'' +
                ", fechaUltimoMantenimiento=" + fechaUltimoMantenimiento +
                ", notas='" + notas + '\'' +
                ", codigoVerificacion='" + codigoVerificacion + '\'' +
                ", modeloPanel='" + modeloPanel + '\'' +
                ", numeroTeclados=" + numeroTeclados +
                ", modeloTeclado='" + modeloTeclado + '\'' +
                ", codigoInstalador='" + codigoInstalador + '\'' +
                ", codigoMaestro='" + codigoMaestro + '\'' +
                ", numeroZonasCableadas=" + numeroZonasCableadas +
                ", tipoSensorCableado='" + tipoSensorCableado + '\'' +
                ", tipoSensorInalambrico='" + tipoSensorInalambrico + '\'' +
                ", numeroSerieSensorCableado='" + numeroSerieSensorCableado + '\'' +
                ", numeroSerieSensorInalambrico='" + numeroSerieSensorInalambrico + '\'' +
                ", zonaCuidarCableado='" + zonaCuidarCableado + '\'' +
                ", zonaCuidarInalambrico='" + zonaCuidarInalambrico + '\'' +
                ", numeroZonaCableadas=" + numeroZonaCableadas +
                ", numeroZonaInalambricas=" + numeroZonaInalambricas +
                ", numeroLlaveros=" + numeroLlaveros +
                ", modeloLlavero='" + modeloLlavero + '\'' +
                ", numeroSirenas=" + numeroSirenas +
                ", modeloSirenas='" + modeloSirenas + '\'' +
                ", numeroSensoresTotal=" + numeroSensoresTotal +
                ", modeloComunicador='" + modeloComunicador + '\'' +
                ", serieComunicador='" + serieComunicador + '\'' +
                ", macComunicador='" + macComunicador + '\'' +
                ", modeloFrenteCalle='" + modeloFrenteCalle + '\'' +
                ", serieFrenteCalle='" + serieFrenteCalle + '\'' +
                ", numeroMonitores=" + numeroMonitores +
                ", modeloMonitor='" + modeloMonitor + '\'' +
                ", numeroDepartamento='" + numeroDepartamento + '\'' +
                ", numeroSerieMonitor='" + numeroSerieMonitor + '\'' +
                ", ipFrenteCalle='" + ipFrenteCalle + '\'' +
                ", ipMonitorFija='" + ipMonitorFija + '\'' +
                ", serieP2p='" + serieP2p + '\'' +
                ", numeroPanel=" + numeroPanel +
                ", modeloPanelCentral='" + modeloPanelCentral + '\'' +
                ", numeroSeriePanel='" + numeroSeriePanel + '\'' +
                ", ipPanelCentral='" + ipPanelCentral + '\'' +
                ", numeroPanelStandalone='" + numeroPanelStandalone + '\'' +
                ", numeroSeriePanelStandalone='" + numeroSeriePanelStandalone + '\'' +
                ", ipStandalone='" + ipStandalone + '\'' +
                ", numeroPuertosPanel=" + numeroPuertosPanel +
                ", modeloChapaPuerta='" + modeloChapaPuerta + '\'' +
                ", modeloChapaArea='" + modeloChapaArea + '\'' +
                ", modeloChapaModoAcceso='" + modeloChapaModoAcceso + '\'' +
                ", modeloBotonLiberador='" + modeloBotonLiberador + '\'' +
                ", modeloControlador='" + modeloControlador + '\'' +
                ", modeloPuntoAcceso='" + modeloPuntoAcceso + '\'' +
                ", versionFireware='" + versionFireware + '\'' +
                ", numeroPuntosAccesoInstalador=" + numeroPuntosAccesoInstalador +
                ", mac='" + mac + '\'' +
                ", numeroSerie='" + numeroSerie + '\'' +
                ", Ip='" + Ip + '\'' +
                ", esclavo='" + esclavo + '\'' +
                ", maestro='" + maestro + '\'' +
                '}';
    }

}
