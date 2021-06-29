package se.mentor;

import se.dbconnect.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mentor 
{
	MentorQueryHandler obj;
	public Mentor()
	{
		obj = new MentorQueryHandler();
	}
	public boolean addMentor(MentorData mobj , String password) 
	{
		try
		{
			obj.addMentor(mobj,password);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public MentorData getMentorById(String MentorID) 
	{
		try
		{
			ResultSet rs = obj.getMentorById(MentorID);
			rs.next();
			MentorData data = new MentorData();
			data.EmployeeId = rs.getString(1);
			data.name = rs.getString(2);
			data.Emailid = rs.getString(3);
			return data;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public boolean updateMentorPassword(String MentorId, String newPassword)
	{
		try
		{
			obj.updateMentorPassword(MentorId, newPassword);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean deleteMentor(String MentorId) 
	{
		try
		{
			obj.deleteMentor(MentorId);
		}
		catch(Exception e)
		{
			return false;
		}
		return true;
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
}
