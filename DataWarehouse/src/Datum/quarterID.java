package Datum;

import java.util.Date;
import java.util.HashMap;

public class quarterID {

	private int quarter;
	private yearID year;
	private static HashMap<Date, quarterID> instanzen = new HashMap<Date, quarterID>();
	
	public quarterID(Date thisdate) {
		quarter = (thisdate.getMonth()/3)+1;
		year = yearID.getInstanze(thisdate);
	}

	public static quarterID getInstanze(Date thisdate) {
		if(instanzen.containsKey(thisdate)){
			return instanzen.get(thisdate);
		}
		return new quarterID(thisdate);
	}

}
