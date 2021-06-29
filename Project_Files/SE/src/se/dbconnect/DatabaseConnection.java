package se.dbconnect;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;

public class DatabaseConnection 
{
	private String databasename = "orcl";
	private String databasePassword = "password";
	private int portAddress = 1521;
	private String username = "c##se";
	private String connectionUrl = "jdbc:oracle:thin:@localhost:"+portAddress+":"+databasename;
	Connection connectionObject;
	
	public ResultSet QueryRunner(String query) throws Exception
	{
		boolean status =  makeConnection();
		if(status)
		{
			try 
			{
				Statement stmt=connectionObject.createStatement();
				ResultSet rs=stmt.executeQuery(query);
				return rs;
			} 
			catch (SQLException e) 
			{
				closeConnection();
				e.printStackTrace();
				throw new SQLException();
			} 
		}
		else 
		{
			return null;
		}
	}
	private boolean makeConnection()
	{
		try 
		{
		   connectionObject = DriverManager.getConnection(connectionUrl , username , databasePassword);
		} 
		catch (SQLException e) 
		{
			return false;
		}
		
		return true;
	}
	private void closeConnection()
	{
		try 
		{
			connectionObject.close();
		} 
		catch (SQLException e) 
		{
			
		}
	}
}
