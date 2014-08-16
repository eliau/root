package com.main;

import com.graphics.GraphicUseInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainProgram {


	private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	private static final String DB_URL = "jdbc:mysql://localhost/network";
	private static final String USER = "root";
	private static final String PASS = "melkor";
	private static Connection conn = null;
	private static Statement stmt = null;
	protected static ResultSet rs = null;
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
	
		new GraphicUseInterface().run();
		
		GraphicUseInterface.outStudent.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					String SQLQuery = "";
					Class.forName(JDBC_DRIVER);
					// create a query
					SQLQuery += "SELECT * FROM " + GraphicUseInterface.toQuery + " WHERE " + 
												   GraphicUseInterface.col + 
												   GraphicUseInterface.enterId.getText().toString();
					
					conn = DriverManager.getConnection(DB_URL, USER, PASS);
					stmt = conn.createStatement();
					rs = stmt.executeQuery(SQLQuery);
		        
					GraphicUseInterface.model.setDataSource(rs);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException se) {
					se.printStackTrace();
				}
				
			}
			
		}); 
	}

}
