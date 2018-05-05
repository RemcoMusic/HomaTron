package HomaTron.Database;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class sensorHandler 
{
	private String formattedID; 
	private String formattedCommand; 
	
	public sensorHandler()
	{
		
	}
	
	public int checkSensor(int deviceID)
	{
		//Hier kan de methode komen om de sensor data op te vragen 
		return 5;
	}
	
	public void checkValueWithSensor(JSONArray array)
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
				intFormatting(outputDeviceID, outputCommand);
				
				//System.out.println(getFormattedID());
				//System.out.println(getFormattedCommand());
		
				if(operator.equalsIgnoreCase("="))
				{
					if(checkSensor(deviceID) == databaseValue)
					{
						mqtt.publishToBroker("HomaTron", getFormattedID() + ":" + getFormattedCommand());
					}
				}
				
				if(operator.equalsIgnoreCase("<"))
				{
					if(checkSensor(deviceID) < databaseValue)
					{
						mqtt.publishToBroker("HomaTron", getFormattedID() + ":" + getFormattedCommand());
					}
				}
				
				if(operator.equalsIgnoreCase(">"))
				{
					if(checkSensor(deviceID) > databaseValue)
					{
						mqtt.publishToBroker("HomaTron", getFormattedID() + ":" + getFormattedCommand());
					}
				}
			} 
			catch (JSONException e) 
			{
				System.out.println("Ja dan gebeurt er maar niks");
			}				
		}
	}
	
	public void intFormatting(int ID, int Command)
	{
		
		String formattedID = String.format("%03d", ID);
		String formattedCommand = String.format("%03d", Command);
		
		setFormattedID(formattedID);
		setFormattedCommand(formattedCommand);
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
