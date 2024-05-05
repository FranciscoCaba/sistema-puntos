package com.example.sistemapts.model;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "uso_puntos_cabecera")
public class Cabecera {
    @Id
    @Basic(optional = false)
    @Column(name = "id_uso_puntos_cabecera")
    @GeneratedValue(generator = "usoPuntosSec")
    @SequenceGenerator(name = "usoPuntosSec", sequenceName = "uso_puntos_cabecera_sec", allocationSize = 0)
    private Integer idCabecera;

    @ManyToOne //t
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_concept_pts", referencedColumnName = "id_concept_pts")
    private ConceptoUsoDePuntos conceptoUsoDePuntos;

    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    @Column(name = "fecha")
    @Basic(optional = true)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    public Cabecera() {};

    public Integer getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(Integer idCabecera) {
        this.idCabecera = idCabecera;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ConceptoUsoDePuntos getConceptoUsoDePuntos() {
        return conceptoUsoDePuntos;
    }

    public void setConceptoUsoDePuntos(ConceptoUsoDePuntos conceptoUsoDePuntos) {
        this.conceptoUsoDePuntos = conceptoUsoDePuntos;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}
