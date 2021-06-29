package se.dbconnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import se.request.RequestData;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;  

public class RequestQueryHandler 
{
	private DatabaseConnection DatabaseConnectionobject;
	public RequestQueryHandler()
	{
		DatabaseConnectionobject = new DatabaseConnection();
	}
	public ResultSet getRequestById(String id) throws Exception
	{ 
		id = id.toUpperCase();
		String query = "select * from request where RequestID = '"+id+"'";
		System.out.println(query);
		ResultSet rs=DatabaseConnectionobject.QueryRunner(query);
		return rs;
	}
	public ResultSet getRequestbyHostel(String HostelId)  throws Exception
	{ 
		HostelId = HostelId.toUpperCase();
		String query = "select * from RequestHostel where hostelId = '"+HostelId+"'";
		System.out.println(query);
		return DatabaseConnectionobject.QueryRunner(query);
	}
	public ResultSet getRequestbyMentor(String MentorId) throws Exception
	{ 
		MentorId = MentorId.toUpperCase();
		String query = "select * from Requestmentor where mentorId = '"+MentorId+"'";
		System.out.println(query);
		return DatabaseConnectionobject.QueryRunner(query);
	}
	public void addRequest(RequestData obj) throws Exception
	{
		String todate = new SimpleDateFormat("dd-MM-yyyy").format(obj.toDate);
		String fromdate = new SimpleDateFormat("dd-MM-yyyy").format(obj.fromDate);
		String query = "insert into request(requestid,studentid,destination,fromdate,todate,intime,outtime,internalcomment,status,requesttype) "
				+ "values ('"+obj.RequestId+"','"+obj.StudentId+"','"+obj.Destination+"',TO_DATE('"+fromdate+"' , 'dd-mm-yyyy'),TO_DATE('"+todate+"' , 'dd-mm-yyyy'),"
				+ "NULL,NULL,NULL,1,"+obj.RequestType+")";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void updateRequestbyMentor(String RequestId , int choice) throws Exception 
	{
		RequestId = RequestId.toUpperCase();
		String query = "UPDATE request SET status = '"+choice+"' WHERE RequestId = '"+RequestId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void updateRequestbyWarden(String RequestId, int choice , String comment) throws Exception
	{
		RequestId = RequestId.toUpperCase();
		String query = "UPDATE request SET status = '"+choice+"' WHERE RequestId = '"+RequestId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
		
		query = "UPDATE request SET internalcomment = '"+comment+"' WHERE RequestId = '"+RequestId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void deleteRequest(String RequestId) throws Exception 
	{
		RequestId = RequestId.toUpperCase();
		String query = "DELETE from request where requestId = '"+ RequestId +"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public void updateIntime(String RequestId , Date time) throws Exception 
	{
		RequestId = RequestId.toUpperCase();
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");  
        String strDate = dateFormat.format(time);
        String query = "UPDATE request SET intime = '"+strDate+"' WHERE RequestId = '"+RequestId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
        
	}
	public void updateOuttime(String RequestId, Date time) throws Exception
	{
		RequestId = RequestId.toUpperCase();
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");  
        String strDate = dateFormat.format(time);
        String query = "UPDATE request SET outtime = '"+strDate+"' WHERE RequestId = '"+RequestId+"'";
		System.out.println(query);
		DatabaseConnectionobject.QueryRunner(query);
	}
	public ResultSet getAllApprovedRequest() throws Exception
	{
		String query = "select * from request where status = 6";
		System.out.println(query);
		return DatabaseConnectionobject.QueryRunner(query);
	}
	public ResultSet getRequestbyStudent(String StudentId) throws Exception
	{
		StudentId = StudentId.toUpperCase();
		String query = "select * from request where studentId = '" + StudentId + "'";
		System.out.println(query);
		return DatabaseConnectionobject.QueryRunner(query);
	}
	public void checkId(String Id) throws Exception
	{
		ResultSet rs = getRequestById(Id);
		rs.next();
		String getid = rs.getString(1);
	}
}
