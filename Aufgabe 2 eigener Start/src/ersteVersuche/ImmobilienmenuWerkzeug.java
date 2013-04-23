package ersteVersuche;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import ersteVersuche.Material.Immobilie;

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

		if (i != null) {
			// TODO add in sql und table
			_GUI.GetTableModel().AddImmobilie(i);
		}
		_GUI.repaint();
	}

	private void DelImmobilie(Immobilie i) {

		if (i != null) {
			// TODO add in sql und table
			_GUI.GetTableModel().DeleteImmobilie(i);
		}
		_GUI.repaint();
	}

	private void UpdImmobilie(Immobilie i) {

		if (i != null) {
			// TODO add in sql (table ist schon fertig :-) )
		}
		_GUI.repaint();
	}

	public static void main(String[] args) {
		new ImmobilienmenuWerkzeug().ZeigeImmobilienMenu();
	}

	public void ZeigeImmobilienMenu() {
		_GUI.setVisible(true);

	}

}
