package com.huanshi.resource;

import com.huanshi.dao.DataLineDAO;
import com.huanshi.model.DataLine;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/dataline")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DataLineResource {

    @Inject
    private DataLineDAO dataLineDAO;

    @GET
    @Path("/last/{deviceName}")
    public Response getLastDataLine(@PathParam("deviceName")String deviceName) {
        DataLine dataLine = dataLineDAO.getTheLatest(deviceName);
        return Response.ok(dataLine).build();
    }

    @GET
    @Path("/allByDevice/{deviceName}")
    public Response getDataLineByDevice(@PathParam("deviceName")String deviceName) {
        List<DataLine> dataLines = dataLineDAO.getAllByDevice(deviceName);
        return Response.ok(dataLines).build();
    }

    @GET
    @Path("/history/{deviceName}")
    public Response getHistoryByDevice(@PathParam("deviceName")String deviceName) {
        List<DataLine> dataLines = dataLineDAO.getHistoryByDevice(deviceName);
        return Response.ok(dataLines).build();
    }

}
