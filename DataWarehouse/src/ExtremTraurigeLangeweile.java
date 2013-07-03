import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JTextField;

import dis2011_kopie.DB2ConnectionManager;

import Datum.dayID;
import main.*;


public class ExtremTraurigeLangeweile {

	ArrayList<Fakten> faktentabelle = new ArrayList<Fakten>();
	HashMap<String, Integer> bekannteDati = new HashMap<String, Integer>();
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
	    	  behandleShop(input[1]);
	    	  behandleArtikel(input[2]);
	    	  behandleVerkauft(input[3]);
	    	  behandleUmsatz(input[4]);
	    	  
	    	  
	    	  stringRead = br.readLine();
	      }
	      br.close( );
	    }
	    catch(IOException ioe){
	    	
	    }
	}
	
	@SuppressWarnings("deprecation")
	private int behandleDatum(String datum)
	{
		if(!bekannteDati.containsKey(datum))
		{
			Date d = new Date(datum);
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			try {
				// Erzeuge Anfrage
				String selectSQL = "select id from blub...???";
				PreparedStatement pstmt = con.prepareStatement(selectSQL);
				pstmt.setInt(1, d.getDate());
				pstmt.setInt(2, d.getMonth()+1);
				pstmt.setInt(3, d.getYear()+1900);

				// Führe Anfrage aus
				ResultSet rs = pstmt.executeQuery();
				bekannteDati.put(datum, rs.getInt(1));
				
			} catch (SQLException e) {
				
				// TODO ordentliches Exeption Handling
				
			} finally {
				// try { con.close();} catch (SQLException e) {e.printStackTrace();}

			}
		}
		return bekannteDati.get(datum);
	}
	
	private int behandleArtikel(String artikel)
	{
		if(!bekannteDati.containsKey(artikel))
		{
			Date d = new Date(artikel);
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			try {
				// Erzeuge Anfrage
				String selectSQL = "select id from blub...???";
				PreparedStatement pstmt = con.prepareStatement(selectSQL);
				pstmt.setInt(1, d.getDate());
				pstmt.setInt(2, d.getMonth()+1);
				pstmt.setInt(3, d.getYear()+1900);

				// Führe Anfrage aus
				ResultSet rs = pstmt.executeQuery();
				bekannteDati.put(artikel, rs.getInt(1));
				
			} catch (SQLException e) {
				
				// TODO ordentliches Exeption Handling
				
			} finally {
				// try { con.close();} catch (SQLException e) {e.printStackTrace();}

			}
		}
		return bekannteDati.get(artikel);
	}
	
	private int behandleShop(String shop) {
		if(!bekannteDati.containsKey(shop))
		{
			Date d = new Date(shop);
			Connection con = DB2ConnectionManager.getInstance().getConnection();
			try {
				// Erzeuge Anfrage
				String selectSQL = "select id from blub...???";
				PreparedStatement pstmt = con.prepareStatement(selectSQL);
				pstmt.setInt(1, d.getDate());
				pstmt.setInt(2, d.getMonth()+1);
				pstmt.setInt(3, d.getYear()+1900);

				// Führe Anfrage aus
				ResultSet rs = pstmt.executeQuery();
				bekannteDati.put(shop, rs.getInt(1));
				
			} catch (SQLException e) {
				
				// TODO ordentliches Exeption Handling
				
			} finally {
				// try { con.close();} catch (SQLException e) {e.printStackTrace();}

			}
		}
		return bekannteDati.get(shop);
	}
	
	private int behandleVerkauft(String verkauft) {
		return Integer.parseInt(verkauft);
	}
	
	private double behandleUmsatz(String umsatz) {
		return Double.parseDouble(umsatz);
	}
	
	private void upload(int dayID, int shopID, int artikelID, int verkauf, int umsatz)
	{
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		try {
			// Erzeuge Anfrage
			String selectSQL = "insert int fakten values (?,?,?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, dayID);
			pstmt.setInt(2, shopID);
			pstmt.setInt(3, artikelID);
			pstmt.setInt(4, verkauf);
			pstmt.setInt(5, umsatz);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();
			
			
		} catch (SQLException e) {
			
			// TODO ordentliches Exeption Handling
			
		} finally {
			

		}
	}
}
