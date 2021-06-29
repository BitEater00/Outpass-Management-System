package se.dbconnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import se.Student.StudentData;

public class StudentQueryHandler 
{
	private DatabaseConnection DatabaseConnectionobject;
	
	public StudentQueryHandler()
	{
		DatabaseConnectionobject = new DatabaseConnection();
	}
	public ResultSet getStudentById(String id) throws Exception
	{
		id = id.toUpperCase();
		String query = "select * from student where studentid = '"+id+"'";
		System.out.println(query);
		ResultSet rs=DatabaseConnectionobject.QueryRunner(query);
		return rs;
	}
	public ResultSet getStudentbyHostel(String HostelId) throws Exception
	{
		HostelId = HostelId.toUpperCase();
		String query = "select * from student where hostelid = " + "'"+HostelId+"'";
		System.out.println(query);
		ResultSet rs=DatabaseConnectionobject.QueryRunner(query);
		return rs;
	}
	public ResultSet getStudentbyMentor(String MentorId) throws Exception
	{
		MentorId = MentorId.toUpperCase();
		String query = "select * from student where mentorid = " + "'"+MentorId+"'";
		System.out.println(query);
		ResultSet rs=DatabaseConnectionobject.QueryRunner(query);
		return rs;
	}
	public void addStudent(StudentData obj , String Password) throws Exception
	{
		String query = "insert into student values('" + obj.Id +"' , '"+ obj.Name +"' , '"+ obj.gender+"' , '"+obj.Email+"' , '"+Password+"' , 0 ,  0 , " + obj.RoomNumber + 
				", '" + obj.MentorDetails.EmployeeId + "','" + obj.HostelName + "')";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void checkId(String id) throws Exception
	{
		ResultSet rs = getStudentById(id);
		rs.next();
		String getid = rs.getString(1);
	}
	public String getPassword(String id) throws Exception
	{
		ResultSet rs = getStudentById(id);
		rs.next();
		String password = rs.getString(5);
		return password;
	}
	public void updatePassword(String id , String newPassword)  throws Exception
	{
		String query = "UPDATE student SET StudentPassword = '"+newPassword+"' WHERE StudentId = '"+id+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void updateMentor(String StudentId , String newMentor) throws Exception
	{
		String query = "UPDATE student SET MentorId = '"+newMentor+"' WHERE StudentId = '"+StudentId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void updateHostel(String StudentId , String HostelId) throws Exception
	{
		String query = "UPDATE student SET HostelId = '"+HostelId+"' WHERE StudentId = '"+StudentId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void updatetimeslate(String StudentId) throws Exception
	{
		ResultSet rs = DatabaseConnectionobject.QueryRunner("select late from student where studentid = '"+StudentId+"'");
		rs.next();
		int newcount =  rs.getInt(1) + 1;
		String query = "UPDATE student SET late = '"+newcount+"' WHERE StudentId = '"+StudentId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void updatestatus(String StudentId , int choice) throws Exception
	{
		String query = "UPDATE student SET status = '"+choice+"' WHERE StudentId = '"+StudentId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
}
