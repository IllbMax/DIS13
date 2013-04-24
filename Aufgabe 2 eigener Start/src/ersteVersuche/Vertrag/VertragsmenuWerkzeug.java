package ersteVersuche.Vertrag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	private final List<Person> _personen = LadePersonen();
	private final List<Immobilie> _immobilien = LadeImmobilien();

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
		Vertrag i = _vertragNeu.ErstelleVertrag();

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
		String insertSQL = "INSERT INTO Vertrag (Datum, Ort) VALUES(?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, v.getDatum());
			pstmt.setString(2, v.getOrt());
			
			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			if(rs <= 0) return false;
			
			ResultSet keys = pstmt.getGeneratedKeys();
			keys.next();
			int ID = keys.getInt(1);
			v.setVertragsnr(ID);
			if(v instanceof Kaufvertrag)
			{
				insertSQL = "INSERT INTO Kaufvertrag (KVertrNr, AnzahlRaten, Ratenzins, Haus, Person) VALUES(?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, ID);
				pstmt.setInt(2, ((Kaufvertrag) v).getAnzahlRaten());
				pstmt.setFloat(3, ((Kaufvertrag) v).getRatenzins());
				pstmt.setInt(4, ((Kaufvertrag) v).getHaus().getID());
				pstmt.setInt(5, v.getPerson().getPID());
				
				rs = pstmt.executeUpdate();
				if(rs > 0) return true;
			}
			if(v instanceof Mietvertrag)
			{
				insertSQL = "INSERT INTO Mietvertrag (MVertrNr, Mietbeginn, Dauer, Nebenkosten, Wohnung, Mieter) VALUES(?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, ID);
				pstmt.setDate(2, v.getDatum());
				pstmt.setFloat(3, ((Mietvertrag) v).getDauer());
				pstmt.setFloat(4, ((Mietvertrag) v).getNebenkosten());
				pstmt.setInt(5, ((Mietvertrag) v).getWohnung().getID());
				pstmt.setInt(6, ((Mietvertrag) v).getPerson().getPID());
				
				rs = pstmt.executeUpdate();
				if(rs > 0) return true;
			}
			
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

	private List<Immobilie> LadeImmobilien() {

		List<Immobilie> result = new ArrayList<Immobilie>();
		result.addAll(LadeImmobilienHaeuser());
		result.addAll(LadeImmobilienWohnungen());

		return result;
	}

	private List<Haus> LadeImmobilienHaeuser() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		List<Haus> result = new ArrayList<Haus>();
		String selectSQL = "SELECT * FROM Haus h, Immobilie i where h.id = i.id";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(new Haus(rs.getInt("ID"), rs.getString("Ort"), rs
						.getInt("PLZ"), rs.getString("Straße"), rs
						.getInt("Hausnummer"), rs.getFloat("Fläche"), rs
						.getInt("Stockwerke"), rs.getFloat("Kaufpreis"), rs
						.getBoolean("Garten")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	private List<Wohnung> LadeImmobilienWohnungen() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		List<Wohnung> result = new ArrayList<Wohnung>();
		String selectSQL = "SELECT * FROM Wohnung w, Immobilie i where w.id = i.id";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(new Wohnung(rs.getInt("ID"), rs.getString("Ort"), rs
						.getInt("PLZ"), rs.getString("Straße"), Integer
						.parseInt(rs.getString("Hausnummer")), rs
						.getFloat("Fläche"), rs.getInt("Stockwerk"), rs
						.getFloat("Mietpreis"), rs.getInt("Zimmer"), rs
						.getBoolean("Balkon"), rs.getBoolean("EBK")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	private List<Person> LadePersonen() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		List<Person> result = new ArrayList<Person>();
		String selectSQL = "SELECT * FROM Person";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(new Person(rs.getInt("PID"),
						rs.getString("Vorname"), rs.getString("Nachname"), rs
								.getString("Adresse")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

	public void AddWindowListener(WindowListener l) {
		_GUI.addWindowListener(l);
	}

}
