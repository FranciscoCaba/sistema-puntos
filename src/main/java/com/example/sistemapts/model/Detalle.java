package com.example.sistemapts.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "uso_puntos_detalle")
public class Detalle {
    @Id
    @Basic(optional = false)
    @Column(name = "id_pts_detalle")
    @GeneratedValue(generator = "usoDetalleSec")
    @SequenceGenerator(name = "usoDetalleSec", sequenceName = "uso_puntos_detalle_sec", allocationSize = 0)
    private Integer idDetalle;

    @ManyToOne
    @JoinColumn(name = "id_uso_puntos_cabecera", referencedColumnName = "id_uso_puntos_cabecera")
    private Cabecera cabecera;

    @ManyToOne
    @JoinColumn(name = "id_bolsa_pts", referencedColumnName = "id_bolsa_pts")
    private BolsaDePuntos bolsaDePuntos;

    @Basic(optional = false)
    @JoinColumn(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    public Detalle() {};

    public Integer getIdDetalle() {
        return idDetalle;
    }

    public void setIdDetalle(Integer idDetalle) {
        this.idDetalle = idDetalle;
    }

    public Cabecera getCabecera() {
        return cabecera;
    }

    public void setCabecera(Cabecera cabecera) {
        this.cabecera = cabecera;
    }

    public BolsaDePuntos getBolsaDePuntos() {
        return bolsaDePuntos;
    }

    public void setBolsaDePuntos(BolsaDePuntos bolsaDePuntos) {
        this.bolsaDePuntos = bolsaDePuntos;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }
}


