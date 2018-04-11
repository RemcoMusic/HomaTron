public class main 
{
	
	private static databaseConnection test = new databaseConnection();

	public static void main(String[] args) 
	{
		test.setDefaultData();
		
		test.checkForUpdate();

	}

}
