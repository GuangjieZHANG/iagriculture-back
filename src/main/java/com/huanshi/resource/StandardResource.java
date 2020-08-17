package com.huanshi.resource;

import com.huanshi.dao.StandardDAO;
import com.huanshi.model.ErrorMessage;
import com.huanshi.model.Standard;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("/standard")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StandardResource {

    private static final Logger LOGGER = Logger.getLogger("Agriculture-back");

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
        try {
            standardDAO.update(standard);
            return Response.ok(standard).build();
        }  catch (Exception e) {
            LOGGER.severe(e.getMessage());
            ErrorMessage message = new ErrorMessage("none",
                    "Cannot update the dataline, please re-try again later.", "none");
            return Response.serverError().entity(message).build();
        }
    }

    @POST
    public Response createStandard(@Valid Standard standard) {
        try {
            Standard created = standardDAO.create(standard);
            return Response.ok(created).build();
        }  catch (Exception e) {
            LOGGER.severe(e.getMessage());
            ErrorMessage message = new ErrorMessage("none",
                    "Cannot create the dataline, please re-try again later.", "none");
            return Response.serverError().entity(message).build();
        }

    }

    @DELETE
    @Path("/{id}")
    public Response deleteStandard(@PathParam("id") long id) {
        try {
            Standard standard = standardDAO.delete(id);
            return Response.ok(standard).build();
        } catch (Exception e) {
            LOGGER.severe(e.getMessage());
            ErrorMessage message = new ErrorMessage("none",
                    "Cannot delete the latest dataline, please re-try again later.", "none");
            return Response.serverError().entity(message).build();
        }
    }
}
