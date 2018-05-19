package HomaTron.Database;

import org.json.JSONException;

public class main 
{
	
	private static databaseConnection server = new databaseConnection();	
	
	public static void main(String[] args) 
	{
		server.checkForUpdate(true);
	}

}
