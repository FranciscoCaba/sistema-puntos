package com.example.sistemapts.rest;

import com.example.sistemapts.ejb.ClienteDAO;
import com.example.sistemapts.model.Cliente;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.core.Response;

import java.time.LocalDate;
import java.util.List;

@Path("cliente")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteRest {
    @Inject
    ClienteDAO clienteDAO;

    @GET
    @Path("/")
    public Response lista() {
        return Response.ok(clienteDAO.obtenerPersonas()).build();
    }

    @POST
    @Path("/")
    public Response agregar(Cliente c) {
        clienteDAO.agregar(c);
        return Response.ok(c).build();
    }

    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") Integer id) {
        clienteDAO.eliminar(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/")
    public Response modificar (Cliente c) {
        Cliente p2=clienteDAO.modificar(c);
        return Response.ok(p2).build();
    }

    @GET
    @Path("/nacionalidad/{nacionalidad}")
    public List<Cliente> obtenerClientesPorNacionalidad(@PathParam("nacionalidad") String nacionalidad) {
        return clienteDAO.obtenerClientesPorNacionalidad(nacionalidad);
    }

    @GET
    @Path("/fecha/{fecha}")
    public List<Cliente> obtenerClientesPorFechaNacimiento(@PathParam("fecha") String fecha) {
        LocalDate fechaNacimiento = LocalDate.parse(fecha);
        return clienteDAO.obtenerClientesPorFechaNacimiento(fechaNacimiento);
    }


}
