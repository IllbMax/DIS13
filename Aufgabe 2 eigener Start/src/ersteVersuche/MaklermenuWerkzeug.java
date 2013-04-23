package ersteVersuche;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import ersteVersuche.Material.Makler;

public class MaklermenuWerkzeug {
	private final MaklermenuGUI _GUI;
	MaklerNeuWerkzeug _maklerNeu;

	public MaklermenuWerkzeug() {
		_GUI = new MaklermenuGUI();
		_maklerNeu = new MaklerNeuWerkzeug();

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
				Makler m = model.GetElement(row);
				UpdMakler(m);
			}
		});

	}

	private void AddMakler() {
		Makler m = _maklerNeu.ErstelleMakler();

		if (m != null) {
			// TODO add in sql und table
			_GUI.GetTableModel().AddMakler(m);
		}
		_GUI.repaint();
	}

	private void DelMakler(Makler m) {

		if (m != null) {
			// TODO add in sql und table
			_GUI.GetTableModel().DeleteMakler(m);
		}
		_GUI.repaint();
	}

	private void UpdMakler(Makler m) {

		if (m != null) {
			// TODO add in sql (table ist schon fertig :-) )
		}
		_GUI.repaint();
	}

	public static void main(String[] args) {
		new MaklermenuWerkzeug();
	}
	
	public void ZeigeMaklerMenu()
	{
		_GUI.setVisible(true);
	}

}