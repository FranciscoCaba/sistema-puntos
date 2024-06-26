package com.example.sistemapts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Basic(optional = false)
    @Column(name = "id_cliente")
    @GeneratedValue(generator = "clienteSec")
    @SequenceGenerator(name = "clienteSec", sequenceName = "cliente_sec", allocationSize = 0)
    private Integer idCliente;

    @Basic(optional = false)
    @Column(name = "nombre", length = 50)
    private String nombre;

    @Basic(optional = false)
    @Column(name = "apellido", length = 50)
    private String apellido;

    @Basic(optional = false)
    @Column(name = "nro_documento", length = 8)
    private String nroDocumento;

    @Basic(optional = false)
    @Column(name = "tipo_documento", length = 20)
    private String tipoDocumento;

    @Basic(optional = false)
    @Column(name = "nacionalidad", length = 20)
    private String nacionalidad;

    @Basic(optional = false)
    @Column(name = "email", length = 30)
    private String email;

    @Basic(optional = false)
    @Column(name = "telefono", length = 14)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @OneToMany(mappedBy = "cliente",fetch = FetchType.EAGER)
    @JsonIgnore
    private List<BolsaDePuntos> listaBolsas;

    public Cliente() {};

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public List<BolsaDePuntos> getListaBolsas() {
        return listaBolsas;
    }

    public void setListaBolsas(List<BolsaDePuntos> listaBolsas) {
        this.listaBolsas = listaBolsas;
    }
}
