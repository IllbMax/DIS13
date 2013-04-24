package ersteVersuche.Makler;

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
import ersteVersuche.Material.Makler;

public class MaklermenuWerkzeug {
	private final MaklermenuGUI _GUI;
	MaklerNeuWerkzeug _maklerNeu;

	public MaklermenuWerkzeug() {
		_GUI = new MaklermenuGUI();
		_maklerNeu = new MaklerNeuWerkzeug();
		for(Makler m :LadeMakler())
		{
			_GUI.GetTableModel().AddMakler(m);
		}

		_GUI.AddMaklerAddListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddMakler();
			}

		});
		_GUI.AddMaklerDelListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DelMakler(_GUI.GetAktiveMakler());
			}

		});

		_GUI.AddMaklerUpdListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				int row = e.getFirstRow();
				// int column = e.getColumn();
				TableModelMakler model = (TableModelMakler) e.getSource();
				// String columnName = model.getColumnName(column);
				// Object data = model.getValueAt(row, column);
				String login = model.getLastLoginValue();
				Makler m = model.GetElement(row);
				UpdMakler(m, login);
			}
		});

	}

	private void AddMakler() {
		Makler m = _maklerNeu.ErstelleMakler();

		if (m != null && AddMaklerSQL(m)) {
			_GUI.GetTableModel().AddMakler(m);
		}
		_GUI.repaint();
	}

	private boolean AddMaklerSQL(Makler m) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String selectSQL = "INSERT INTO Makler (Name, Adresse, Login, Passwort) VALUES(?, ?, ?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getAdresse());
			pstmt.setString(3, m.getLogin());
			pstmt.setString(4, m.getPasswort());

			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			return rs > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	private void DelMakler(Makler m) {

		if (m != null && DelMaklerSQL(m)) {
			_GUI.GetTableModel().DeleteMakler(m);
		}
		_GUI.repaint();
	}

	private boolean DelMaklerSQL(Makler m) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String selectSQL = "DELETE FROM Makler WHERE Login = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, m.getLogin());

			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			return rs > 0;
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	private void UpdMakler(Makler m, String login) {

		if (m != null && UpdMaklerSQL(m, login)) {
			// TODO add in sql (table ist schon fertig :-) )
		}
		_GUI.repaint();
	}

	private boolean UpdMaklerSQL(Makler m, String login) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String selectSQL = "UPDATE Makler SET Name = ?, Adresse = ?, Login = ?, Passwort = ? WHERE Login = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, m.getName());
			pstmt.setString(2, m.getAdresse());
			pstmt.setString(3, m.getLogin());
			pstmt.setString(4, m.getPasswort());

			pstmt.setString(5, login);
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