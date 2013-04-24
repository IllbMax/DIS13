package ersteVersuche.Vertrag;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dis2011.DB2ConnectionManager;
import ersteVersuche.Makler.MaklermenuWerkzeug;
import ersteVersuche.Material.Makler;
import ersteVersuche.Material.Person;

public class PersonVerwaltungWerkzeug {
	
	private PersonVerwaltungGUI _GUI;
	private Person _person;
	private   PersonNeuWerkzeug _personNeu;

	
	private void AddPerson() {
		Person  m = _personNeu.ErstellePerson();

		if (m != null && AddPersonSQL(m)) {
			_GUI.GetTableModel().AddPerson(m);
		}
		_GUI.repaint();
	}

	private boolean AddPersonSQL(Person m) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String selectSQL = "INSERT INTO Person (Vorname, Nachname, Adresse) VALUES(?, ?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, m.getVorname());
			pstmt.setString(2, m.getNachname());
			pstmt.setString(3, m.getAdresse());
			

			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			return rs > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	private void DelPerson(Person m) {

		if (m != null && DelPersonSQL(m)) {
			_GUI.GetTableModel().DeletePerson(m);
		}
		_GUI.repaint();
	}

	private boolean DelPersonSQL(Person m) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String selectSQL = "DELETE FROM Person WHERE PID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, m.getPID());

			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			return rs > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	


	public static void main(String[] args) {
		new MaklermenuWerkzeug();
	}

	public void ZeigeMaklerMenu() {
		_GUI.setVisible(true);
	}

	private List<Makler> LadeMakler() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		List<Makler> result = new ArrayList<Makler>();
		String selectSQL = "SELECT * FROM Makler";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(new Makler(rs.getString("Name"), rs
						.getString("Adresse"), rs.getString("Login"), rs
						.getString("Passwort")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

}

