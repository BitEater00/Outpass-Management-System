package se.request;

import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import se.Student.Student;
import se.Student.StudentData;
import se.dbconnect.RequestQueryHandler;
import se.mentor.Mentor;
import se.warden.Warden;

public class Request 
{
	RequestQueryHandler Robj;
	Mentor mentorobj;
	Warden wardenobj;
	Student Sobj;
	public Request()
	{
		mentorobj = new Mentor();
		wardenobj = new Warden();
		Sobj = new Student();
		Robj = new RequestQueryHandler();
	}
	public RequestData getRequestById(String id) 
	{
		if(!checkId(id))
			return null;
		
		try
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss"); 
			ResultSet rs = Robj.getRequestById(id);
			rs.next();
			RequestData data = new RequestData();
			data.RequestId = rs.getString(1);
			data.StudentId = rs.getString(2);
			data.Destination  = rs.getString(3);
			data.fromDate  = rs.getDate(4);
			data.toDate  = rs.getDate(4);
			try
			{
				data.intime  = dateFormat.parse(rs.getString(6));
			}
			catch(Exception e)
			{
				data.intime = null;
			}
			
			try
			{
				data.outtime  = dateFormat.parse(rs.getString(7));
			}
			catch(Exception e)
			{
				data.outtime = null;
			}
			data.internalComment = rs.getString(8);
			data.Status = rs.getInt(9);
			data.RequestType = rs.getInt(10);
			
			return data;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public  ArrayList<RequestData> getRequestbyHostel(String HostelId)
	{	
		try
		{
			ResultSet rs = Robj.getRequestbyHostel(HostelId);
			ArrayList<RequestData> data = new ArrayList<>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
			while(rs.next())
			{
				RequestData Rdata = new RequestData();
				Rdata.RequestId = rs.getString(1);
				Rdata.StudentId = rs.getString(11);
				Rdata.Destination  = rs.getString(2);
				Rdata.fromDate  = rs.getDate(3);
				Rdata.toDate  = rs.getDate(4);
				try
				{
					Rdata.intime  = dateFormat.parse(rs.getString(5));
				}
				catch(Exception e)
				{
					Rdata.intime = null;
				}
				
				try
				{
					Rdata.outtime  = dateFormat.parse(rs.getString(6));
				}
				catch(Exception e)
				{
					Rdata.outtime = null;
				}
				Rdata.internalComment = rs.getString(7);
				Rdata.Status = rs.getInt(8);
				Rdata.RequestType = rs.getInt(9);
				data.add(Rdata);
			}
			return data;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public  ArrayList<RequestData> getRequestbyMentor(String MentorId) 
	{
		if(!mentorobj.checkId(MentorId))
			return null;
		
		try
		{
			ResultSet rs = Robj.getRequestbyMentor(MentorId);
			ArrayList<RequestData> data = new ArrayList<>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
			while(rs.next())
			{
				RequestData Rdata = new RequestData();
				Rdata.RequestId = rs.getString(1);
				Rdata.StudentId = rs.getString(11);
				Rdata.Destination  = rs.getString(2);
				Rdata.fromDate  = rs.getDate(3);
				Rdata.toDate  = rs.getDate(4);
				try
				{
					Rdata.intime  = dateFormat.parse(rs.getString(5));
				}
				catch(Exception e)
				{
					Rdata.intime = null;
				}
				
				try
				{
					Rdata.outtime  = dateFormat.parse(rs.getString(6));
				}
				catch(Exception e)
				{
					Rdata.outtime = null;
				}
				Rdata.internalComment = rs.getString(7);
				Rdata.Status = rs.getInt(8);
				Rdata.RequestType = Integer.parseInt(rs.getString(9));
				data.add(Rdata);
			}
			return data;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public  ArrayList<RequestData> getRequestbyStudent(String StudentId)
	{
		if(!Sobj.checkId(StudentId))
			return null;
		
		try
		{
			ResultSet rs = Robj.getRequestbyStudent(StudentId);
			ArrayList<RequestData> data = new ArrayList<>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
			while(rs.next())
			{
				RequestData Rdata = new RequestData();
				Rdata.RequestId = rs.getString(1);
				Rdata.StudentId = rs.getString(2);
				Rdata.Destination  = rs.getString(3);
				Rdata.fromDate  = rs.getDate(4);
				Rdata.toDate  = rs.getDate(4);
				try
				{
					Rdata.intime  = dateFormat.parse(rs.getString(6));
				}
				catch(Exception e)
				{
					Rdata.intime = null;
				}
				
				try
				{
					Rdata.outtime  = dateFormat.parse(rs.getString(7));
				}
				catch(Exception e)
				{
					Rdata.outtime = null;
				}
				Rdata.internalComment = rs.getString(8);
				Rdata.Status = rs.getInt(9);
				Rdata.RequestType = rs.getInt(10);
				data.add(Rdata);
			}
			return data;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public  ArrayList<RequestData> getAllApprovedRequest() 
	{
		try
		{
			ResultSet rs = Robj.getAllApprovedRequest();
			ArrayList<RequestData> data = new ArrayList<>();
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");
			while(rs.next())
			{
				RequestData Rdata = new RequestData();
				Rdata.RequestId = rs.getString(1);
				Rdata.StudentId = rs.getString(2);
				Rdata.Destination  = rs.getString(3);
				Rdata.fromDate  = rs.getDate(4);
				Rdata.toDate  = rs.getDate(4);
				try
				{
					Rdata.intime  = dateFormat.parse(rs.getString(6));
				}
				catch(Exception e)
				{
					Rdata.intime = null;
				}
				
				try
				{
					Rdata.outtime  = dateFormat.parse(rs.getString(7));
				}
				catch(Exception e)
				{
					Rdata.outtime = null;
				}
				Rdata.internalComment = rs.getString(8);
				Rdata.Status = rs.getInt(9);
				Rdata.RequestType = rs.getInt(10);
				data.add(Rdata);
			}
			return data;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	public  boolean addRequest(RequestData obj) 
	{
		try
		{
			Robj.addRequest(obj);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public  boolean updateRequestbyMentor(String RequestId , int choice) 
	{
		if(!checkId(RequestId))
			return false;
		
		try
		{
			Robj.updateRequestbyMentor(RequestId, choice);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public  boolean updateRequestbyWarden(String RequestId, int choice, String comment) 
	{
		if(!checkId(RequestId))
			return false;
		
		try
		{
			Robj.updateRequestbyWarden(RequestId, choice,comment);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public  boolean deleteRequest(String RequestId) 
	{
		if(!checkId(RequestId))
			return false;
		
		try
		{
			Robj.deleteRequest(RequestId);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public  boolean updateIntime(String RequestId , Date time) 
	{
		if(!checkId(RequestId))
			return false;
		
		try
		{
			Robj.updateIntime(RequestId, time);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public  boolean updateOuttime(String RequestId, Date time) 
	{
		if(!checkId(RequestId))
			return false;
		
		try
		{
			Robj.updateOuttime(RequestId, time);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public  boolean checkIfStudentLate(RequestData obj)
	{
		if(obj.RequestType == 1)
		{
			if(obj.intime.getHours() > 20)
				return true;
		}
		else
		{
			if(obj.intime.getDate() > obj.toDate.getDate())
				return true;
		}
		
		return false;
	}
	public String getRequestId(String StudentId)
	{
		Date date = Calendar.getInstance().getTime();  
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(date);
        strDate=strDate.replaceAll("-","");
        strDate=strDate.replaceAll(":","");
        strDate=strDate.replaceAll(" ","");
		String id = StudentId + strDate;
		return id;
	}
	public boolean checkId(String id)
	{
		try
		{
			Robj.checkId(id);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public ArrayList<StudentData> StudentsOut(String HostelId)
	{
		try
		{
			ArrayList<RequestData> RDobj = getRequestbyHostel(HostelId);
			ArrayList<StudentData> SDobj = new ArrayList<>();
			for(RequestData x : RDobj)
			{
				if(x.intime == null && x.outtime != null)
					SDobj.add(Sobj.getStudentById(x.StudentId));
			}
			return SDobj;
		}
		catch(Exception e)
		{
			return null;
		}
	}
}
