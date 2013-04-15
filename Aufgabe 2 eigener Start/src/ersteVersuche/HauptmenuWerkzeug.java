package ersteVersuche;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Observable;
import java.sql.SQLException;
import de.dis2011.data.DB2ConnectionManager;

public class HauptmenuWerkzeug
{

	/**
	 * @param args werden nicht beachtet.
	 */
	public static void main(String[] args) {
		//TODO Hier muss das Loginfenster aufgerufen werden.

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
						// Erzeuge Anf)rage
						String selectSQL = "select Passwort from Makler where Login="+login;
						PreparedStatement pstmt = con.prepareStatement(selectSQL);

						// Führe Anfrage aus
						ResultSet rs = pstmt.executeQuery();
						if(rs.next())
						{
							return passwort.equals(rs.getString("passwort"));
						}
						}
					 catch (SQLException e) {
						e.printStackTrace();
						//TODO ordentliches Exeption Handling
					 	}
					finally {return false;}
		
	}
}
