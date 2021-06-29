package se.Student;

import se.dbconnect.*;
import java.sql.ResultSet;
import java.util.ArrayList;

import se.mentor.*;
import se.warden.*;

public class Student 
{
	StudentQueryHandler obj;
	Mentor mentorobj;
	Warden wardenobj;
	public Student()
	{
	   obj = new StudentQueryHandler();
	   mentorobj = new Mentor();
	   wardenobj = new Warden();
	}
	public StudentData getStudentById(String id)
	{
		if(!checkId(id))
			return null;
		
		try
		{
			ResultSet rs = obj.getStudentById(id);
			rs.next();
			StudentData data = new StudentData();
			data.Id = rs.getString(1); 
			data.Name = rs.getString(2); 
			data.gender = rs.getString(3); 
			data.Email = rs.getString(4); 
			data.status = rs.getInt(6); 
			data.late = rs.getInt(7); 
			data.RoomNumber = rs.getInt(8); 
			data.MentorDetails = mentorobj.getMentorById(rs.getString(9)); 
			data.HostelName = rs.getString(10);
			data.WardenData = wardenobj.getWardenByHostelId(data.HostelName);
			return data;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public ArrayList<StudentData> getStudentsbyHostel(String HostelId)
	{
		try
		{
			ResultSet rs = obj.getStudentbyHostel(HostelId);
			ArrayList<StudentData> data = new ArrayList<>();
			while(rs.next())
			{
				StudentData Sdata = new StudentData();
				Sdata.Id = rs.getString(1); 
				Sdata.Name = rs.getString(2); 
				Sdata.gender = rs.getString(3); 
				Sdata.Email = rs.getString(4); 
				Sdata.status = rs.getInt(6); 
				Sdata.late = rs.getInt(7); 
				Sdata.RoomNumber = rs.getInt(8); 
				Sdata.MentorDetails = mentorobj.getMentorById(rs.getString(9)); 
				Sdata.HostelName = rs.getString(10);
				Sdata.WardenData = wardenobj.getWardenByHostelId(Sdata.HostelName);
				data.add(Sdata);
			}
			return data;
		}
		catch(Exception e)
		{
			return null;
		}
	} 
	public ArrayList<StudentData> getStudentbyMentor(String MentorId)
	{
		try
		{
			ResultSet rs = obj.getStudentbyMentor(MentorId);
			ArrayList<StudentData> data = new ArrayList<>();
			while(rs.next())
			{
				StudentData Sdata = new StudentData();
				Sdata.Id = rs.getString(1); 
				Sdata.Name = rs.getString(2); 
				Sdata.gender = rs.getString(3); 
				Sdata.Email = rs.getString(4); 
				Sdata.status = rs.getInt(6); 
				Sdata.late = rs.getInt(7); 
				Sdata.RoomNumber = rs.getInt(8); 
				Sdata.MentorDetails = mentorobj.getMentorById(rs.getString(9)); 
				Sdata.HostelName = rs.getString(10);
				Sdata.WardenData = wardenobj.getWardenByHostelId(Sdata.HostelName);
				data.add(Sdata);
			}
			return data;
		}
		catch(Exception e)
		{
			return null;
		}
	} 
    public boolean addStudent(StudentData sobj,String password)
	{
		try
		{
			obj.addStudent(sobj,password);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean updatePassword(String id , String newPassword)
	{
		if(!checkId(id))
			return false;
		
		try
		{
			obj.updatePassword(id, newPassword);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean checkId(String id)
	{
		
		try
		{
			obj.checkId(id);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public String getPassword(String id)
	{
		
		try
		{
			if(checkId(id))
			{
				String password = obj.getPassword(id);
				return password;
			}
			else
				return null;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public boolean updateMentor(String StudentId , String newMentor)
	{
		if(!checkId(StudentId))
			return false;
		try
		{
			obj.updateMentor(StudentId, newMentor);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	} 
	public boolean updateHostel(String StudentId , String HostelId)
	{
		if(!checkId(StudentId))
			return false;
		try
		{
			obj.updateHostel(StudentId, HostelId);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	} 
	public boolean updatetimeslate(String StudentId)
	{
		if(!checkId(StudentId))
			return false;
		try
		{
			obj.updatetimeslate(StudentId);;
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean updatestatus(String StudentId , int choice)
	{
		if(!checkId(StudentId))
			return false;
		
		try
		{
			obj.updatestatus(StudentId, choice);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
}
