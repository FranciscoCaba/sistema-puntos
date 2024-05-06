package com.example.sistemapts.ejb;

import com.example.sistemapts.model.ConceptoUsoDePuntos;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.util.List;

@Stateless
public class ConceptoUsoPuntosDAO {
    @PersistenceContext(unitName = "sistema-ptsDS")
    private EntityManager entityManager;

    protected Session getSession(){
        return (SessionImpl)entityManager.getDelegate();
    }

    public List<ConceptoUsoDePuntos> obtenerConceptos() {
        return this.entityManager.createQuery("select c from ConceptoUsoDePuntos c").getResultList();
    }

    public ConceptoUsoDePuntos obtenerConcepto(Integer id) {
        return (ConceptoUsoDePuntos) this.entityManager.createQuery("select c from ConceptoUsoDePuntos c where c.idConceptoUsoDePuntos = :id")
                .setParameter("id", id)
                .getSingleResult();
    }
}
