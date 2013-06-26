package Datum;

import java.util.Date;
import java.util.HashMap;


public class dayID {
	private int day;
	private monthID month;
	private static HashMap<Date, dayID> instanzen = new HashMap<Date, dayID>();
	
	@SuppressWarnings("deprecation")
	public dayID(Date thisdate) {
		super();
		
		this.day = thisdate.getDay();
		this.month = monthID.getInstanze(thisdate);
		instanzen.put(thisdate, this);
	}
	
	public static dayID getInstanze(Date thisdate)
	{
		
		if(instanzen.containsKey(thisdate)){
			return instanzen.get(thisdate);
		}
		return new dayID(thisdate);
		
	}

	public int getday()
	{
		return day;
	}
	
	public monthID getmonthID()
	{
		return month;
	}
}
