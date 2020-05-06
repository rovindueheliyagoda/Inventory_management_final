package com;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;

public class inventory {

	public Connection connect() {

		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/inventory_management", "root", "");
			// For testing
			System.out.println("Successfully connected---1");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	// inserting an product .........................

	public String insertProduct(String code, String name, String price, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database";
			}
			// create a prepared statement
			String query = "insert into product" + "(`productID`,`productCode`,`productName`,`productPrice`,`productDesc`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc);

			// execute the statement
			preparedStmt.execute();
//		 System.out.print("Successfully inserted");
			con.close();
			
			
			String newProduct = readProduct();
			output = "{\"status\":\"success\", \"data\": \"" +
			newProduct + "\"}";
			
			//output = "Inserted successfully";
			
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the product.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

	// read the product from database and display----------------------

	public String readProduct()
	{
	String output = "";
	try
	{
	Connection con = connect();
	if (con == null)
	{
	return "Error while connecting to the database for reading.";
	}
	// Prepare the html table to be displayed
	output = "<table border='1'><tr><th>Product Code</th>"
			+ "<th>Product Name</th><th>Product Price</th>"
			+"<th>Product Description</th>"
			+ "<th>Update</th><th>Remove</th></tr>";
	String query = "select * from product";
	Statement stmt = con.createStatement();
	ResultSet rs = stmt.executeQuery(query);
	// iterate through the rows in the result set
	while (rs.next())
	{
	String productID = Integer.toString(rs.getInt("productID"));
	String productCode = rs.getString("productCode");
	String productName = rs.getString("productName");
	String productPrice = Double.toString(rs.getDouble("productPrice"));
	String productDesc = rs.getString("productDesc");
	
	
	// Add into the html table
	output += "<tr><td><input id='hidProductIDUpdate'"
			+ "name='hidProductIDUpdate' type='hidden'"
			+ "value='" + productID + "'>" + productCode + "</td>";
	output += "<td>" + productName + "</td>";
	output += "<td>" + productPrice + "</td>";
	output += "<td>" + productDesc + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button'"
			+ "value='Update'"
			+ "class='btnUpdate btn btn-secondary'></td>"
			+ "<td><input name='btnRemove' type='button'"
			+ "value='Remove'"
			+ "class='btnRemove btn btn-danger' data-productid='"
	+ productID + "'>" + "</td></tr>";
	}
	con.close();
	// Complete the html table
	output += "</table>";
	}
	catch (Exception e)
	{
	output = "Error while reading the product.";
	System.err.println(e.getMessage());
	}
	return output;
	}
	
	
	
	
	
	
	// update product ---------------------------------------------

	public String updateProduct(String ID, String code, String name, String price, String desc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE product SET productCode=?,productName=?,productPrice=?,productDesc=? WHERE productID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, name);
			preparedStmt.setDouble(3, Double.parseDouble(price));
			preparedStmt.setString(4, desc);
 			preparedStmt.setInt(5, Integer.parseInt(ID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			
			String newProduct = readProduct();
			output = "{\"status\":\"success\", \"data\": \"" +
			newProduct + "\"}";
			
			//output = "Updated successfully";
			
			
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the product.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

	// delete product--------------------------------------

	public String deleteProduct(String productID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from items where productID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(productID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newProduct = readProduct();
			output = "{\"status\":\"success\", \"data\": \"" +
			newProduct + "\"}";
			
			//output = "Deleted successfully";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the product.\"}";
					System.err.println(e.getMessage());
		}
		return output;
	}

}
