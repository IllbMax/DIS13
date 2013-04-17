package ersteVersuche;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dis2011.DB2ConnectionManager;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class HauptmenuWerkzeug
{
	private HauptmenuGUI _hauptmenuGUI;
	
	private MaklermenuWerkzeug _MaklermenuWerkzeug;
	private ImmobilienmenuWerkzeug _ImmobilienmenuWerkzeug;
	private VertragsmenuWerkzeug _VertragsmenuWerkzeug;
	
	/**
	 * @param args werden nicht beachtet.
	 */
	public static void main(String[] args) {
		//TODO Hier muss das Loginfenster aufgerufen werden.
		final HauptmenuWerkzeug HauptmenuWerkzeug = new HauptmenuWerkzeug();
		
		
	}
	
	/**
	 * Konstruktor oder so
	 */
	public HauptmenuWerkzeug()
	{
		_MaklermenuWerkzeug = new MaklermenuWerkzeug();
		_ImmobilienmenuWerkzeug = new ImmobilienmenuWerkzeug();
		_VertragsmenuWerkzeug = new VertragsmenuWerkzeug();
		
		_hauptmenuGUI = new HauptmenuGUI();
		
		_hauptmenuGUI.addWindowListener(new WindowAdapter(){
			  public void windowClosing(WindowEvent we){
			  System.exit(0);
			  }
			  });
	}
	/**
	 * Erzeugt das Fenster zur Verwaltung der Makler.
	 */
	private void ZeigeMaklerVerwaltung(String passwort) {
		if(pruefePasswort(passwort))
		{
			System.out.println("Zugang genehmigt!");
			//TODO hier muss das passende Fenster geöffnet werden(Makler)
		}
		
	}
	
	/**
	 * Erzeugt das Fenster zur Verwaltung der Immobilien.
	 */
	private void ZeigeImmobilienVerwaltung(String login, String passwort) {
		if(pruefeLoginDaten(login, passwort))
		{
			
			System.out.println("Zugang genehmigt!");
			//TODO hier muss das passende Fenster geöffnet werden(Immoblilien)
		}
	}
	
	/**
	 * Erzeugt das Fenster zur Verwaltung der Verträge.
	 */
	private void ZeigeVertragsVerwaltung() {
		//TODO hier muss das passende Fenster geöffnet werden(Verträge)
	}
	
	/**
	 * Überprüft das gegebene Passwort für den Zutritt zur Makler-Verwaltung auf Gültigkeit.
	 */
	private boolean pruefePasswort(String passwort) {
		return "123456".equals(passwort);
	}
	
	/**
	 * Überprüft mit Hilfe der Datenbank, ob für den angegebenen Makler(Login) das Passwort stimmt
	 */
	@SuppressWarnings("finally")
	private boolean pruefeLoginDaten(String login, String passwort) {
		// Hole Verbindung
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		try {
			// Erzeuge Anfrage
			String selectSQL = "select 42 from Makler where Login="+login+" and passwort="+passwort;
			PreparedStatement pstmt = con.prepareStatement(selectSQL);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();
			return rs.next() && !login.contains("'") && !passwort.contains("'");
		}
		catch (SQLException e) {
			e.printStackTrace();
			//TODO ordentliches Exeption Handling
	 	}
		finally {
			try {	con.close();} catch (SQLException e) {e.printStackTrace();}
			return false;
		}
		
	}
}
