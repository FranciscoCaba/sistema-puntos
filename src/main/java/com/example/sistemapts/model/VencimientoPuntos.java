package com.example.sistemapts.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vencimientos_puntos")
public class VencimientoPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_venc_pts")
    @GeneratedValue(generator = "vencimientosSec")
    @SequenceGenerator(name = "vencimientosSec", sequenceName = "vencimientos_puntos_sec", allocationSize = 0)
    private Integer idVencimientoPuntos;

    @Column(name = "fecha_inicio_validez")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;

    @Column(name = "fecha_fin_validez")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFin;

    @Basic(optional = false)
    @Column(name = "dias_validez_puntos")
    private Integer duracion;

    public VencimientoPuntos() {};

    public Integer getIdVencimientoPuntos() {
        return idVencimientoPuntos;
    }

    public void setIdVencimientoPuntos(Integer idVencimientoPuntos) {
        this.idVencimientoPuntos = idVencimientoPuntos;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }
}