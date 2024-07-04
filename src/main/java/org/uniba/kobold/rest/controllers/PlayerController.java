package org.uniba.kobold.rest.controllers;

import com.google.gson.Gson;
import org.uniba.kobold.rest.models.Player;
import org.uniba.kobold.rest.services.PlayerService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("players")
public class PlayerController {
    Gson gson = new Gson();
    PlayerService playerService = new PlayerService();

    @POST
    @Produces("application/json")
    public Response save(String json) {
        Player player = playerService.save(gson.fromJson(json, Player.class));

        String jsonString = gson.toJson(player);

        return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
    }

    @DELETE
    @Path("/{playerId}")
    @Produces("application/json")
    public Response deleteById(@PathParam("playerId") String playerId) {
        int id = playerService.deleteById(Integer.parseInt(playerId));

        if(id == -1) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok("true", MediaType.APPLICATION_JSON).build();
        }
    }

    @PUT
    @Path("/{playerId}")
    @Produces("application/json")
    public Response updateById(String json, @PathParam("playerId") String playerId) {
        Player player = playerService.updateById(gson.fromJson(json, Player.class), Integer.parseInt(playerId));

        String jsonString = gson.toJson(player);

        if(player == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }

    @GET
    @Path("/{playerId}")
    @Produces("application/json")
    public Response updateById(@PathParam("playerId") String playerId) {
        Player player = playerService.getById(Integer.parseInt(playerId));

        String jsonString = gson.toJson(player);

        if(player == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(jsonString, MediaType.APPLICATION_JSON).build();
        }
    }

}