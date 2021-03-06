package ersteVersuche;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import ersteVersuche.Immobilie.ImmobilienmenuWerkzeug;
import ersteVersuche.Makler.MaklermenuWerkzeug;
import ersteVersuche.Material.Makler;
import ersteVersuche.Services.ImmoService;
import ersteVersuche.Vertrag.PersonVerwaltungWerkzeug;
import ersteVersuche.Vertrag.VertragsmenuWerkzeug;

public class HauptmenuWerkzeug {
	private final HauptmenuGUI _hauptmenuGUI;

	private final ImmoService _service;

	private final MaklermenuWerkzeug _MaklermenuWerkzeug;
	private final ImmobilienmenuWerkzeug _ImmobilienmenuWerkzeug;
	private final VertragsmenuWerkzeug _VertragsmenuWerkzeug;
	private final PersonVerwaltungWerkzeug _PersonverwaltungWerkzeug;

	/**
	 * @param args
	 *            werden nicht beachtet.
	 */
	public static void main(String[] args) {
		// TODO Hier muss das Loginfenster aufgerufen werden.
		ImmoService service = new ImmoService();
		final HauptmenuWerkzeug HauptmenuWerkzeug = new HauptmenuWerkzeug(
				service);
		// try {
		//
		// } finally {
		// try {
		// DB2ConnectionManager.getInstance().getConnection().close();
		// } catch (SQLException e) {
		// e.printStackTrace();
		// }
		// }

	}

	/**
	 * Konstruktor oder so
	 */
	public HauptmenuWerkzeug(ImmoService service) {
		_service = service;
		_MaklermenuWerkzeug = new MaklermenuWerkzeug(_service);
		_ImmobilienmenuWerkzeug = new ImmobilienmenuWerkzeug(_service);
		_VertragsmenuWerkzeug = new VertragsmenuWerkzeug(_service);
		_PersonverwaltungWerkzeug = new PersonVerwaltungWerkzeug(_service);

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
				ZeigeVertragsVerwaltung(_hauptmenuGUI.GetLoginText(),
						_hauptmenuGUI.GetPasswort2Text());

			}
		});
		_hauptmenuGUI.AddImmobilienButtonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ZeigeImmobilienVerwaltung(_hauptmenuGUI.GetLoginText(),
						_hauptmenuGUI.GetPasswort2Text());
			}
		});
		_hauptmenuGUI.AddPersonListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_PersonverwaltungWerkzeug.ZeigePersonenMenu();

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
		if (pruefePasswort(passwort)) {
			System.out.println("Zugang genehmigt!");
			_MaklermenuWerkzeug.ZeigeMaklerMenu();
			_hauptmenuGUI.setVisible(false);
		}

	}

	/**
	 * Erzeugt das Fenster zur Verwaltung der Immobilien.
	 */
	private void ZeigeImmobilienVerwaltung(String login, String passwort) {
		if (pruefeLoginDaten(login, passwort)) {

			System.out.println("Zugang genehmigt!");
			Makler m = _service.getMaklerByLogin(login);
			_ImmobilienmenuWerkzeug.ZeigeImmobilienMenu(m);
			// TODO hier muss das passende Fenster geöffnet werden(Immoblilien)
			_hauptmenuGUI.setVisible(false);
		}
	}

	/**
	 * Erzeugt das Fenster zur Verwaltung der Verträge.
	 */
	private void ZeigeVertragsVerwaltung(String login, String passwort) {
		if (pruefeLoginDaten(login, passwort)) {

			System.out.println("Zugang genehmigt!");
			Makler m = _service.getMaklerByLogin(login);
			_VertragsmenuWerkzeug.ZeigeVertragsMenu(m);
			_hauptmenuGUI.setVisible(false);
		}
	}

	/**
	 * Überprüft das gegebene Passwort für den Zutritt zur Makler-Verwaltung auf
	 * Gültigkeit.
	 */
	private boolean pruefePasswort(String passwort) {
		return "123456".equals(passwort);
	}

	/**
	 * Überprüft mit Hilfe der Datenbank, ob für den angegebenen Makler(Login)
	 * das Passwort stimmt
	 */
	private boolean pruefeLoginDaten(String login, String passwort) {

		Makler m = _service.getMaklerByLoginPasswort(login, passwort);

		return m != null;

	}
}
