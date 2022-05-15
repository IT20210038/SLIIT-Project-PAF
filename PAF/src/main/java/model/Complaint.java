package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Complaint {

	private Connection connect() {
		Connection con = null;
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
			 //Provide the correct details: DBServer/DBName, username, password 
			 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/approvement", "root", "root");

			//For testing          
			 System.out.print("Successfully connected");
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con; 
	} 
	
	public String readComplaint() {
		String output= "";
		
		try {
			Connection con = connect();
			if(con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			//prepare the html table to be displayed
			output = "<table border='1'><tr><th>complaintCategory</th>"
					+"<th>complaintType</th>"
					+"<th>accountNo</th>"
					+"<th>name</th>"
					+"<th>mobileno</th>"
					+"<th>address</th>"
					+ "<th>Update</th><th>Remove</th></tr>";

			String query ="select * from complaint";
			Statement stmt = con.createStatement();   
			ResultSet rs = stmt.executeQuery(query); 
			
			 // iterate through the rows in the result set   
			  while (rs.next())   { 
				  String complaintId = Integer.toString(rs.getInt("complaintId"));
				  String complaintCategory = rs.getString("complaintCategory");
				  String complaintType = rs.getString("complaintType");
				  String accountNo = rs.getString("accountNo");
				  String name = rs.getString("name");
				  String mobileno =rs.getString("mobileno");
				  String address = rs.getString("address");
				  
				  // Add into the html table    

				  output += "<tr><td><input id='hidApp_IDUpdate' name='hidApp_IDUpdate' type='hidden' value='" + complaintId + "'>" + complaintCategory + "</td>"; 

				  output += "<td>" + complaintType + "</td>";
				  output += "<td>" + accountNo + "</td>";
				  output += "<td>" + name + "</td>";
				  output += "<td>" + mobileno + "</td>";
				  output += "<td>" + address + "</td>";
				  
				// buttons     
				  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					  		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-complaintId='"+ complaintId +"'>"+"</td></tr>";

				} 
			  	con.close(); 

			  // Complete the HTML tables
			  output += "</table>"; 

			  }
			catch (Exception e) {  
				output = "Error while reading the complaint.";  
				System.err.println(e.getMessage()); 
			}
		return output;
		}
	
		
	//Insert complaint
	public String insertComplaint(String complaintCategory,String complaintType,String accountNo,String name,String mobileno,String address) {
		String output = "";
		
		try {
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database";
			}
			
			//create a prepared statement
			String query = "insert into complaint (`complaintId`,`complaintCategory`,`complaintType`,`accountNo`,`name`,`mobileno`,`address`)" + "values(?,?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values 
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, complaintCategory);
			preparedStmt.setString(3, complaintType);
			preparedStmt.setString(4, accountNo);
			preparedStmt.setString(5,name);
			preparedStmt.setString(6,mobileno);
			preparedStmt.setString(7,address);
			
			//execute the statement   
			preparedStmt.execute();   
			con.close(); 
			
			//Create JSON Object to show successful msg.
			String newComplaint = readComplaint();
			output = "{\"status\":\"success\", \"data\": \"" + newComplaint + "\"}";
		
					
		}catch (Exception e) {  
			//Create JSON Object to show Error msg.
			output = "{\"status\":\"error\", \"data\": \"Error while Inserting complaint.\"}";   
			System.err.println(e.getMessage());  
		} 
		return output; 
	}
	
	//Update complaint
	public String updateComplaint(String complaintId,String complaintCategory,String complaintType,String accountNo,String name,String mobileno,String address) {
		String output = ""; 
		 
		  try   {   
			  Connection con = connect();
		 
			  if (con == null)    {
				  return "Error while connecting to the database for updating."; 
			  } 
			  // create a prepared statement    
		   String query = "UPDATE complaint SET complaintCategory=?,complaintType=?,accountNo=?,name=?,mobileno=?,address=?WHERE complaintId=?";
				 
		   PreparedStatement preparedStmt = con.prepareStatement(query); 
		   
		   // binding values    
		    preparedStmt.setString(1, complaintCategory);
			preparedStmt.setString(2,complaintType);
			preparedStmt.setString(3, accountNo);
			preparedStmt.setString(4,name);
			preparedStmt.setString(5,mobileno);
			preparedStmt.setString(6,address);
			preparedStmt.setInt(7, Integer.parseInt(complaintId));
			
			 // execute the statement    
			 preparedStmt.execute();    
			 con.close(); 
			 
			 //create JSON object to show successful msg
			   String newComplaint = readComplaint();
			   output = "{\"status\":\"success\", \"data\": \"" + newComplaint + "\"}";
			   }   catch (Exception e)   {    
				   output = "{\"status\":\"error\", \"data\": \"Error while Updating complaint Details.\"}";      
				   System.err.println(e.getMessage());   
			   } 
		  return output; 
		 
	}
	
	public String deleteComplaint(String complaintId) {  
		String output = ""; 
	 
	 try  {   
		 Connection con = connect();
	 
	  if (con == null)   {    
		  return "Error while connecting to the database for deleting.";   
	  } 
	 
	  // create a prepared statement   
	  String query = "DELETE FROM complaint WHERE complaintId=?"; 
	 
	  PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	  // binding values   
	  preparedStmt.setInt(1, Integer.parseInt(complaintId));       
	  // execute the statement   
	  preparedStmt.execute();   
	  con.close(); 
	 
	  //create JSON Object
	  //String newComplaint = readComplaint();
	 // output = "{\"status\":\"success\", \"data\": \"" + newComplaint + "\"}";
	  }  catch (Exception e)  {  
		  //Create JSON object 
		  output = "{\"status\":\"error\", \"data\": \"Error while deleting complaint.\"}";
		  System.err.println(e.getMessage());  
		  
	 } 
	 
	 return output; 
	 }
	
}
