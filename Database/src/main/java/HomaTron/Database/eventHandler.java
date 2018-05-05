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
				System.out.println("blabla");
			}

		}
	}
	
}
