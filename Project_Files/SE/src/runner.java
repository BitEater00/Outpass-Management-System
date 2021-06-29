import se.Student.*;
import se.mentor.*;
import se.warden.*;
import se.request.*;

public class runner 
{
	public static void main(String agrs[])
	{
		Request obj = new Request();
		System.out.println(obj.getAllApprovedRequest());
	}
}
