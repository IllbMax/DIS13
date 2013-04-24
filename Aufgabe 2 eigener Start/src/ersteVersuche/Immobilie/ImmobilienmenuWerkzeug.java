package ersteVersuche.Immobilie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import dis2011.DB2ConnectionManager;
import ersteVersuche.Material.Haus;
import ersteVersuche.Material.Immobilie;
import ersteVersuche.Material.Wohnung;

public class ImmobilienmenuWerkzeug {
	private final ImmobilienmenuGUI _GUI;
	private final ImmobilieNeuWerkzeug _immobilieNeu;

	public ImmobilienmenuWerkzeug() {
		_GUI = new ImmobilienmenuGUI();
		_immobilieNeu = new ImmobilieNeuWerkzeug();
		_GUI.AddImmobilienAddListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddImmobilie();
			}

		});
		_GUI.AddImmobilienDelListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DelImmobilie(_GUI.GetAktiveImmobilie());
			}

		});

		_GUI.AddImmobilienUpdListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				// int column = e.getColumn();
				TableModelImmobilie model = (TableModelImmobilie) e.getSource();
				// String columnName = model.getColumnName(column);
				// Object data = model.getValueAt(row, column);
				Immobilie i = model.GetElement(row);
				UpdImmobilie(i);
			}
		});
	}

	private void AddImmobilie() {
		Immobilie i = _immobilieNeu.ErstelleImmobilie();

		if (i != null )
		{//&& AddImmobilieSQL(i)) {
			_GUI.GetTableModel().AddImmobilie(i);
		}
		_GUI.repaint();
	}

	private void DelImmobilie(Immobilie i) {

		if (i != null){// && DelImmobilieSQL(i)) {
			_GUI.GetTableModel().DeleteImmobilie(i);
		}
		_GUI.repaint();
	}

	private void UpdImmobilie(Immobilie i) {

		if (i != null){// && UpdImmobilieSQL(i)) {
		}
		_GUI.repaint();
	}


	public void ZeigeImmobilienMenu() {
		_GUI.setVisible(true);

	}

	private boolean AddImmobilieSQL(Immobilie i) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String insertSQL = "INSERT INTO Immobilie (ID, Ort, PLZ, Straße, Hausnummer, Fläche, Makler) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(insertSQL);
			pstmt.setInt(1, i.getID());
			pstmt.setString(2, i.getOrt());
			pstmt.setInt(3, i.getPLZ());
			pstmt.setString(4, i.getStrasse());
			pstmt.setString(5, i.getHausNr());
			pstmt.setFloat(6, i.getFlaeche());
			pstmt.setString(7, i.getMakler());
			// TODO: INSERT für immobilien
			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			return rs > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	private boolean DelImmobilieSQL(Immobilie i) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String selectSQL = "DELETE FROM Immobilie WHERE ID = ?; ";
		if (i instanceof Haus)
			selectSQL += " DELETE FROM Haus WHERE ID = ?";
		if (i instanceof Wohnung)
			selectSQL += " DELETE FROM Wohnung WHERE ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, i.getID());
			pstmt.setInt(2, i.getID());

			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			return rs > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	private boolean UpdImmobilieSQL(Immobilie i) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String selectSQL = "UPDATE Makler SET Name = ?, Adresse = ?, Login = ?, Passwort = ? WHERE Login = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);
			// pstmt.setString(1, i.getName());
			// pstmt.setString(2, i.getAdresse());
			// pstmt.setString(3, i.getLogin());
			// pstmt.setString(4, i.getPasswort());

			// pstmt.setString(5, login);

			// TODO: UPDATE für immobilien

			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			return rs > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
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
		String selectSQL = "SELECT * FROM Haus JOIN Immobilie ON Id";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(new Haus(rs.getInt("ID"), rs.getString("Ort"), rs
						.getInt("PLZ"), rs.getString("Strasse"), rs
						.getString("HausNr"), rs.getFloat("Flaeche"), rs
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
		String selectSQL = "SELECT * FROM Wohnung JOIN Immobilie ON Id";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);

			// Führe Anfrage aus
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				result.add(new Wohnung(rs.getInt("ID"), rs.getString("Ort"), rs
						.getInt("PLZ"), rs.getString("Strasse"), rs
						.getString("HausNr"), rs.getFloat("Flaeche"), rs
						.getInt("Stockwerk"), rs.getFloat("Mietpreis"), rs
						.getInt("Zimmer"), rs.getBoolean("Balkon"), rs
						.getBoolean("EBK")));
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
