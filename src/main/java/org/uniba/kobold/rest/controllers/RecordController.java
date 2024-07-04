package org.uniba.kobold.rest.controllers;

import com.google.gson.Gson;
import org.uniba.kobold.rest.models.Record;
import org.uniba.kobold.rest.services.RecordService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("records")
public class RecordController {
    Gson gson = new Gson();
    RecordService recordService = new RecordService();

    @POST
    @Produces("application/json")
    public Response save(String json) {
        Record record = recordService.save(gson.fromJson(json, Record.class));

        String jsonString = gson.toJson(record);

        return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{recordId}")
    @Produces("application/json")
    public Response deleteById(@PathParam("recordId") String recordId) {
        int id = recordService.deleteById(Integer.parseInt(recordId));

        if(id == -1) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok("true", MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("/{recordId}")
    @Produces("application/json")
    public Response updateById(String json, @PathParam("recordId") String recordId) {
        Record record = recordService.updateById(gson.fromJson(json, Record.class), Integer.parseInt(recordId));

        String jsonString = gson.toJson(record);

        if(record == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("/{recordId}")
    @Produces("application/json")
    public Response updateById(@PathParam("recordId") String recordId) {
        Record record = recordService.getById(Integer.parseInt(recordId));

        String jsonString = gson.toJson(record);

        if(record == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("/best")
    @Produces("application/json")
    public Response getBestFive() {
        List<Record> records = recordService.getBest(5);

        String jsonString = gson.toJson(records);

        return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
    }

}