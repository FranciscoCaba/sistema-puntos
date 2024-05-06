package com.example.sistemapts.ejb;

import com.example.sistemapts.model.BolsaDePuntos;
import com.example.sistemapts.model.Cliente;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.internal.SessionImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
public class BolsaDePuntosDAO {
    @PersistenceContext(unitName = "sistema-ptsDS")
    private EntityManager entityManager;

    @Inject
    ClienteDAO clienteDAO;

    protected Session getSession(){
        return (SessionImpl)entityManager.getDelegate();
    }

    public void agregar(BolsaDePuntos b) {
        this.entityManager.persist(b);
    }

    public List<BolsaDePuntos> obtenerBolsas(Integer cliente, Integer rango) {
        CriteriaBuilder qb = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<BolsaDePuntos> cq = qb.createQuery(BolsaDePuntos.class);
        Root<BolsaDePuntos> bolsaRoot = cq.from(BolsaDePuntos.class);

        List<Predicate> predicates = new ArrayList<>();

        if (rango != null)
            predicates.add(qb.gt(bolsaRoot.get("saldo"), rango));
        if (cliente != null){
            Cliente clienteObj = clienteDAO.obtenerCliente(cliente);
            predicates.add(qb.equal(bolsaRoot.get("cliente"), clienteObj));
        }
        Date hoy = new Date();
        predicates.add(qb.gt(bolsaRoot.get("fechaCaducidad"), hoy.getTime()));

        cq.select(bolsaRoot)
                .where(predicates.toArray(new Predicate[]{}));

        return this.entityManager.createQuery(cq).getResultList();
    }

    public List<BolsaDePuntos> obtenerBolsasAVencer(Integer dias){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, dias);
        return this.entityManager.createQuery("select b from BolsaDePuntos b where b.fechaCaducidad = :fechaCalc")
                .setParameter("fechaCalc", c.getTime())
                .getResultList();
    }

    public BolsaDePuntos modificar (BolsaDePuntos b) {
        return this.entityManager.merge(b);
    }
}
