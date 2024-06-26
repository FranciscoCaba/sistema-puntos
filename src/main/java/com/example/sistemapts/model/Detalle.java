package com.example.sistemapts.model;
import jakarta.persistence.*;

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
    @JoinColumn(name = "id_cabecera", referencedColumnName = "id_uso_cabecera")
    private Cabecera cabecera;

    @ManyToOne
    @JoinColumn(name = "id_bolsa_puntos_utilizada", referencedColumnName = "id_bolsa_pts")
    private BolsaDePuntos bolsaDePuntos;

    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    public Detalle() {};

    public Detalle(Cabecera cabecera, BolsaDePuntos bolsaDePuntos, Integer puntajeUtilizado) {
        this.cabecera = cabecera;
        this.bolsaDePuntos = bolsaDePuntos;
        this.puntajeUtilizado = puntajeUtilizado;
    }

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


