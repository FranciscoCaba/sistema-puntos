package com.example.sistemapts.rest;

import com.example.sistemapts.ejb.BolsaDePuntosDAO;
import com.example.sistemapts.ejb.ClienteDAO;
import com.example.sistemapts.ejb.ReglasDAO;
import com.example.sistemapts.ejb.VencimientoPuntosDAO;
import com.example.sistemapts.model.BolsaDePuntos;
import com.example.sistemapts.model.Cliente;
import com.example.sistemapts.model.ReglaAsignacionPuntos;
import com.example.sistemapts.model.VencimientoPuntos;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Path("bolsas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BolsasRest {
    @Inject
    BolsaDePuntosDAO bolsasDAO;
    @Inject
    ClienteDAO clienteDAO;
    @Inject
    ReglasDAO reglasDAO;
    @Inject
    VencimientoPuntosDAO vencimientosDAO;

    @POST
    @Path("/")
    public Response agregar(HashMap<String, Integer> cuerpo) {
        Integer monto = cuerpo.get("monto");
        Integer clienteId = cuerpo.get("cliente");
        int puntos = 0;
        Cliente unCliente = clienteDAO.obtenerCliente(clienteId);
        List<ReglaAsignacionPuntos> reglas = reglasDAO.obtenerPuntosPorMonto();
        List<VencimientoPuntos> vencimientos = vencimientosDAO.obtenerVencimientos();
        Date hoy = new Date();
        int cantDias = 0;

        for(ReglaAsignacionPuntos regla:reglas){
            if(regla.getLimiteInferior() <= monto && regla.getLimiteSuperior() >= monto){
                puntos = monto/regla.getEquivalencia();
            }
        }

        for(VencimientoPuntos vencimiento: vencimientos){
            if(hoy.after(vencimiento.getFechaInicio()) && hoy.before(vencimiento.getFechaFin())){
                cantDias = vencimiento.getDuracion();
            }
        }

        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DATE, cantDias);

        BolsaDePuntos bolsaGenerada = new BolsaDePuntos(
                unCliente, puntos, 0, puntos, monto, hoy, c.getTime()
        );

        bolsasDAO.agregar(bolsaGenerada);
        return Response.ok(bolsaGenerada).build();
    }

    @GET
    @Path("/")
    public Response lista() {
        return Response.ok(bolsasDAO.obtenerBolsas()).build();
    }

    @PUT
    @Path("/")
    public Response modificar (BolsaDePuntos b) {
        BolsaDePuntos b2=bolsasDAO.modificar(b);
        return Response.ok(b2).build();
    }
}
