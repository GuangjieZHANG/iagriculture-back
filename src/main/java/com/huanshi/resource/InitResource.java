package com.huanshi.resource;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.huanshi.dao.DataLineDAO;
import com.huanshi.model.DataLine;
import com.huanshi.tool.DataAdapter;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.messages.*;
import org.thethingsnetwork.data.mqtt.Client;

@Path("/init")
@ApplicationScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InitResource {


    @Inject
    private DataLineDAO dataLineDAO;

    @Inject
    private DataAdapter dataAdapter;

    @GET
    public String init() throws Exception{
        String region =  "asia-se";
        String appId = "mtest";
        String accessKey = "ttn-account-v2.agyVWV7ms0P35DRnxVfTRkfZt8ieMzM31nLE9cGku4E";

        Client client = new Client(region, appId, accessKey);

        class Response {

            private boolean led;

            public Response(boolean _led) {
                led = _led;
            }
        }

        client.onMessage(null, "led", (String _devId, DataMessage _data) -> {
            try {
                RawMessage message = (RawMessage) _data;
                // Toggle the LED
                DownlinkMessage response = new DownlinkMessage(0, new Response(!message.asBoolean()));

                /**
                 * If you don't have an encoder payload function:
                 * client.send(_devId, new Response(0, message.asBoolean() ? new byte[]{0x00} : new byte[]{0x01}));
                 */
                System.out.println("Sending: " + response);
                client.send(_devId, response);
            } catch (Exception ex) {
                System.out.println("Response failed: " + ex.getMessage());
            }
        });

        // Here we trait the data
        client.onMessage((String devId, DataMessage data) -> {
            DataLine dataLine = dataAdapter.dataMessageToDataLine(data);
            dataLine.setDevice(devId);
            System.out.println(dataLine.toString());
            dataLineDAO.create(dataLine);
        });

        client.onActivation((String _devId, ActivationMessage _data) -> System.out.println("Activation: " + _devId + ", data: " + _data.getDevAddr()));

        client.onError((Throwable _error) -> System.err.println("error: " + _error.getMessage()));

        client.onConnected((Connection _client) -> System.out.println(" ------------ Connected ! ----------------"));

        client.start();

        return "hello, init finished!";
    }
}