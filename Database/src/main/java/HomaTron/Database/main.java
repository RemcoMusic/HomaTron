package HomaTron.Database;

import org.json.JSONException;

public class main 
{
	
	private static databaseConnection test = new databaseConnection();	
	
	public static void main(String[] args) 
	{
		test.checkForUpdate(true);
	}

}
