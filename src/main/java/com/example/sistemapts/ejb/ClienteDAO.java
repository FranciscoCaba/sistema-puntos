package com.example.sistemapts.ejb;

import com.example.sistemapts.model.*;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName = "sistema-ptsDS")
    private EntityManager entityManager;

    protected Session getSession(){
        return (SessionImpl)entityManager.getDelegate();
    }

    private boolean mailValido(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void agregar(Cliente c) {
        if (!mailValido(c.getEmail())) {
            throw new IllegalArgumentException("El formato del correo electrónico no es válido");
        }
        this.entityManager.persist(c);
    }

    public List<Cliente> obtenerPersonas() {
        return this.entityManager.createQuery("select c from Cliente c").getResultList();
    }

    public void eliminar (Integer idCliente) {
        Cliente c=this.entityManager.find(Cliente.class,idCliente);
        this.entityManager.remove(c);
    }

    public Cliente modificar (Cliente c) {
        return this.entityManager.merge(c);
    }

    public List<Cliente> obtenerClientesPorNacionalidad(String nacionalidad) {
        return entityManager.createQuery("SELECT c FROM Cliente c WHERE c.nacionalidad = :nacionalidad", Cliente.class)
                .setParameter("nacionalidad", nacionalidad)
                .getResultList();
    }

    public List<Cliente> obtenerClientesPorFechaNacimiento(LocalDate fechaNacimiento) {
        return entityManager.createQuery("SELECT c FROM Cliente c WHERE c.fechaNacimiento = :fechaNacimiento", Cliente.class)
                .setParameter("fechaNacimiento", fechaNacimiento)
                .getResultList();
    }
}
