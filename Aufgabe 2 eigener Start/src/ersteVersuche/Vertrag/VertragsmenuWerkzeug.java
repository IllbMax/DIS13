package ersteVersuche.Vertrag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dis2011.DB2ConnectionManager;
import ersteVersuche.Material.Haus;
import ersteVersuche.Material.Immobilie;
import ersteVersuche.Material.Kaufvertrag;
import ersteVersuche.Material.Mietvertrag;
import ersteVersuche.Material.Person;
import ersteVersuche.Material.Vertrag;
import ersteVersuche.Material.Wohnung;

public class VertragsmenuWerkzeug {
	private final VertragsmenuGUI _GUI;
	private String _makler;
	private final VertragNeuWerkzeug _vertragNeu;

	private List<Person> _personen;
	private List<Immobilie> _immobilien;

	public VertragsmenuWerkzeug() {

		_GUI = new VertragsmenuGUI();
		_vertragNeu = new VertragNeuWerkzeug(_personen, _immobilien);
		_GUI.AddVertragAddListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddVertrag();
			}

		});
	}

	private void AddVertrag() {
		// TODO: heir fehtl noch der Neu diaglog !!!
		Vertrag i = null; // _immobilieNeu.ErstelleImmobilie();

		if (i != null) {// && AddImmobilieSQL(i)) {
			_GUI.GetTableModel().AddVertrag(i);
		}
		_GUI.repaint();
	}

	public void ZeigeVertragsMenu() {
		_GUI.setVisible(true);
		// _makler = makler;
	}

	private boolean AddVertragSQL(Vertrag v) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String insertSQL = "INSERT INTO Immobilie (ID, Ort, PLZ, Straße, Hausnummer, Fläche, Makler) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(insertSQL);
			// pstmt.setInt(1, v.getID());
			// pstmt.setString(2, v.getOrt());
			// pstmt.setInt(3, v.getPLZ());
			// pstmt.setString(4, v.getStrasse());
			// pstmt.setInt(5, v.getHausNr());
			// pstmt.setFloat(6, v.getFlaeche());
			// pstmt.setString(7, Makler);
			// // TODO: INSERT für immobilien
			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();

			return rs > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	private List<Vertrag> LadeVertrag() {

		List<Vertrag> result = new ArrayList<Vertrag>();
		result.addAll(LadeKaufvertrag());
		result.addAll(LadeMietvertrag());

		return result;
	}

	private List<Kaufvertrag> LadeKaufvertrag() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		List<Kaufvertrag> result = new ArrayList<Kaufvertrag>();
		String selectSQL = "SELECT V.*, K.* FROM Vertrag V, Kaufvertrag K WHERE K.KVertrNr = V.Vertragsnr";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);

			// pstmt.setString(1, _makler);
			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(new Kaufvertrag(rs.getInt("Vertragsnummer"), rs
						.getDate("Datum"), rs.getString("Ort"), FindePerson(rs
						.getInt("Person")), (Haus) FindeImmobilie(rs
						.getInt("Haus")), rs.getInt("AnzahlRaten"), rs
						.getFloat("Ratenzins")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	private List<Mietvertrag> LadeMietvertrag() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		List<Mietvertrag> result = new ArrayList<Mietvertrag>();
		String selectSQL = "SELECT M.*, K.* FROM Vertrag V, Mietvertrag M WHERE M.MVertrNr = V.Vertragsnr";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);

			// pstmt.setString(1, _makler);
			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(new Mietvertrag(rs.getInt("Vertragsnummer"), rs
						.getDate("Datum"), rs.getString("Ort"), FindePerson(rs
						.getInt("Mieter")), (Wohnung) FindeImmobilie(rs
						.getInt("Wohnung")), rs.getDate("Mietbeginn"), rs
						.getInt("Dauer"), rs.getFloat("Nebenkosten")));
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	private Person FindePerson(int pid) {
		Person result = null;
		for (Person p : _personen)
			if (p.getPID() == pid) {
				result = p;
				break;
			}

		return result;
	}

	private Immobilie FindeImmobilie(int id) {
		Immobilie result = null;
		for (Immobilie i : _immobilien)
			if (i.getID() == id) {
				result = i;
				break;
			}
		return result;
	}

	public void AddWindowListener(WindowListener l) {
		_GUI.addWindowListener(l);
	}

}
