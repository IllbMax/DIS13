package Datum;

import java.util.Date;
import java.util.HashMap;

public class monthID {

	private String month;
	private quarterID quarter;
	private static String[] monate = {"Januar","Februar","März","April",
										"Mai","Juni","Juli","August",
										"September","Oktober","November","Dezember"};
	private static HashMap<Date, monthID> instanzen = new HashMap<Date, monthID>();

	
	@SuppressWarnings("deprecation")
	public monthID(Date thisdate) {
		month= monate[thisdate.getMonth()];
		quarter = quarterID.getInstanze(thisdate);
		instanzen.put(thisdate, this);
	}


	public String getMonth() {
		return month;
	}


	public quarterID getQuarter() {
		return quarter;
	}


	public static monthID getInstanze(Date thisdate) {
		if(instanzen.containsKey(thisdate)){
			return instanzen.get(thisdate);
		}
		return new monthID(thisdate);
	}

}
