import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import Datum.dayID;
import main.*;


public class ExtremTraurigeLangeweile {

	ArrayList<Fakten> faktentabelle = new ArrayList<Fakten>();
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
	    	  faktentabelle.add(new Fakten(behandleArtikel(input[2]), behandleShop(input[1]), behandleDatum(input[0]), behandleVerkauft(input[3]), behandleUmsatz(input[4])));
	    	  
	    	  
	    	  stringRead = br.readLine();
	      }
	      br.close( );
	    }
	    catch(IOException ioe){
	    	
	    }
	}
	
	private dayID behandleDatum(String datum)
	{
		return dayID.getInstanze(new Date(datum));
	}
	
	private ArtikelID behandleArtikel(String artikel)
	{
		return ArtikelID.getInstanze(artikel);
	}
	
	private ShopID behandleShop(String shop) {
		return ShopID.getInstanze(shop);
	}
	
	private int behandleVerkauft(String verkauft) {
		return Integer.parseInt(verkauft);
	}
	
	private double behandleUmsatz(String umsatz) {
		return Double.parseDouble(umsatz);
	}
}
