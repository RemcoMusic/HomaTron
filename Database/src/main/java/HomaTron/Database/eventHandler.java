package HomaTron.Database;
import java.sql.*; 
import org.json.JSONObject;
import org.json.JSONArray;

public class eventHandler 
{
	
	private databaseConnection foo = new databaseConnection();
	
	
	public eventHandler(){}
	
	public void test()
	{
		foo.setDefaultData();
		System.out.println("Dit is de eventHandler: " + foo.getDefaultData());
	}
	
}
