package se.computation;

import java.util.ArrayList;
import se.request.Request;
import se.request.RequestData;

public class MS 
{
	public int[] getDataM(String id)
	{
		Request obj = new Request();
		ArrayList<RequestData> data = obj.getRequestbyMentor(id);
		int total = 0;
		int active = 0;
		for(RequestData x : data)
		{
			total++;
			if(x.Status == 1 || x.Status == 4)
				active++;
		}
		
		return new int[] {total , active};
	}
	
	public int[] getDataW(String id)
	{
		Request obj = new Request();
		ArrayList<RequestData> data = obj.getRequestbyHostel(id);
		ArrayList<RequestData> data2 = obj.getAllApprovedRequest();
		int total = 0;
		int active = 0;
		int out = 0;
		for(RequestData x : data)
		{
			total++;
			if(x.Status == 2)
				active++;
		}
		
		for(RequestData x : data2)
			if(x.intime != null && x.outtime == null)
				out++;
		
		return new int[] {total , active , out};
	}
}
