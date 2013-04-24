package ersteVersuche;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dis2011.DB2ConnectionManager;
import ersteVersuche.Immobilie.ImmobilienmenuWerkzeug;
import ersteVersuche.Makler.MaklermenuWerkzeug;
import ersteVersuche.Vertrag.VertragsmenuWerkzeug;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextField;

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
		
		
		
		_hauptmenuGUI.AddMaklerButtonListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ZeigeMaklerVerwaltung(_hauptmenuGUI.GetPasswort1Text());
				
			}
		});
		_hauptmenuGUI.AddVertragsListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ZeigeVertragsVerwaltung();
				
			}
		});
		_hauptmenuGUI.AddImmobilienButtonListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ZeigeImmobilienVerwaltung(_hauptmenuGUI.GetLoginText(),
						_hauptmenuGUI.GetPasswort2Text());	
			}
		});
		_MaklermenuWerkzeug.AddWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				_hauptmenuGUI.setVisible(true);
			}	
		});
		_VertragsmenuWerkzeug.AddWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				_hauptmenuGUI.setVisible(true);
			}	
		});
		_ImmobilienmenuWerkzeug.AddWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				_hauptmenuGUI.setVisible(true);
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
			_MaklermenuWerkzeug.ZeigeMaklerMenu();
			_hauptmenuGUI.setVisible(false);
		}
		
	}
	
	/**
	 * Erzeugt das Fenster zur Verwaltung der Immobilien.
	 */
	private void ZeigeImmobilienVerwaltung(String login, String passwort) {
		if(pruefeLoginDaten(login, passwort))
		{
			
			System.out.println("Zugang genehmigt!");
			_ImmobilienmenuWerkzeug.ZeigeImmobilienMenu();
			//TODO hier muss das passende Fenster geöffnet werden(Immoblilien)
			_hauptmenuGUI.setVisible(false);
		}
	}
	
	/**
	 * Erzeugt das Fenster zur Verwaltung der Verträge.
	 */
	private void ZeigeVertragsVerwaltung() {
		_VertragsmenuWerkzeug.ZeigeVertragsMenu();
		_hauptmenuGUI.setVisible(false);
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
			String selectSQL = "select 42 from Makler where Login= ? and passwort= ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, login);
			pstmt.setString(2, passwort);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();
			
			return rs.next()&&(rs.getInt(1)==42);// && !login.contains("'") && !passwort.contains("'");
		}
		catch (SQLException e) {
			e.printStackTrace();
			//TODO ordentliches Exeption Handling
			JDialog warnung = new JDialog();
			warnung.add(new JTextField("Ein Verbindungsfehler ist aufgetreten."));
			warnung.setAlwaysOnTop(true);
			warnung.setVisible(true);
	 	}
		finally {
			//try {	con.close();} catch (SQLException e) {e.printStackTrace();}
			
		}
		return false;
		
	}
}
