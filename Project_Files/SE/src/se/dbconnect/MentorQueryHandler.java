package se.dbconnect;

import java.sql.ResultSet;
import se.mentor.MentorData;

public class MentorQueryHandler 
{
	private DatabaseConnection DatabaseConnectionobject;
	public MentorQueryHandler()
	{
		DatabaseConnectionobject = new DatabaseConnection();
	}
	public void addMentor(MentorData obj , String password) throws Exception
	{
		String query = "insert into mentor values('" + obj.EmployeeId + "' , ' " + obj.name + "' , '" + obj.Emailid + "' , '" + password + "')";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public ResultSet getMentorById(String MentorID) throws Exception
	{
		MentorID = MentorID.toUpperCase();
		String query = "select * from mentor where MentorId = '"+MentorID+"'";
		System.out.println(query);
		ResultSet rs=DatabaseConnectionobject.QueryRunner(query);
		return rs;
	}
	public void updateMentorPassword(String MentorId, String newPassword) throws Exception
	{
		String query = "UPDATE mentor SET MentorPassword = '"+newPassword+"' WHERE MentorId = '"+MentorId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void deleteMentor(String MentorId) throws Exception
	{
		String query = "DELETE from mentor where MentorId = '"+ MentorId +"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void checkId(String id) throws Exception
	{
		ResultSet rs = getMentorById(id);
		rs.next();
		String getid = rs.getString(1);
	}
	
	public String getPassword(String id) throws Exception
	{
		ResultSet rs = getMentorById(id);
		rs.next();
		String password = rs.getString(4);
		return password;
	}
}
