package HomaTron.Database;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;


public class mqtt implements MqttCallback 
{
	MqttClient client;	
	static String incomingIDrequest;
	static String incomingID;
	int intValue;
	String id;
	static boolean messageRecieved = false;
	
	public static void publishToBroker(String topic, String content)	
	{
	    int qos             = 0;
	    String broker       = "tcp://127.0.0.1:1883";
	    String clientId     = "JavaSample23rfazc";
		     
	    MemoryPersistence persistence = new MemoryPersistence();
	                      
	    try {
	        MqttClient sampleClient = new MqttClient(broker, clientId, persistence);   
	        MqttConnectOptions connOpts = new MqttConnectOptions();
	        connOpts.setCleanSession(true);
	        
	        System.out.println("Connecting to broker: "+broker);
	        sampleClient.connect(connOpts);
	        

	        System.out.println("Connected");
	        
	        sampleClient.subscribe("homatron");
	        
	        System.out.println("Publishing message: "+content);
	        MqttMessage message = new MqttMessage(content.getBytes());
	        message.setQos(qos);
	        sampleClient.publish(topic, message);
	        System.out.println("Message published");
	        
	        
	       //sampleClient.disconnect();
	       //System.out.println("Disconnected");             
	       //System.exit(0);
	    } catch(MqttException me) {
	        System.out.println("reason "+me.getReasonCode());
	        System.out.println("msg "+me.getMessage());
	        System.out.println("loc "+me.getLocalizedMessage());
	        System.out.println("cause "+me.getCause());
	        System.out.println("excep "+me);
	        me.printStackTrace();
	    }
	}
	
	public void Connect() 
	{
		String broker       = "tcp://127.0.0.1:1883";
		String clientId     = "1203809fjase908";
		
		MemoryPersistence persistence = new MemoryPersistence();
		
	    try 
	    {
	    	//System.out.println("connecting"); 
	        client = new MqttClient(broker, clientId, persistence); 
	        client.connect();
	        //System.out.println("connected"); 
	        client.setCallback(this);
	        //System.out.println("callback set"); 
	        client.subscribe("homatronSensor");
	        //System.out.println("Subscribed"); 
	        
	        String content = sensorHandler.incomingIDrequest;
			//System.out.println("Publishing message: "+content);
	        MqttMessage message = new MqttMessage(content.getBytes());	      
	        client.publish("homatron", message);
	        //System.out.println("Message published");
	   
	    } 
	    catch (MqttException e) 
	    {
	        e.printStackTrace();
	    }
	}

	public void connectionLost(Throwable cause) 
	{	
	}

	public static void test()
	{
		new mqtt().Connect();
	}
	
	public void messageArrived(String topic, MqttMessage message) throws Exception 
	{	
		 String messageString = message.toString();	 
		 String id = messageString.substring(0, 3);	 
		 String value = messageString.substring(4, messageString.length());
		 		 
		 System.out.println("Recieved data from id: " + id);	 
		 System.out.println("Value: " + value);	 
		 
		 intValue = Integer.parseInt(value);
		 SetValue(intValue, id);	    
	}
	
	private void SetValue(int x, String y) 
	{
		//System.out.println("Value Set");	 
		sensorHandler.value = x;
		sensorHandler.id = y;
		messageRecieved = true;
		
	}

	public void deliveryComplete(IMqttDeliveryToken token) 
	{
	}
}