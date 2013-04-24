package ersteVersuche.Makler;

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

import ersteVersuche.Material.Makler;

public class MaklermenuGUI extends JFrame {
	private static final long serialVersionUID = -8976151471696419140L;

	TableModelMakler _tmodel;
	JTable _table;
	JButton _maklerAdd, _maklerDel;

	public MaklermenuGUI() {

		setTitle("Immobilienverwaltungssoftware - Maklerverwaltung");
		setSize(800, 600);

		setLocation(280, 100);
		setLayout(new BorderLayout());
		setBackground(new Color(255, 255, 255));

		JPanel tablepanel = new JPanel(new BorderLayout());

		_table = new JTable(_tmodel = new TableModelMakler());

		JScrollPane scrollPane = new JScrollPane(_table);
		_table.setFillsViewportHeight(true);
		tablepanel.add(_table.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(scrollPane, BorderLayout.CENTER);

		add(tablepanel);

		JPanel buttonpanel = new JPanel(new GridLayout(0, 1));
		add(buttonpanel, BorderLayout.LINE_END);

		_maklerAdd = new JButton("Makler hinzufügen");
		_maklerDel = new JButton("Makler löschen");

		buttonpanel.add(_maklerAdd);
		buttonpanel.add(_maklerDel);
		
		setAlwaysOnTop(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		//setVisible(true);

	}

	public void AddMaklerAddListener(ActionListener al) {
		_maklerAdd.addActionListener(al);
	}

	public void AddMaklerDelListener(ActionListener al) {
		_maklerDel.addActionListener(al);
	}

	public void AddMaklerUpdListener(TableModelListener tml) {
		_tmodel.addTableModelListener(tml);
	}

	public TableModelMakler GetTableModel() {
		return _tmodel;
	}

	public Makler GetAktiveMakler() {
		int row = _table.getSelectedRow();

		if (row < 0 || _tmodel.getRowCount() == 0)
			return null;

		return _tmodel.GetElement(row);
	}
}