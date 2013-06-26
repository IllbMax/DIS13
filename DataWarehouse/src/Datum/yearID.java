package Datum;

import java.util.Date;
import java.util.HashMap;

public class yearID {

	private int year;
	
	private static HashMap<Date, yearID> instanzen = new HashMap<Date, yearID>();
	
	@SuppressWarnings("deprecation")
	public yearID(Date thisdate) {
		year = thisdate.getYear();
		instanzen.put(thisdate, this);
	}

	public static yearID getInstanze(Date thisdate) {
		if(instanzen.containsKey(thisdate)){
			return instanzen.get(thisdate);
		}
		return new yearID(thisdate);
	}

	public int getYear() {
		return year;
	}


}
