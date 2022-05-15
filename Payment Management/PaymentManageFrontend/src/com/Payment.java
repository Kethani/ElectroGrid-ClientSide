package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {
	
	private Connection connect() 
	{
		  Connection con = null;
		  
		     try {
			       Class.forName("com.mysql.cj.jdbc.Driver");

			      // Provide the correct details: DBServer/DBName, username, password
			      con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3308/electrimanage?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			       } 
		     catch (Exception e){
			       e.printStackTrace();
		     }
		          return con;
	         }
	
    //Insert Payments
	public String insertPayment(String date_time, String user_address, String amount, String payment_method) {
		    String output = "";
		    try {
			       Connection con = connect();
			       if (con == null) 
			       {
				      return "Error while connecting to the database for inserting.";
			       }
			
			// create a prepared statement
			String query = " insert into payment1(`paymnt_id`,`date_time`,`user_address`,`amount`,`payment_method`)" 
			+ " values (?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			     // binding values
			     preparedStmt.setInt(1, 0);
			     preparedStmt.setString(2, date_time);
			     preparedStmt.setString(3, user_address);
			     preparedStmt.setString(4, amount);
			     preparedStmt.setString(5, payment_method);

			     // execute the statement
			     preparedStmt.execute();
			     con.close();
			
			     String newPayment = readPayment();
			     output = "{\"status\":\"success\", \"data\": \"" +
			             newPayment + "\"}";

		} catch (Exception e) {
			  output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment.\"}";
			  System.err.println(e.getMessage());
		      }
		    
	   return output;
	   }
	
	//Read Payments
	public String readPayment() {
		
		    String output = "";
		    try {
			         Connection con = connect();
			         if (con == null) 
			         {
				           return "Error while connecting to the database for reading.";
			         }
			      
			              // Prepare the html table to be displayed
			              output = "<table border=\"1\">"
			              		+ "<tr>"
			              		+ "<th>Date & Time</th>"
			              		+ "<th>Address</th>"
			              		+ "<th>Amount</th>"
			              		+ "<th>Payment Method</th>"
			              		+ "<th>Update</th>"
			              		+ "<th>Remove</th></tr>";
			              
			             String query = "select * from payment1";
			             Statement stmt = (Statement) con.createStatement();
			             ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			             
			             // iterate through the rows in the result set
			             while (rs.next()) {
			            	 
				               String paymnt_id = Integer.toString(rs.getInt("paymnt_id"));
				               String date_time = rs.getString("date_time");
				               String user_address = rs.getString("user_address");
				               String amount = rs.getString("amount");
				               String payment_method = rs.getString("payment_method");
				               
				                 //Add row into the html table
				                 output += "<tr><td><input id='hidPayment_IDUpdate' name='hidPayment_IDUpdate' type='hidden' value='" + paymnt_id + "'>" + date_time + "</td>";
				                 output += "<td>" + user_address + "</td>";
				                 output += "<td>" + amount + "</td>";
				                 output += "<td>" + payment_method + "</td>";
				                 
				                 // buttons
				                 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
				                 + "<td><input name='btnRemove' type='button' value='Remove' "
				                 + "class='btnRemove btn btn-danger' data-paymnt_id='" + paymnt_id +"'>" + "</td></tr>";	                 
			}
			                    con.close();
			                    //Complete the html table
                                output += "</table>";
                                
		} catch (Exception e) {
			    output = "Error while reading the Payment.";
			    System.err.println(e.getMessage());
		}
		    
		return output;
	}
	
	//Update Payments
	public String updatePayment(String paymnt_id, String date_time, String user_address, String amount, String payment_method) 
	{
		    String output = "";
                 try 
                 {
			     Connection con = connect();
			           if (con == null) {
				               return "Error while connecting to the database for updating.";
			           }

			           // create a prepared statement
			           String query = "UPDATE payment1 SET date_time=?,user_address=?,amount=?,payment_method=? WHERE paymnt_id=?";
			           PreparedStatement preparedStmt = con.prepareStatement(query);

			           // binding values
			           preparedStmt.setString(1, date_time);
			           preparedStmt.setString(2, user_address);
			           preparedStmt.setString(3, amount);
			           preparedStmt.setString(4, payment_method);
			           preparedStmt.setInt(5, Integer.parseInt(paymnt_id));
			           
			           //Execute the statement
                       preparedStmt.execute();
			           con.close();

			           String newPayment = readPayment();
			           output = "{\"status\":\"success\", \"data\": \"" +
			                                   newPayment + "\"}";

			} catch (Exception e) {
			         output = "{\"status\":\"error\", \"data\":\"Error while inserting the payment.\"}";
			         System.err.println(e.getMessage());
			}
			
		return output;
	}
	
	//Update Payments 
    public String deletePayment(String paymnt_id) {

		String output = "";
		try {
			     Connection con = connect();
                 if (con == null) {
				          return "Error while connecting to the database for deleting.";
			              }
		
			     String query = "delete from payment1 where paymnt_id=?";
			     PreparedStatement preparedStmt = con.prepareStatement(query);

			     // binding values
			     preparedStmt.setInt(1, Integer.parseInt(paymnt_id));

			     // execute the statement
			     preparedStmt.execute();
			     con.close();

			     String newPayment = readPayment();
			     output = "{\"status\":\"success\", \"data\": \"" +
			                              newPayment + "\"}";

			} catch (Exception e) {
			        output = "{\"status\":\"error\", \"data\":"
			        		+ "\"Error while inserting the payment.\"}";
			        System.err.println(e.getMessage());
			}
		
		    return output;
	}
}



