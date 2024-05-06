package com.example.sistemapts.rest;

import com.example.sistemapts.ejb.*;
import com.example.sistemapts.model.*;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.util.*;

@Path("uso-puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class UsoPuntosRest {
    @Inject
    ConceptoUsoPuntosDAO conceptoDAO;
    @Inject
    BolsaDePuntosDAO bolsasDAO;
    @Inject
    ClienteDAO clienteDAO;
    @Inject
    CabeceraDAO cabeceraDAO;
    @Inject
    DetalleDAO detalleDAO;
    @POST
    @Path("/")
    public Response agregar(HashMap<String, Integer> cuerpo) {
        Integer clienteId = cuerpo.get("cliente");
        Integer conceptoId = cuerpo.get("concepto");
        Cliente unCliente = clienteDAO.obtenerCliente(clienteId);
        ConceptoUsoDePuntos unConcepto = conceptoDAO.obtenerConcepto(conceptoId);
        List<BolsaDePuntos> bolsas = unCliente.getListaBolsas();
        Date hoy = new Date();
        Integer suma = 0;
        Integer puntosReq = unConcepto.getPuntosReq();

        Collections.sort(bolsas, new Comparator<BolsaDePuntos>() {
            @Override
            public int compare(BolsaDePuntos o1, BolsaDePuntos o2) {
                return o1.getFechaCaducidad().compareTo(o2.getFechaCaducidad());
            }
        });

        for( BolsaDePuntos bolsa : bolsas){
            if(bolsa.getFechaCaducidad().after(hoy)){
                suma+=bolsa.getSaldo();
            }
        }

        if(suma >= puntosReq){
            Cabecera cabecera = new Cabecera(unCliente, unConcepto, puntosReq, hoy);
            cabeceraDAO.agregar(cabecera);
            for( BolsaDePuntos bolsa : bolsas){
                if(bolsa.getFechaCaducidad().after(hoy) && bolsa.getSaldo() > 0){
                    Integer saldo = bolsa.getSaldo();
                    Detalle detalle = new Detalle(cabecera, bolsa, 0);
                    if (puntosReq >= saldo){
                        detalle.setPuntajeUtilizado(saldo);
                        puntosReq -= saldo;
                        bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado()+saldo);
                        bolsa.setSaldo(0);
                    } else{
                        detalle.setPuntajeUtilizado(puntosReq);
                        bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado()+puntosReq);
                        bolsa.setSaldo(bolsa.getSaldo()-puntosReq);
                        puntosReq = 0;
                    }
                    bolsasDAO.modificar(bolsa);
                    detalleDAO.agregar(detalle);

                    if (puntosReq == 0)
                        break;
                }
            }

            return Response.ok(cabecera).build();
        }else{
            return Response.status(Response.Status.BAD_REQUEST).entity("Saldo Insuficiente").build();
        }
    }

    @GET
    @Path("/")
    public Response lista() {
        return Response.ok(conceptoDAO.obtenerConceptos()).build();
    }
}
