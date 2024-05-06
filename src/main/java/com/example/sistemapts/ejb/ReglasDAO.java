package com.example.sistemapts.ejb;

import com.example.sistemapts.model.ReglaAsignacionPuntos;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class ReglasDAO {
    @PersistenceContext(unitName = "sistema-ptsDS")
    private EntityManager entityManager;
    public List<ReglaAsignacionPuntos> obtenerPuntosPorMonto() {
        return this.entityManager.createQuery("select r from ReglaAsignacionPuntos r").getResultList();
    }
}
