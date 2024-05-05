package com.example.sistemapts.rest;

import com.example.sistemapts.ejb.ClienteDAO;
import com.example.sistemapts.model.Cliente;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Response;

@Path("cliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteRest {
    @Inject
    ClienteDAO clienteDAO;

    @POST
    @Path("/")
    public Response agregar(Cliente c) {
        clienteDAO.agregar(c);
        return Response.ok(c).build();
    }

    @GET
    @Path("/")
    public Response lista() {
        return Response.ok(clienteDAO.obtenerPersonas()).build();
    }
}
