package ersteVersuche.Immobilie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import dis2011.DB2ConnectionManager;
import ersteVersuche.Material.Haus;
import ersteVersuche.Material.Immobilie;
import ersteVersuche.Material.Makler;
import ersteVersuche.Material.Wohnung;

public class ImmobilienmenuWerkzeug {
	private final ImmobilienmenuGUI _GUI;
	private final ImmobilieNeuWerkzeug _immobilieNeu;
	private String Makler;

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
		
		for(Immobilie i :LadeImmobilien())
		{
			_GUI.GetTableModel().AddImmobilie(i);
		}
		
	}

	private void AddImmobilie() {
		Immobilie i = _immobilieNeu.ErstelleImmobilie();

		if (i != null && AddImmobilieSQL(i)) {
			_GUI.GetTableModel().AddImmobilie(i);
		}
		_GUI.repaint();
	}

	private void DelImmobilie(Immobilie i) {

		if (i != null && DelImmobilieSQL(i)) {
			_GUI.GetTableModel().DeleteImmobilie(i);
		}
		_GUI.repaint();
	}

	private void UpdImmobilie(Immobilie i) {

		if (i != null && UpdImmobilieSQL(i)) {
		}
		_GUI.repaint();
	}


	public void ZeigeImmobilienMenu(String Login) {
		Makler = Login;
		_GUI.setVisible(true);

	}

	private boolean AddImmobilieSQL(Immobilie i) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String insertSQL = "INSERT INTO Immobilie ( Ort, PLZ, Straße, Hausnummer, Fläche, Makler) VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, i.getOrt());
			pstmt.setInt(2, i.getPLZ());
			pstmt.setString(3, i.getStrasse());
			pstmt.setInt(4, i.getHausNr());
			pstmt.setFloat(5, i.getFlaeche());
			pstmt.setString(6, Makler);
			// TODO: INSERT für immobilien
			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			if(rs <= 0) return false;
			
			ResultSet keys = pstmt.getGeneratedKeys();
			keys.next();
			int ID = keys.getInt(1);
			i.setID(ID);
			if(i instanceof Haus)
			{
				insertSQL = "INSERT INTO Haus (ID, Stockwerke, Kaufpreis, Garten) VALUES(?, ?, ?, ?)";
				pstmt = con.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, ID);
				pstmt.setInt(2, ((Haus) i).getStockwerke());
				pstmt.setFloat(3, ((Haus) i).getKaufpreis());
				pstmt.setBoolean(4, ((Haus) i).isGarten());
				
				rs = pstmt.executeUpdate();
				if(rs > 0) return true;
			}
			if(i instanceof Wohnung)
			{
				insertSQL = "INSERT INTO Wohnung (ID, Stockwerk, Mietpreis, Zimmer, Balkon, EBK) VALUES(?, ?, ?, ?, ?, ?)";
				pstmt = con.prepareStatement(insertSQL,Statement.RETURN_GENERATED_KEYS);
				pstmt.setInt(1, ID);
				pstmt.setInt(2, ((Wohnung) i).getStockwerk());
				pstmt.setFloat(3, ((Wohnung) i).getMietpreis());
				pstmt.setInt(4,((Wohnung) i).getZimmer());
				pstmt.setBoolean(5, ((Wohnung) i).isBalkon());
				pstmt.setBoolean(6, ((Wohnung) i).isEBK());
				
				rs = pstmt.executeUpdate();
				if(rs > 0) return true;
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	private boolean DelImmobilieSQL(Immobilie i) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String selectSQL ="";
		if (i instanceof Haus)
			selectSQL += " DELETE FROM Haus WHERE ID = ?";
		if (i instanceof Wohnung)
			selectSQL += " DELETE FROM Wohnung WHERE ID = ?";
		
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, i.getID());
			//pstmt.setInt(2, i.getID());

			// Führe Anfrage aus
			int rs = pstmt.executeUpdate();
			
			//Und nun die Immobilie weg.
			if(rs < 0) return false;
			selectSQL = "DELETE FROM Immobilie WHERE ID = ? ";
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setInt(1, i.getID());
			rs = pstmt.executeUpdate();
			return rs > 0;
			
		} catch (SQLException e) {

			e.printStackTrace();
		}

		return false;
	}

	private boolean UpdImmobilieSQL(Immobilie i) {
		Connection con = DB2ConnectionManager.getInstance().getConnection();
		String selectSQL = "UPDATE Immobilie SET Ort = ?, PLZ = ?, Straße = ?, Hausnummer = ?, Fläche = ? WHERE ID = ?";
		PreparedStatement pstmt;
		try {
			pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, i.getOrt());
			pstmt.setInt(2, i.getPLZ());
			pstmt.setString(3, i.getStrasse());
			pstmt.setInt(4, i.getHausNr());
			pstmt.setFloat(5, i.getFlaeche());

			// pstmt.setString(5, login);
			pstmt.setInt(6, i.getID());

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
						.getInt("PLZ"), rs.getString("Straße"), Integer.parseInt(rs
						.getString("Hausnummer")), rs.getFloat("Fläche"), rs
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
