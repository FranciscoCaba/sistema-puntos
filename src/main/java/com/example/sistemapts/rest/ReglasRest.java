package com.example.sistemapts.rest;

import com.example.sistemapts.ejb.ClienteDAO;
import com.example.sistemapts.ejb.ReglasDAO;
import com.example.sistemapts.model.ReglaAsignacionPuntos;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.TypedQuery;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;




@Path("reglas_asignacion_puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ReglasRest {

    @Inject
    ReglasDAO reglasDAO;

    @POST
    @Path("/")
    public Response  obtenerPuntosPorMonto(HashMap<String, Integer> monto) {
        List<ReglaAsignacionPuntos> reglas = reglasDAO.obtenerPuntosPorMonto();
        Integer valorMonto = monto.get("monto");
        int puntos = 0;

        for (ReglaAsignacionPuntos regla : reglas){
            if (regla.getLimiteInferior() <= valorMonto && valorMonto <= regla.getLimiteSuperior())
                puntos = valorMonto / regla.getEquivalencia();

        }
        return Response.ok(puntos).build();
    }
}