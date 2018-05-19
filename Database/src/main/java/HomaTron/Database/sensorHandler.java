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
	
	public sensorHandler()
	{	
	}
	
	public int checkSensor(int deviceID)
	{	
		formatSensorID(deviceID);
		mqtt.checkSensor(getFormattedSensorID());
		System.out.println("Result: " + value);	 
		return value;		
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
	}
	
	public void formatSensorID(int ID)
	{
		String formattedID = String.format("%03d", ID);
		setFormattedSensorID(formattedID);
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
