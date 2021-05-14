package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Beneficiary {
	
	//A common method to connect to the DB
		private Connection connect()
		{
			Connection con = null;
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				
				//Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadget_db", "root", "");
			}
			
			catch (Exception e)
			{e.printStackTrace();}
			
			return con;
		}
		
		public String insertInventor(String user, String age, String address, String password)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for inserting."; }
				// create a prepared statement
				String query = " insert into beneficiares(`inventorID`,`inventorUsername`,`inventorAge`,`inventorAddress`,`inventorPassword`)"
						+ " values (?, ?, ?, ?, ?)";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, user);
				preparedStmt.setString(3, age);
				preparedStmt.setString(4, address);
				preparedStmt.setString(5, password);
				
				// execute the statement3
				preparedStmt.execute();
				con.close();
				output = "Inserted successfully";
				
				String newInventors = readInventors();
				output = "{\"status\":\"success\", \"data\": \"" +
						newInventors + "\"}";
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while inserting the inventor.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
		public String readInventors()
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for reading."; }
				// Prepare the html table to be displayed
				output = "<table border='1'><th>Inventor Username</th>" +
						"<th>Inventor Age</th>" +
						"<th>Inventor Address</th>" +
						"<th>Inventor Password</th>" +
						"<th>Update</th><th>Remove</th></tr>";
				String query = "select * from beneficiares";
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				
				// iterate through the rows in the result set
				while (rs.next())
				{
					String inventorID = Integer.toString(rs.getInt("inventorID"));
					String inventorUsername = rs.getString("inventorUsername");
					String inventorAge = rs.getString("inventorAge");
					String inventorAddress = rs.getString("inventorAddress");
					String inventorPassword = rs.getString("inventorPassword");
					
					// Add into the html table
					output += "<tr><td><input id='hidInventorIDUpdate' name='hidInventorIDUpdate' type='hidden' value='" + inventorID + "'>"
							+ inventorUsername + "</td>";
					output += "<td>" + inventorAge + "</td>";
					output += "<td>" + inventorAddress + "</td>";
					output += "<td>" + inventorPassword + "</td>";
					
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' "+ "class='btnUpdate btn btn-secondary' data-inventorid='" + inventorID + "'></td>"
							+"<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-inventorid='" + inventorID + "'>" +"</td></tr>";
				}
				
				// Complete the HTML table
				con.close();
				output += "</table>";
				
			}
			catch (Exception e)
			{
				output = "Error while reading the inventors.";
				System.err.println(e.getMessage());
			}
				return output;
		}
		
		public String updateInventor(String id, String user, String age, String address, String password)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				
				// create a prepared statement
				String query = "UPDATE beneficiares SET inventorUsername=?,inventorAge=?,inventorAddress=?,inventorPassword=? WHERE inventorID=?";
				
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				
				preparedStmt.setString(1, user);
				preparedStmt.setString(2, age);
				preparedStmt.setString(3, address);
				preparedStmt.setString(4, password);
				preparedStmt.setInt(5, Integer.parseInt(id));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newInventors = readInventors();
				output = "{\"status\":\"success\", \"data\": \"" +
						newInventors + "\"}";
				
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while updating the inventors.\"}";
				System.err.println(e.getMessage());
				
			}
			return output;
		}
		
		public String deleteInventor(String inventorID)
		{
			String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for deleting."; }
				
				// create a prepared statement
				String query = "delete from beneficiares where `inventorID`=?;";
				PreparedStatement preparedStmt = con.prepareStatement(query);
				
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(inventorID));
				
				// execute the statement
				preparedStmt.execute();
				con.close();
				
				String newInventors = readInventors();
				output = "{\"status\":\"success\", \"data\": \"" +
						newInventors + "\"}";
				
		
			}
			catch (Exception e)
			{
				output = "{\"status\":\"error\", \"data\":\"Error while deleting the inventor.\"}";
				System.err.println(e.getMessage());
			}
			return output;
		}
		
}
