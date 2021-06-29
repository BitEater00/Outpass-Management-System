package se.dbconnect;

import java.sql.ResultSet;
import se.warden.WardenData;

public class WardenQueryHandler 
{
	private DatabaseConnection DatabaseConnectionobject;
	public WardenQueryHandler()
	{
		DatabaseConnectionobject = new DatabaseConnection();
	}
	public void addWarden(WardenData obj , String password) throws Exception
	{
		String query = "insert into warden values('" + obj.EmployeeId + "' , ' " + obj.name + "' , '" + obj.Emailid + "' , '" + password + "' , '" + obj.HostelId +"')";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public ResultSet getWardenById(String WardenID)  throws Exception
	{
		WardenID = WardenID.toUpperCase();
		String query = "select * from warden where WardenId = '"+WardenID+"'";
		System.out.println(query);
		ResultSet rs=DatabaseConnectionobject.QueryRunner(query);
		return rs;
	}
	public ResultSet getWardenByHostelId(String HostelID) throws Exception
	{
		HostelID = HostelID.toUpperCase();
		String query = "select * from warden where HostelId = '"+HostelID+"'";
		System.out.println(query);
		ResultSet rs=DatabaseConnectionobject.QueryRunner(query);
		return rs;
	}
	public void updateWardenPassword(String WardenId, String newPassword) throws Exception
	{
		String query = "UPDATE warden SET WardenPassword = '"+newPassword+"' WHERE WardenId = '"+WardenId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void updateWardenHostelId(String WardenId , String HostelId) throws Exception
	{
		String query = "UPDATE warden SET HostelId = '"+HostelId+"' WHERE WardenId = '"+WardenId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void deleteWarden(String WardenId) throws Exception
	{
		String query = "DELETE from warden where WardenId = '"+ WardenId +"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void checkId(String id) throws Exception
	{
		ResultSet rs = getWardenById(id);
		rs.next();
		String getid = rs.getString(1);
	}
	public String getPassword(String id) throws Exception
	{
		ResultSet rs = getWardenById(id);
		rs.next();
		String password = rs.getString(4);
		return password;
	}
}
