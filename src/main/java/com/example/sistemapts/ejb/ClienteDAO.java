package com.example.sistemapts.ejb;

import com.example.sistemapts.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.util.List;

@Stateless
public class ClienteDAO {
    @PersistenceContext(unitName = "sistema-ptsDS")
    private EntityManager entityManager;

    protected Session getSession(){
        return (SessionImpl)entityManager.getDelegate();
    }

    public void agregar(Cliente c) {
        this.entityManager.persist(c);
    }

    public List<Cliente> obtenerPersonas() {
        return this.entityManager.createQuery("select c from Cliente c").getResultList();
    }
}
