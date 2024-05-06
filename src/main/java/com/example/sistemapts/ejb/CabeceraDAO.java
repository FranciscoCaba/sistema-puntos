package com.example.sistemapts.ejb;

import com.example.sistemapts.model.Cliente;
import com.example.sistemapts.model.ConceptoUsoDePuntos;
import jakarta.inject.Inject;
import jakarta.persistence.criteria.*;
import com.example.sistemapts.model.Cabecera;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.util.ArrayList;
import java.util.List;

@Stateless
public class CabeceraDAO {
    @PersistenceContext(unitName = "sistema-ptsDS")
    private EntityManager entityManager;

    @Inject
    ClienteDAO clienteDAO;
    @Inject
    ConceptoUsoPuntosDAO conceptoUsoPuntosDAO;

    protected Session getSession(){
        return (SessionImpl)entityManager.getDelegate();
    }

    public void agregar(Cabecera c) {
        this.entityManager.persist(c);
    }

    public List<Cabecera> obtenerUsos(Integer concepto, String fecha, Integer cliente) {
        CriteriaBuilder qb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Cabecera> cq = qb.createQuery(Cabecera.class);
        Root<Cabecera> cabeceraRoot = cq.from(Cabecera.class);

        List<Predicate> predicates = new ArrayList<>();

        if (concepto != null){
            ConceptoUsoDePuntos conceptoObj = conceptoUsoPuntosDAO.obtenerConcepto(concepto);

            predicates.add(qb.equal(cabeceraRoot.get("conceptoUsoDePuntos"), conceptoObj));
        }
        if (fecha != null)
            predicates.add(qb.equal(cabeceraRoot.get("fecha"), fecha));
        if (cliente != null){
            Cliente clienteObj = clienteDAO.obtenerCliente(cliente);
            predicates.add(qb.equal(cabeceraRoot.get("cliente"), clienteObj));
        }

        cq.select(cabeceraRoot)
                .where(predicates.toArray(new Predicate[]{}));

        return this.entityManager.createQuery(cq).getResultList();

    }
}
