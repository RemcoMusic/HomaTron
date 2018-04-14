package HomaTron.Database;
import java.sql.*; 
import org.json.JSONObject;
import org.json.JSONArray;



import org.json.JSONException;
import org.skyscreamer.jsonassert.Customization;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.comparator.CustomComparator;

public class eventHandler 
{
	
	private databaseConnection foo = new databaseConnection();
	
	
	public eventHandler()
	{
		foo.pullDefaultData();;
		System.out.println("=====eventHandler=====");
		System.out.println("eventHandler: Dit is de eventHandler default data : " + foo.getDefaultData());
		System.out.println("=====eventHandler=====");
		
		receivedUpdate();
	}
	
	public void receivedUpdate()
	{
		while(true)
		{
			if(foo.checkForUpdate(true))
			{
				System.out.println("=====eventHandler=====");
				System.out.println("Dit is de eventHandler new data: " + foo.getNewData());
				System.out.println("=====eventHandler=====");
				System.out.println();
				System.out.println("=====eventHandler=====");
				System.out.println("Dit is de eventHandler default data: " + foo.getDefaultData());
				System.out.println("=====eventHandler=====");
				JSONArray actual = foo.getNewData();
				JSONArray expected = foo.getDefaultData();
			
				try 
				{
					JSONAssert.assertNotEquals(expected.getJSONObject(1), actual.getJSONObject(1), true);
					
					System.out.println(expected.getJSONObject(1).get("DeviceID"));
					System.out.println(actual.getJSONObject(1).get("DeviceID"));
					
					String fooo =  expected.getJSONObject(1).get("DeviceID").toString();
					String foooo = actual.getJSONObject(1).get("DeviceID").toString();
					
					if(fooo.equalsIgnoreCase(foooo))
					{
						System.out.println("Komt hier iets uberhaupt?");
						String x = actual.getJSONObject(1).get("Status").toString();
						System.out.println();
						System.out.println(x);
						
						if(x.equalsIgnoreCase("1"))
						{
							mqtt.publishToBroker("HomaTron", "00" + expected.getJSONObject(1).get("DeviceID") + ":003");
						}
						if(x.equalsIgnoreCase("0"))
						{
							mqtt.publishToBroker("HomaTron", "00" + expected.getJSONObject(1).get("DeviceID") + ":004");
						}
					}
					
					JSONObject test1 = new JSONObject();
					test1 = expected.getJSONObject(1);
					
					String test = test1.get("DeviceID").toString();
					
					System.out.println();
					System.out.println("Er is een actie gebeurd");
					System.out.println();
					System.out.println("Dit is de default id: " + test);
					System.out.println("Dit is de new id: " + actual.getJSONObject(1).get("DeviceID"));
				} 
				catch (JSONException e) 
				{
					System.out.println("Het is niet gelukt neem ik aan ?????");
					//e.printStackTrace();
				}		
			}
		}
	}
	
}
