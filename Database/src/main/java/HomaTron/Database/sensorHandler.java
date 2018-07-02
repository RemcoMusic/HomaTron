package HomaTron.Database;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class sensorHandler 
{
	private String formattedID;
	private String formattedSensorID;
	private String formattedCommand; 

	public static int value;
	public static String id;
	public static String incomingIDrequest;
	
	public sensorHandler()
	{	
	}
	
	public void pushSensorData(JSONArray array)
	{	
		for(int i = 0; i<array.length(); i++)
		{
			try 
			{
				int deviceID = array.getJSONObject(i).getInt("inputDeviceID");
				int outputDeviceID = array.getJSONObject(i).getInt("outputDeviceID");
				int databaseValue = array.getJSONObject(i).getInt("inputValue");
				String operator = array.getJSONObject(i).getString("operator");
				int outputCommand = array.getJSONObject(i).getInt("outputCommand");
				int RowID = array.getJSONObject(i).getInt("rowID");
				
				formatSensorID(deviceID);
				mqtt.publishToBroker("homatronSensor", getFormattedSensorID() + ":" + "999" + ":" + RowID + ":" + operator + ":" + databaseValue);
				
			}
			catch (JSONException e)
			{
				System.out.println("PushSensorData methode gaat niet goed!");
			}
		}
		/*
		formatSensorID(deviceID);
		mqtt.checkSensor(getFormattedID());
		System.out.println("Result: " + value);	 
		return value;
		*/		
	}
	
	/*public void checkValueWithSensor(JSONArray array)
	{
		for(int i = 0; i<array.length(); i++)
		{
			try 
			{
				int deviceID = array.getJSONObject(i).getInt("inputDeviceID");
				int outputDeviceID = array.getJSONObject(i).getInt("outputDeviceID");
				int databaseValue = array.getJSONObject(i).getInt("inputValue");
				String operator = array.getJSONObject(i).getString("operator");
				int outputCommand = array.getJSONObject(i).getInt("outputCommand");
				formatID(outputDeviceID);
				formatCommand(outputCommand);
		
				if(operator.equalsIgnoreCase("="))
				{
					if(checkSensor(deviceID) == databaseValue)
					{
						System.out.println("operator = ");
						mqtt.publishToBroker("homatron", getFormattedID() + ":" + getFormattedCommand());
					}
				}
				
				if(operator.equalsIgnoreCase("<"))
				{
					if(checkSensor(deviceID) < databaseValue)
					{
						System.out.println("operator < ");
						mqtt.publishToBroker("homatron", getFormattedID() + ":" + getFormattedCommand());
					}
				}
				
				if(operator.equalsIgnoreCase(">"))
				{
					if(checkSensor(deviceID) > databaseValue)
					{
						System.out.println("operator > ");
						mqtt.publishToBroker("homatron", getFormattedID() + ":" + getFormattedCommand());
					}
				}
				
				if(operator.equalsIgnoreCase(">="))
				{
					if(checkSensor(deviceID) >= databaseValue)
					{
						System.out.println("operator >= ");
						mqtt.publishToBroker("homatron", getFormattedID() + ":" + getFormattedCommand());
					}
				}
				
				if(operator.equalsIgnoreCase("<="))
				{
					if(checkSensor(deviceID) <= databaseValue)
					{
						System.out.println("operator <= ");
						mqtt.publishToBroker("homatron", getFormattedID() + ":" + getFormattedCommand());
					}
				}
			} 
			catch (JSONException e) 
			{
				System.out.println("There is no data to compare");
			}				
		}
	}*/

	public void checkStatusDevice(JSONArray array)
	{
		for(int i = 0; i<array.length(); i++)
		{
			try 
			{
				
				mqtt.test();
				
				int deviceID = array.getJSONObject(i).getInt("inputDeviceID");
				int outputDeviceID = array.getJSONObject(i).getInt("outputDeviceID");
				int databaseValue = array.getJSONObject(i).getInt("inputValue");
				String operator = array.getJSONObject(i).getString("operator");
				int outputCommand = array.getJSONObject(i).getInt("outputCommand");
				int rowID = array.getJSONObject(i).getInt("rowID");
				
				//System.out.println("Dit is de rowID id: " + rowID);
				//System.out.println("Dit is de sensor device id: " + deviceID);
				formatSensorID(deviceID);
				formatID(outputDeviceID);
				formatCommand(outputCommand);
				//System.out.println("Dit is de id van de ontvangen message: " + id);
				//System.out.println("Dit is de id van de ontvangen rowid : " + value);
				//System.out.println("Is dit de goede formattedsensorid?: " + getFormattedSensorID());
				//System.out.println("Dit is de id van de andere sensor dan lololol: " + getFormattedSensorID());
				//System.out.println("Dit is de operator sldjhgfewiyvgfberwuyvgfkerajgvfSJUDYFVRWUJYVGWEUGTVWRUTGVFEQWUOYGVRAOUFYVEQUGTVWR: " + operator);
				
				if(id.equalsIgnoreCase(getFormattedSensorID()))
				{
					System.out.println("Ik kom hier 1");
					if(value == rowID)
					{
						System.out.println("Ik kom hier 2");
						mqtt.publishToBroker("homatron", getFormattedID() + ":" + getFormattedCommand());
					}
					
					id = null;
					value = 0;
					
				}
			}
			catch (Exception e)
			{
				System.out.println("PushSensorData methode gaat niet goed!");
			}
		}
	}
	
	public void pushOutputData(JSONArray array)
	{
		
	}
	
	
	
	public void formatSensorID(int ID)
	{
		String formattedID = String.format("%03d", ID);
		setFormattedID(formattedID);
	}
	
	public void formatID(int ID)
	{
		String formattedID = String.format("%03d", ID);
		setFormattedID(formattedID);
	}

	public void formatCommand(int Command)
	{
		String formattedCommand = String.format("%03d", Command);
		setFormattedCommand(formattedCommand);
	}
	
	public void setFormattedSensorID(String string)
	{
		this.formattedSensorID = string;
	}
	
	public String getFormattedSensorID()
	{
		return this.formattedSensorID;
	}
	
	public void setFormattedID(String string)
	{
		this.formattedID = string;
	}
	
	public String getFormattedID()
	{
		return this.formattedID;
	}
	
	public void setFormattedCommand(String string)
	{
		this.formattedCommand = string;
	}
	
	public String getFormattedCommand()
	{
		return this.formattedCommand;
	}
}
