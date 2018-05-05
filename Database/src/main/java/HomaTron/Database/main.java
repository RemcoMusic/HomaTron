package HomaTron.Database;

import org.json.JSONException;

public class main 
{
	
	private static databaseConnection test = new databaseConnection();
	//private static eventHandler eventHandler = new eventHandler();
	
	public static void main(String[] args) 
	{
		test.checkForUpdate(true);
	}

}
