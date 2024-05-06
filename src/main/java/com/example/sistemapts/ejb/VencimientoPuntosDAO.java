package com.example.sistemapts.ejb;

import com.example.sistemapts.model.VencimientoPuntos;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.util.List;

@Stateless
public class VencimientoPuntosDAO {
    @PersistenceContext(unitName = "sistema-ptsDS")
    private EntityManager entityManager;

    protected Session getSession(){
        return (SessionImpl)entityManager.getDelegate();
    }

    public void agregar(VencimientoPuntos v) {
        this.entityManager.persist(v);
    }

    public List<VencimientoPuntos> obtenerVencimientos() {
        return this.entityManager.createQuery("select v from VencimientoPuntos v").getResultList();
    }
}
