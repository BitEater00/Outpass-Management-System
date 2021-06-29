package se.computation;

import java.util.ArrayList;

import se.request.Request;
import se.request.RequestData;

public class StudentSummary 
{
	int totalrequest;
	int activerequest;
	int approved;
	int rejected;
	Request obj = new Request();
	
	public int[] getData(String id)
	{
		ArrayList<RequestData> data = obj.getRequestbyStudent(id);
		for(RequestData x : data)
		{
			totalrequest++;
			if(x.Status == 1 || x.Status == 2 || x.Status == 4)
				activerequest++;
			if(x.Status == 6)
				approved++;
			if(x.Status == 3 || x.Status == 5)
				rejected++;
		}
		
		return new int[] {totalrequest , activerequest , approved , rejected};
	}
}
