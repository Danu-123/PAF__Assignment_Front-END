package model;

import java.sql.*;

public class ElecAccount {
	
	public Connection connect() 
	{ 
	 Connection con = null; 
	 
	 try 
	 { 
	 Class.forName("com.mysql.jdbc.Driver"); 
	 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/elec","root",""); 
	 //For testing
	 System.out.print("Successfully connected"); 
	 } 
	 catch(Exception e) 
	 { 
	 e.printStackTrace(); 
	 } 
	 
	 return con; 
	}
    
	//Add an account
		public String addAccount(String Account_number, String Account_name,String Premises_id, String Cus_id )
		{
			String output="";
			
			try {
				Connection con=connect();
				if(con==null) {
					return "Error while connecting to the database";
				}
				//create a prepared statement
				String query = " insert into electricity_account(`Account_number`,`Account_name`,`Premises_id`,`Cus_id`)"
						 + " values (?, ?, ? , ?)"; 
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				//binding values
				
				preparedStmt.setInt(1,Integer.parseInt(Account_number));
				//preparedStmt.setString(1, Account_number);
				preparedStmt.setString(2, Account_name);
				preparedStmt.setString(3, Premises_id);
				preparedStmt.setString(4, Cus_id);
				
				
				
				//execute the statement
				preparedStmt.execute();
				con.close();
				
				String newAccounts = readElecAccounts(); 
	        	output = "{\"status\":\"success\", \"data\": \"" +newAccounts + "\"}"; 
				
				
			}
			catch(Exception e)
			{
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the account.\"}"; 
				System.err.println(e.getMessage());
			}
			return output;
		}
	    
		//update an account
			public String updateAccount(String AccID,String Account_number, String Account_name, String Premises_id, String Cus_id)
			{
				String output="";
				try
				{
					Connection con = connect();
					if(con==null)
					{
						return "Error while connecting to the database for updating.";
						
					}
					//create a prepared statement
					String query = "UPDATE electricity_account SET Account_number=?, Account_name=?,Premises_id=?, Cus_id=? WHERE AccID=?";
					
					PreparedStatement preparedStmt = con.prepareStatement(query);
					
					//binding values
					
					preparedStmt.setInt(1,Integer.parseInt(Account_number));
					preparedStmt.setString(2, Account_name);
					preparedStmt.setString(3, Premises_id);
					preparedStmt.setString(4, Cus_id);
					//preparedStmt.setString(4, Account_number);
					//preparedStmt.setInt(4,Integer.parseInt(Account_number));
					preparedStmt.setInt(5, Integer.parseInt(AccID)); 
					
					//execute the statement
					preparedStmt.executeUpdate();
					con.close();
					
					String newAccounts = readElecAccounts();
					
					output = "{\"status\":\"success\", \"data\": \"" + newAccounts + "\"}"; 
				}
				catch(Exception e)
				{
					output="{\"status\":\"error\", \"data\": \"Error while updating the account.\"}"; 
					System.err.println(e.getMessage());
				}
				return output;
			}
	    
			//read an account
			public String readElecAccounts()
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {return "Error while connecting to the database for reading."; }
			 
			 // Prepare the html table to be displayed
			 output = "<table border='1' class='table'><tr><th>Account_number</th><th>Account_name</th>" +
			 "<th>Premises_id</th>" +
			 "<th>Cus_id</th>"+
			 "<th>Update</th><th>Remove</th></tr>";

			 String query = "select * from electricity_account";
			 Statement stmt = con.createStatement();
			 ResultSet rs = stmt.executeQuery(query);
			 
			 // iterate through the rows in the result set
			 while (rs.next())
			 {
			 String AccID = Integer.toString(rs.getInt("AccID"));
			 String Account_number = Integer.toString(rs.getInt("Account_number"));
			 //String Account_number = rs.getString("Account_number");
			 String Account_name = rs.getString("Account_name");
			 String Premises_id = rs.getString("Premises_id");
			 String Cus_id=rs.getString("Cus_id");

			 // Add into the html table
			 output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate' type='hidden' value='"+AccID+"'>" + Account_number + "</td>";
			 output += "<td>" + Account_name + "</td>";
			 output += "<td>" + Premises_id + "</td>";
			 output +="<td>" + Cus_id + "</td>";
			 
			 // buttons
			
			 output += "<td><input name='btnUpdate' type='button' value='Update' "
       				 + "class='btnUpdate btn btn-secondary' data-accid='" + AccID + "'></td>"
       				 + "<td><input name='btnRemove' type='button' value='Remove' "
       				 + "class='btnRemove btn btn-danger' data-accid='" + AccID + "'></td></tr>";
        				
			  
			 }
			 con.close();
			 // Complete the html table
			 output += "</table>";
			 }
			 catch (Exception e)
			 {
			 output = "Error while reading an account.";
			 System.err.println(e.getMessage());
			 }
			 return output; 
			 }
	    
			//delete an account
			public String deleteAccount(String AccID) {
			
				String output="";
				try {
					Connection con = connect();
					if(con==null) {
						return "Error while connecting to the database for deleting";}
						
						//create prepared statement
						String query="delete from electricity_account where AccID=?";
						PreparedStatement preparedStmt=con.prepareStatement(query);
						
						//binding values
						preparedStmt.setInt(1,Integer.parseInt(AccID));
						//preparedStmt.setString(1,Account_number);
						
						//execute the statement
						preparedStmt.execute();
						con.close();
						
						String newAccounts = readElecAccounts(); 
			        	 output = "{\"status\":\"success\", \"data\": \"" +newAccounts + "\"}"; 
						
				}
				catch(Exception e)
				{
					output="{\"status\":\"error\", \"data\": \"Error while deleting the account.\"}"; 
					System.err.println(e.getMessage());
				}
				return output;
			}
}
