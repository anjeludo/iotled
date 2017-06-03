package com.example.demo.util.mqtt;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

class SimpleCallback implements MqttCallback {
	private static Logger LOG = Logger.getLogger(SimpleCallback.class);
	//Called when the client lost the connection to the broker
	public void connectionLost(Throwable arg0) {
		LOG.info("Connection lost to the broker tcp://loclahost:1883");
	}
	
    //Called when a new message has arrived
    public void messageArrived(String topic, MqttMessage message) throws Exception {
    	LOG.info("-------------------------------------------------");
    	LOG.info("| Topic:" + topic);
    	LOG.info("| Message: " + new String(message.getPayload()));
    	LOG.info("-------------------------------------------------");
    }

    public void deliveryComplete(IMqttDeliveryToken token) {
		System.out.println("Delivery is Complete");
		
	}
	
}