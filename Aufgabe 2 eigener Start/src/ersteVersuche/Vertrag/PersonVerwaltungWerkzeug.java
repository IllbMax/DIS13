package ersteVersuche.Vertrag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import dis2011.DB2ConnectionManager;
import ersteVersuche.Makler.MaklerNeuWerkzeug;
import ersteVersuche.Makler.MaklermenuGUI;
import ersteVersuche.Makler.MaklermenuWerkzeug;
import ersteVersuche.Makler.TableModelMakler;
import ersteVersuche.Material.Makler;
import ersteVersuche.Material.Person;

public class PersonVerwaltungWerkzeug {
	
	private PersonVerwaltungGUI _GUI;
	private Person _person;
	private   PersonNeuWerkzeug _personNeu;
 public PersonVerwaltungWerkzeug()
 {
	 _GUI = new PersonVerwaltungGUI();
		_personNeu = new PersonNeuWerkzeug();
		for(Person m :LadePerson())
		{
			_GUI.GetTableModel().AddPerson(m);
		}

		_GUI.AddPersonAddListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddPerson();
			}

		});
		_GUI.AddPersonDelListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DelPerson(_GUI.GetAktivePerson());
			}

		});

		
 
 }
	
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

	public void ZeigePersonenMenu() {
		_GUI.setVisible(true);
	}

	private List<Person> LadePerson() {
		Connection con = DB2ConnectionManager.getInstance().getConnection();

		List<Person> result = new ArrayList<Person>();
		String selectSQL = "SELECT * FROM Person";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(new Person(rs.getInt("PID"), rs
						.getString("Vorname"), rs.getString("Nachname"), rs
						.getString("Adresse")));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return result;
	}

}

