package se.warden;

import se.dbconnect.*;
import java.sql.ResultSet;

public class Warden 
{
	WardenQueryHandler obj;
	public Warden()
	{
		obj = new WardenQueryHandler();
	}
	public boolean addWarden(WardenData wobj , String password) 
	{
		try
		{
			obj.addWarden(wobj,password);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public WardenData getWardenById(String WardenID) 
	{
		try
		{
			ResultSet rs = obj.getWardenById(WardenID);
			rs.next();
			WardenData data = new WardenData();
			data.EmployeeId = rs.getString(1);
			data.name = rs.getString(2);
			data.Emailid = rs.getString(3);
			data.HostelId = rs.getString(5);
			return data;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public WardenData getWardenByHostelId(String HostelId)
	{
		try
		{
			ResultSet rs = obj.getWardenByHostelId(HostelId);
			rs.next();
			WardenData data = new WardenData();
			data.EmployeeId = rs.getString(1);
			data.name = rs.getString(2);
			data.Emailid = rs.getString(3);
			data.HostelId = rs.getString(5);
			return data;
		}
		catch(Exception e)
		{
			return null;
		}
	}
	public boolean updateWardenPassword(String WardenId, String newPassword)
	{
		try
		{
			obj.updateWardenPassword(WardenId, newPassword);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean updateWardenHostelId(String WardenId , String HostelId) 
	{
		try
		{
			obj.updateWardenHostelId(WardenId, HostelId);
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	public boolean deleteMentor(String WardenId) 
	{
		try
		{
			obj.deleteWarden(WardenId);
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
	
}
