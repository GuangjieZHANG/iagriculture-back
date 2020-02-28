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
        dataLine.setAirTempreture(data[0]);
        dataLine.setAirHumidity(data[1]);
        dataLine.setWind(data[2]+"");
        dataLine.setEarthTempreture(data[3]);
        dataLine.setEarthHumidity(data[4]);
        dataLine.setEarthPh(data[5]);
        dataLine.setNitrogen(data[6]);
        dataLine.setPotassium(data[7]);
        dataLine.setPhosphorus(data[8]);
        String time = ((UplinkMessage) dataMessage).getMetadata().getTime().substring(0,19);
        LocalDateTime dateTime = LocalDateTime.parse(time);
        dataLine.setTime(dateTime);
        return dataLine;
    }

}
