package com.huanshi.tool;

import com.huanshi.model.DataLine;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class DataAdapter {

    public DataLine dataMessageToDataLine(DataMessage dataMessage){
        DataLine dataLine = new DataLine();
        byte[] data = ((UplinkMessage) dataMessage).getPayloadRaw();
        float luminosity = (data[0] & 0xFF) * 16777216 + (data[1] & 0xFF) * 65536 + (data[2] & 0xFF) *256 + (data[3] & 0xFF);
        float airHumidity = (float) (((data[4] & 0xFF) *256 + (data[5] & 0xFF)) / 10.0);
        float airTempreture = calculateTem(data[6], data[7]);
        float earthHumidity = (float) (((data[8] & 0xFF) *256 + (data[9] & 0xFF)) / 10.0);
        float earthTempreture = calculateTem(data[10], data[11]);
        float earthConductivity = (data[12] & 0xFF) *256 + (data[13] & 0xFF);
        dataLine.setLuminosity(luminosity);        // 0 1 2 3
        dataLine.setAirHumidity(airHumidity);       // 4 5
        dataLine.setAirTempreture(airTempreture);     // 6 7
        dataLine.setEarthHumidity(earthHumidity);     // 8 9
        dataLine.setEarthTempreture(earthTempreture);   // 10 11
        dataLine.setEarthConductivity(earthConductivity); // 12 13
        String time = ((UplinkMessage) dataMessage).getMetadata().getTime().substring(0,19);
        LocalDateTime dateTime = LocalDateTime.parse(time);
        dataLine.setTime(dateTime);
        return dataLine;
    }

    public float calculateTem(byte high, byte low) {
        float tem = (high & 0xFF) *256 + (low & 0xFF);
        return high < 0 ? (tem - 65536)/10 : tem/10;
    }

}
