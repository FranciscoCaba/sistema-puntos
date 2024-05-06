package com.example.sistemapts.rest;

import com.example.sistemapts.ejb.BolsaDePuntosDAO;
import com.example.sistemapts.ejb.ClienteDAO;
import com.example.sistemapts.model.BolsaDePuntos;
import com.example.sistemapts.model.Cliente;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.HashMap;

@Path("bolsas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class BolsasRest {
    @Inject
    BolsaDePuntosDAO bolsasDAO;
    @Inject
    ClienteDAO clienteDAO;

    @POST
    @Path("/")
    public Response agregar(HashMap<String, Integer> cuerpo) {
        Integer monto = cuerpo.get("monto");
        Integer clienteId = cuerpo.get("cliente");
        Cliente unCliente = clienteDAO.obtenerCliente(clienteId);

        BolsaDePuntos bolsaGenerada = new BolsaDePuntos(
                unCliente, monto, 0, monto, monto, LocalDate.now(), LocalDate.now()
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
