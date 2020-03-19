package com.huanshi.resource;

import com.huanshi.dao.StandardDAO;
import com.huanshi.model.Standard;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/standard")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StandardResource {

    @Inject
    StandardDAO standardDAO;

    @GET
    public Response getStandards() {
        List<Standard> standards = standardDAO.getAll();
        return Response.ok(standards).build();
    }

    @GET
    @Path("/last/{deviceName}")
    public Response getLastStrandard(@PathParam("deviceName") String deviceName) {
        Standard standard = standardDAO.getTheLatest(deviceName);
        return Response.ok(standard).build();
    }

    @PUT
    public Response updateStandard(@Valid Standard standard){
        standardDAO.update(standard);
        return Response.ok(standard).build();
    }

    @POST
    public Response createStandard(@Valid Standard standard) {
        Standard created = standardDAO.create(standard.getMaxAirTempreture(), standard.getMinAirTempreture(),
                standard.getMaxAirHumidity(), standard.getMinAirHumidity(), standard.getMaxEarthTempreture(),
                standard.getMinEarthTempreture(), standard.getMaxEarthHumidity(), standard.getMinEarthHumidity(),
                standard.getMaxEarthPh(),standard.getMinEarthPh(), standard.getMaxNitrogen(), standard.getMinNitrogen(),
                standard.getMaxPhosphorus(), standard.getMinPhosphorus(), standard.getMaxPotassium(), standard.getMinPotassium(),
                standard.getTime(), standard.getDevice());
        return Response.ok(created).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStandard(@PathParam("id") long id) {
        Standard standard = standardDAO.delete(id);
        return Response.ok(standard).build();
    }
}
