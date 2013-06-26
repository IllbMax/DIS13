import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

import Datum.dayID;


public class ExtremTraurigeLangeweile {

	Object[][][] _warehouse = {{{null},{null},{null},{null}},{{null},{null},{null},{null}}};
	File csv = new File("../sales.csv");
	public static void main(String[] args) {
	

	}
	
	@SuppressWarnings("deprecation")
	public void csvRead(){
		try
	    {
	      FileReader fr = new FileReader(csv);
	      BufferedReader br = new BufferedReader(fr);
	      String stringRead = br.readLine();
	      String[] input= null;
	      while( stringRead != null )
	      {
	    	  input = stringRead.split(";");
	    	  behandleDatum(input[0]);
	    	  dayID.getInstanze(new Date(input[0]));
	    	  
	    	  stringRead = br.readLine();
	      }
	      br.close( );
	    }
	    catch(IOException ioe){
	    	
	    }
	}
	
	private void behandleDatum(String datum)
	{
		
	}
}
