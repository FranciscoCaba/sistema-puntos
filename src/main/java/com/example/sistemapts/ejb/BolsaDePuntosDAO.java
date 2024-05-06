package com.example.sistemapts.ejb;

import com.example.sistemapts.model.BolsaDePuntos;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.util.List;

@Stateless
public class BolsaDePuntosDAO {
    @PersistenceContext(unitName = "sistema-ptsDS")
    private EntityManager entityManager;

    protected Session getSession(){
        return (SessionImpl)entityManager.getDelegate();
    }

    public void agregar(BolsaDePuntos b) {
        this.entityManager.persist(b);
    }

    public List<BolsaDePuntos> obtenerBolsas() {
        return this.entityManager.createQuery("select b from BolsaDePuntos b").getResultList();
    }

    public BolsaDePuntos modificar (BolsaDePuntos b) {
        return this.entityManager.merge(b);
    }
}
