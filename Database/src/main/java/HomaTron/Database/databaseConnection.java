package HomaTron.Database;
import java.sql.*; 
import org.json.JSONObject;
import org.json.JSONArray;

public class databaseConnection 
{
	private boolean setUpdateCheck = true;
	
	public databaseConnection()
	{
		//Constructor
	}
	
	public void checkForUpdate() //This method will check if there is an update in the Database
	{
		try
		{  
			
			while(setUpdateCheck)
			{
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://83.86.242.110:3306/webapp","HomaTron","HomaTron");  
			
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
	}
	
	public void pullTables() //This method will pull the new data from the Database when this is updated
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://83.86.242.110:3306/webapp","HomaTron","HomaTron");  
	
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from outputdevice");  
	
			while(rs.next()) 
			{ 
				System.out.println(rs.getInt(1));  
			}
			
			Statement stmt1 = con.createStatement();  
			int rs1 = stmt1.executeUpdate("UPDATE `Test`.`status` SET `status`='0' WHERE `idstatus`='1'");
			
			setUpdateCheck = true;
			checkForUpdate();
			con.close();		
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
	}
	
	public void setDefaultData() //This method will set the default data on startup to check the differents
	{
		try
		{ 
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://83.86.242.110:3306/webapp","HomaTron","HomaTron");  
	
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from outputdevice");  
	
			JSONArray array = new JSONArray();
			int deviceID;
			int status;

			while(rs.next()) 
			{ 
				JSONObject item = new JSONObject();
				deviceID = rs.getInt("OutputDeviceID");
				status = rs.getInt("Online");
				
				item.put("DeviceID", deviceID);
				item.put("Status", status);
				array.put(item);

				//System.out.println(rs.getInt(1));  
			}
			
			String message = array.toString();
			
			System.out.println("Inhoud van de Array: " + message);
			
			System.out.println(array.getJSONObject(0).get("DeviceID"));
			
			
			con.close();
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
	}

}


