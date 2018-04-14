package HomaTron.Database;
import java.sql.*; 
import org.json.JSONObject;
import org.json.JSONArray;

public class databaseConnection 
{
	private JSONArray defaultArray = new JSONArray();
	private JSONArray newArray = new JSONArray();
	
	private String Database = "HomaTron";
	private String DeviceID = "DeviceID";
	private String Status = "Online";
	
	public databaseConnection()
	{
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
				ResultSet rs=stmt.executeQuery("select * from status");  
				
				rs.next();
				if(rs.getInt("status") == 1)
				{
					pullTables();
					con.close(); 
					setUpdateCheck = false;
				}
				con.close();
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
			ResultSet rs = stmt.executeQuery("select * from device");  
	
			JSONArray array = new JSONArray();
			int deviceID;
			int status;
			
			while(rs.next()) 
			{ 
				JSONObject item = new JSONObject();
				deviceID = rs.getInt(DeviceID);
				status = rs.getInt(Status);
				
				item.put("DeviceID", deviceID);
				item.put("Status", status);
				array.put(item);
				
				//System.out.println(rs.getInt(1));  
			}
			
			
			String message = array.toString();
			
			System.out.println();
			System.out.println("databaseConnection: Inhoud van de new Array: " + message);
			
			setNewData(array);
			
			Statement stmt1 = con.createStatement();  
			stmt1.executeUpdate("UPDATE `HomaTron`.`status` SET `status`='0' WHERE `status`='1'");
			
			con.close();		
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
			ResultSet rs = stmt.executeQuery("select * from device");  
	
			JSONArray array = new JSONArray();
			int deviceID;
			int status;

			while(rs.next()) 
			{ 
				JSONObject item = new JSONObject();
				deviceID = rs.getInt(DeviceID);
				status = rs.getInt(Status);
				
				item.put("DeviceID", deviceID);
				item.put("Status", status);
				array.put(item);

				//System.out.println(rs.getInt(1));  
			}
			
			String message = array.toString();
			
			System.out.println("databaseConnection: Inhoud van de default Array: " + message);
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


