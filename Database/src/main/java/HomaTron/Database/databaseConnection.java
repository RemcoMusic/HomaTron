package HomaTron.Database;
import java.sql.*; 
import org.json.JSONObject;
import org.json.JSONArray;

public class databaseConnection 
{
	private sensorHandler Handler = new sensorHandler();
	
	private JSONArray defaultArray = new JSONArray();
	private JSONArray newArray = new JSONArray();
	
	private String Database = "HomaTron";
	
	public databaseConnection()
	{
		pullDefaultData();
	}
	
	public boolean checkForUpdate(boolean setUpdateCheck) //This method will check if there is an update in the Database
	{		
		try
		{  
			
			while(setUpdateCheck)
			{
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://83.86.242.110:3306/" + Database,"HomaTron","HomaTron");  
			
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("select * from HomaTron.status");  
				
				rs.next();
				if(rs.getInt("status") == 1)
				{
					pullTables();
					con.close(); 
					setUpdateCheck = false;
					System.out.println(rs.getInt("status"));
				}
				con.close();
				Handler.checkValueWithSensor(getDefaultData());
				
				
				try {
					//System.out.println("Sleeping");
					Thread.sleep(500);		
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				
								
			}
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		} 
		return true;
	}
	
	public void pullTables() //This method will pull the new data from the Database when this is updated
	{	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://83.86.242.110:3306/" + Database,"HomaTron","HomaTron");  
	
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from smartrow");  
	
			JSONArray array = new JSONArray();
			int rowID;
			String operator;
			int inputValue;
			int outputCommand;
			int outputDeviceID;
			int inputDeviceID;
			
			while(rs.next()) 
			{ 
				JSONObject item = new JSONObject();
				rowID = rs.getInt("RowID");
				operator = rs.getString("operator");
				inputValue = rs.getInt("inputValue");
				outputCommand = rs.getInt("outputValue");
				outputDeviceID = rs.getInt("outputdevice_ID");
				inputDeviceID = rs.getInt("inputdevice_ID");
				
				item.put("rowID", rowID);
				item.put("operator", operator);
				item.put("inputValue", inputValue);
				item.put("outputCommand", outputCommand);
				item.put("outputDeviceID", outputDeviceID);
				item.put("inputDeviceID", inputDeviceID);
				array.put(item);
			}
			
			
			String message = array.toString();
			
			System.out.println();
			System.out.println("=====Database Connection=====");
			System.out.println("databaseConnection: Inhoud van de new Array: " + message);
			System.out.println("=====Database Connection=====");
			System.out.println();
			
			setNewData(array);
			setDefaultData(array);
			
			Statement stmt1 = con.createStatement();  
			stmt1.executeUpdate("UPDATE `HomaTron`.`status` SET `status`='0' WHERE `status`='1'");
			
			con.close();
			checkForUpdate(true);
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		} 
	}
	
	public void pullDefaultData() //This method will set the default data on startup to check the differents
	{
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://83.86.242.110:3306/" + Database,"HomaTron","HomaTron");  
	
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from smartrow");  
	
			JSONArray array = new JSONArray();
			int rowID;
			String operator;
			int inputValue;
			int outputCommand;
			int outputDeviceID;
			int inputDeviceID;
			
			while(rs.next()) 
			{ 
				JSONObject item = new JSONObject();
				rowID = rs.getInt("RowID");
				operator = rs.getString("operator");
				inputValue = rs.getInt("inputValue");
				outputCommand = rs.getInt("outputValue");
				outputDeviceID = rs.getInt("outputdevice_ID");
				inputDeviceID = rs.getInt("inputdevice_ID");
				
				item.put("rowID", rowID);
				item.put("operator", operator);
				item.put("inputValue", inputValue);
				item.put("outputCommand", outputCommand);
				item.put("outputDeviceID", outputDeviceID);
				item.put("inputDeviceID", inputDeviceID);
				array.put(item);  
			}
			
			String message = array.toString();
			
			System.out.println();
			System.out.println("=====Database Connection=====");
			System.out.println("databaseConnection: Inhoud van de default Array: " + message);
			System.out.println("=====Database Connection=====");
			System.out.println();
			
			setDefaultData(array);
			
			con.close();
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		} 
	}
	
	public void setDefaultData(JSONArray array)
	{
		this.defaultArray = array;
	}
	
	public JSONArray getDefaultData()
	{
		return this.defaultArray;
	}
	
	public void setNewData(JSONArray array)
	{
		this.newArray = array;
	}
	
	public JSONArray getNewData()
	{
		return this.newArray; 
	}
}


