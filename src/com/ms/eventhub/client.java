package com.ms.eventhub;

import java.io.IOException;
import java.nio.charset.*;
import java.util.*;
import java.util.concurrent.ExecutionException;

import com.microsoft.azure.eventhubs.*;
import com.microsoft.azure.servicebus.ConnectionStringBuilder;

public class client {

	public static void main(String[] args) 
            throws Exception, ExecutionException, InterruptedException, IOException
    {
		final String namespaceName = "----ServiceBusNamespaceName-----";
        final String eventHubName = "----EventHubName-----";
        final String sasKeyName = "-----SharedAccessSignatureKeyName-----";
        final String sasKey = "---SharedAccessSignatureKey----";
        ConnectionStringBuilder connStr = new ConnectionStringBuilder(namespaceName, eventHubName, sasKeyName, sasKey);
        byte[] payloadBytes;
        		EventData sendEvent;
        EventHubClient ehClient = EventHubClient.createFromConnectionStringSync(connStr.toString());
        String s="Test message for event hub";
        payloadBytes =  s.getBytes("UTF-8");
        sendEvent = new EventData(payloadBytes);
        //Send using PartitionKey - all Events with Same partitionKey will land on the Same Partition
        ehClient.sendSync(sendEvent,"partion1");
        ehClient.sendSync(sendEvent,"partion2");
             
        return;
        
    }

}
