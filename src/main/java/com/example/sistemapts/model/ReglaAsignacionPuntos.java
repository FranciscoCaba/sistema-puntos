package com.example.sistemapts.model;

import jakarta.persistence.*;

@Entity
@Table(name = "reglas_asignacion_puntos")
public class ReglaAsignacionPuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_reglas_pts")
    @GeneratedValue(generator = "reglasAsigSec")
    @SequenceGenerator(name = "reglasAsigSec", sequenceName = "reglas_asignacion_puntos_sec", allocationSize = 0)
    private Integer idReglaAsignacionPuntos;

    @Basic(optional = false)
    @Column(name = "limite_inferior")
    private Integer limiteInferior;

    @Basic(optional = false)
    @Column(name = "limite_superior")
    private Integer limiteSuperior;

    @Basic(optional = false)
    @Column(name = "monto_equivalencia")
    private Integer equivalencia;

    public ReglaAsignacionPuntos() {};

    public Integer getIdReglaAsignacionPuntos() {
        return idReglaAsignacionPuntos;
    }

    public void setIdReglaAsignacionPuntos(Integer idReglaAsignacionPuntos) {
        this.idReglaAsignacionPuntos = idReglaAsignacionPuntos;
    }

    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Integer getEquivalencia() {
        return equivalencia;
    }

    public void setEquivalencia(Integer equivalencia) {
        this.equivalencia = equivalencia;
    }
}
