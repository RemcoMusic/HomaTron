import java.sql.*; 


public class databaseConnection 
{
	private boolean foo = true;
	
	public databaseConnection()
	{
		
	}
	
	public void checkForUpdate()
	{
		try
		{  
			
			while(foo)
			{
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://192.168.178.45:3306/Test","HomaTron","HomaTron");  
			
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery("select * from status");  
			
				/*while(rs.next()) 
				{ 
					System.out.println(rs.getInt(2));  
				}*/
				rs.next();
				if(rs.getInt("status") == 1)
				{
					pullTables();
					con.close(); 
					foo = false;
				}
				con.close();
			}
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
	}
	
	public void pullTables()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://192.168.178.45:3306/Test","HomaTron","HomaTron");  
	
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from outputdevice");  
	
			while(rs.next()) 
			{ 
				System.out.println(rs.getInt(1));  
			}
			
			Statement stmt1 = con.createStatement();  
			int rs1 = stmt1.executeUpdate("UPDATE `Test`.`status` SET `status`='0' WHERE `idstatus`='1'");
			
			foo = true;
			checkForUpdate();
			con.close();		
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
	}
	
	public void setDefaultData()
	{
		try
		{
			int deviceID;
			int status; 

			int deviceID2;
			int status2; 
			
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://192.168.178.45:3306/Test","HomaTron","HomaTron");  
	
			Statement stmt = con.createStatement();  
			ResultSet rs = stmt.executeQuery("select * from outputdevice");  
	
			/*while(rs.next()) 
			{ 
				System.out.println(rs.getInt(1));  
			}*/
			
			rs.next();
			deviceID = rs.getInt("OutputDeviceID");
			status = rs.getInt("Online");
			rs.next();
			deviceID2 = rs.getInt("OutputDeviceID");
			status2 = rs.getInt("Online");
			
			System.out.println("Het Device ID = " + deviceID);
			System.out.println("De status van de Device = " + status);
			
			System.out.println("Het Device ID = " + deviceID2);
			System.out.println("De status van de Device = " + status2);
			
			
			con.close();
		}
		catch(Exception e)
		{ 
			System.out.println(e);
		}  
	}

}


