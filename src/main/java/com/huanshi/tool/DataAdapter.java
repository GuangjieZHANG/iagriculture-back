package com.huanshi.tool;

import com.huanshi.model.DataLine;
import com.huanshi.model.Node;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@ApplicationScoped
public class DataAdapter {

    public DataLine dataMessageToDataLine(DataMessage dataMessage){
        DataLine dataLine = new DataLine();
        byte[] data = ((UplinkMessage) dataMessage).getPayloadRaw();
        System.out.println("-----data[0]-----" + data[0]);
        System.out.println("-----data[1]-----" + data[1]);
        System.out.println("-----data[2]-----" + data[2]);
        System.out.println("-----data[3]-----" + data[3]);
        System.out.println("-----data[4]-----" + data[4]);
        System.out.println("-----data[5]-----" + data[5]);
        System.out.println("-----data[6]-----" + data[6]);
        System.out.println("-----data[7]-----" + data[7]);
        System.out.println("-----data[8]-----" + data[8]);
        String time = ((UplinkMessage) dataMessage).getMetadata().getTime();
        //DateTimeFormatter formatter = DateTimeFormatter.ISO_INSTANT;
        LocalDateTime dateTime = LocalDateTime.parse(time, DateTimeFormatter.ISO_INSTANT);
        dataLine.setTime(dateTime);
        return dataLine;
    }

}
