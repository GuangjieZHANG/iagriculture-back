package com.huanshi;

import com.huanshi.dao.DataLineDAO;
import com.huanshi.model.DataLine;
import com.huanshi.tool.DataAdapter;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.messages.ActivationMessage;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;
import org.thethingsnetwork.data.common.messages.RawMessage;
import org.thethingsnetwork.data.mqtt.Client;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.ws.rs.core.Application;

@ApplicationScoped
public class IagricApplication extends Application {

    private static String region =  "asia-se";
    private static String appId = "mtest";
    private static String accessKey = "ttn-account-v2.agyVWV7ms0P35DRnxVfTRkfZt8ieMzM31nLE9cGku4E";

    @Inject
    DataLineDAO dataLineDAO;

    @Inject
    DataAdapter dataAdapter;

    void onStart(@Observes StartupEvent ev) throws Exception{
        Client client = new Client(region, appId, accessKey);
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
        System.out.println("=======The server is starting=======");
    }

    void onStop(@Observes ShutdownEvent ev) {
        System.out.println("=======The server is stopping=======");
    }

    class Response {
        private boolean led;
        public Response(boolean _led) {
            led = _led;
        }
    }

}
