package com.example.sistemapts.model;

import jakarta.persistence.*;

@Entity
@Table(name = "conceptos_uso_puntos")
public class ConceptoUsoDePuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_concept_pts")
    @GeneratedValue(generator = "conceptSec")
    @SequenceGenerator(name = "conceptSec", sequenceName = "conceptos_uso_puntos_sec", allocationSize = 0)
    private Integer idConceptoUsoDePuntos;

    @Basic(optional = false)
    @Column(name = "descripcion_concepto", length = 100)
    private String descripcion;

    @Basic(optional = false)
    @Column(name = "puntos_requeridos")
    private Integer puntosReq;

    public ConceptoUsoDePuntos(){};

    public Integer getIdConceptoUsoDePuntos() {
        return idConceptoUsoDePuntos;
    }

    public void setIdConceptoUsoDePuntos(Integer idConceptoUsoDePuntos) {
        this.idConceptoUsoDePuntos = idConceptoUsoDePuntos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getPuntosReq() {
        return puntosReq;
    }

    public void setPuntosReq(Integer puntosReq) {
        this.puntosReq = puntosReq;
    }
}
