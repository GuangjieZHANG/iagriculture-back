package com.huanshi.tool;

import com.huanshi.model.DataLine;
import com.huanshi.model.Node;
import org.thethingsnetwork.data.common.messages.DataMessage;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataAdapter {

    private DataLine dataMessageToDataLine(DataMessage dataMessage, String devId){
        DataLine dataLine = new DataLine();
        Node device = new Node(devId);
        return dataLine;
    }

}
