package com.example.sistemapts.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "bolsa_puntos")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class BolsaDePuntos {
    @Id
    @Basic(optional = false)
    @Column(name = "id_bolsa_pts")
    @GeneratedValue(generator = "bolsaPuntosSec")
    @SequenceGenerator(name = "bolsaPuntosSec", sequenceName = "bolsa_puntos_sec", allocationSize = 0)
    private Integer idBolsaDePuntos;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente")
    //@JsonBackReference
    private Cliente cliente;

    @Basic(optional = false)
    @Column(name = "puntaje_asignado")
    private Integer puntajeAsignado;

    @Basic(optional = false)
    @Column(name = "puntaje_utilizado")
    private Integer puntajeUtilizado;

    @Basic(optional = false)
    @Column(name = "saldo_puntos")
    private Integer saldo;

    @Basic(optional = false)
    @Column(name = "monto_operacion")
    private Integer montoDeLaOperacion;

    @Column(name = "fecha_asignacion_puntaje")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacion;

    @Column(name = "fecha_caducidad_puntaje")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;

    public BolsaDePuntos() {};

    public BolsaDePuntos(Cliente cliente, Integer puntajeAsignado, Integer puntajeUtilizado, Integer saldo, Integer montoDeLaOperacion, Date fechaAsignacion, Date fechaCaducidad) {
        this.cliente = cliente;
        this.puntajeAsignado = puntajeAsignado;
        this.puntajeUtilizado = puntajeUtilizado;
        this.saldo = saldo;
        this.montoDeLaOperacion = montoDeLaOperacion;
        this.fechaAsignacion = fechaAsignacion;
        this.fechaCaducidad = fechaCaducidad;
    }

    public Integer getIdBolsaDePuntos() {
        return idBolsaDePuntos;
    }

    public void setIdBolsaDePuntos(Integer idBolsaDePuntos) {
        this.idBolsaDePuntos = idBolsaDePuntos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getPuntajeAsignado() {
        return puntajeAsignado;
    }

    public void setPuntajeAsignado(Integer puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getMontoDeLaOperacion() {
        return montoDeLaOperacion;
    }

    public void setMontoDeLaOperacion(Integer montoDeLaOperacion) {
        this.montoDeLaOperacion = montoDeLaOperacion;
    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
}
