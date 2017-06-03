package com.example.demo.util.mqtt;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

public class MqttUtil {
	private static Logger LOG = Logger.getLogger(MqttUtil.class);
	private final static String PROPERTIES_FILE_NAME = "/mqtt.properties";
	Properties props = new Properties();
	
	private static MqttUtil instance;
    
    private MqttUtil(){
    }
    
    public static MqttUtil getInstance(){
        if(instance == null){
            instance = new MqttUtil();
        }
        return instance;
    }
	
	private MqttClient mqttConnect() throws MqttException{
		MemoryPersistence persistence = new MemoryPersistence();
		/**
		  * Load device properties
		  */
		try {
			props.load(MqttUtil.class.getResourceAsStream(PROPERTIES_FILE_NAME));
		} catch (IOException e) {
			LOG.error("Not able to read the properties file, exiting..");
			System.exit(-1);
		}
		LOG.info("About to connect to MQTT broker with the following parameters: - BROKER_URL=" + 
					props.getProperty("BROKER_URL") +
					" CLIENT_ID=" + props.getProperty("CLIENT_ID"));
		MqttClient sampleClient = new MqttClient(props.getProperty("BROKER_URL"), props.getProperty("CLIENT_ID"),persistence);
        MqttConnectOptions connOpts = new MqttConnectOptions();
        connOpts.setCleanSession(true);
        sampleClient.connect(connOpts);
       
        LOG.info("Connected");
		return sampleClient;
	}
	

	
	public void mqttConnectNPublishNSubscribe(String payload){
	    try {
	    	MqttClient sampleClient = mqttConnect();
	    	LOG.info("Publish message="+payload.toString());
	        sampleClient.subscribe(props.getProperty("TOPIC_NAME"), 1);
	        MqttMessage message = new MqttMessage(payload.toString().getBytes(Charset.forName("UTF-8")));
	        if(props.getProperty("QOS")!=null){
	        	message.setQos(Integer.parseInt(props.getProperty("QOS")));
	    	}
	        sampleClient.setCallback(new SimpleCallback());
	        sampleClient.publish(props.getProperty("TOPIC_NAME"), message);
	        LOG.info("Message published");
	        LOG.info("About to disconnect from MQTT Broker..");
	        sampleClient.disconnect();
	        LOG.info("Successfully disconnected from MQTT Broker.");
	    } catch(MqttException ex){
	    	LOG.error("reason " + ex.getReasonCode());
	    	LOG.error("msg " + ex.getMessage());
	    	LOG.error("loc " + ex.getLocalizedMessage());
	    	LOG.error("cause " + ex.getCause());
	    	LOG.error("except " + ex);
	        //ex.printStackTrace();
	    }
}

}
