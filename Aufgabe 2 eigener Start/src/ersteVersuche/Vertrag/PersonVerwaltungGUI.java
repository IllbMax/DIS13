package ersteVersuche.Vertrag;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

import ersteVersuche.Material.Person;

public class PersonVerwaltungGUI extends JFrame {
	private static final long serialVersionUID = -8976151471696419140L;

	TableModelPerson _tmodel;
	JTable _table;
	JButton _personAdd, _personDel;

	public PersonVerwaltungGUI() {

		setTitle("Immobilienverwaltungssoftware - Personverwaltung");
		setSize(800, 600);

		setLocation(280, 100);
		setLayout(new BorderLayout());
		setBackground(new Color(255, 255, 255));

		JPanel tablepanel = new JPanel(new BorderLayout());

		_table = new JTable(_tmodel = new TableModelPerson());

		JScrollPane scrollPane = new JScrollPane(_table);
		_table.setFillsViewportHeight(true);
		tablepanel.add(_table.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(scrollPane, BorderLayout.CENTER);

		add(tablepanel);

		JPanel buttonpanel = new JPanel(new GridLayout(0, 1));
		add(buttonpanel, BorderLayout.LINE_END);

		_personAdd = new JButton("Person hinzufügen");
		_personDel = new JButton("Person löschen");

		buttonpanel.add(_personAdd);
		buttonpanel.add(_personDel);

		setAlwaysOnTop(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		// setVisible(true);

	}

	public void AddMaklerAddListener(ActionListener al) {
		_personAdd.addActionListener(al);
	}

	public void AddMaklerDelListener(ActionListener al) {
		_personDel.addActionListener(al);
	}

	public void AddMaklerUpdListener(TableModelListener tml) {
		_tmodel.addTableModelListener(tml);
	}

	public TableModelPerson GetTableModel() {
		return _tmodel;
	}

	public Person GetAktivePerson() {
		int row = _table.getSelectedRow();

		if (row < 0 || _tmodel.getRowCount() == 0)
			return null;

		return _tmodel.GetElement(row);
	}
}
