package ersteVersuche.Vertrag;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ersteVersuche.Material.Vertrag;

public class VertragsmenuGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame _frame;
	JButton _anlegen;
	JButton _refresh;
	JPanel _operationSelection;
	private JTable _table;
	private TableModelVertrag _tmodel;

	JPanel _typSelection;
	private ButtonGroup _classButtonGroup;
	private JRadioButton _classKauf;
	private JRadioButton _classMiet;
	private JRadioButton _classAlle;

	public VertragsmenuGUI() {

		setLayout(new BorderLayout());
		_operationSelection = new JPanel(new GridLayout(4, 1));

		JPanel tablepanel = initTable();

		add(tablepanel, BorderLayout.CENTER);

		JPanel control = new JPanel(new BorderLayout());
		_typSelection = new JPanel(new GridLayout(0, 1));

		control.add(_operationSelection, BorderLayout.CENTER);
		control.add(_typSelection, BorderLayout.NORTH);

		add(control, BorderLayout.EAST);

		initTypWahl();

		initButtons();

		pack();

	}

	private void initTypWahl() {

		_classButtonGroup = new ButtonGroup();
		_classKauf = new JRadioButton("Kaufverträge");

		_classMiet = new JRadioButton("Mietverträge");
		_classAlle = new JRadioButton("Alle Verträge");
		_classAlle.setSelected(true);

		_typSelection.add(_classAlle);
		_typSelection.add(_classKauf);
		_typSelection.add(_classMiet);

		_classButtonGroup.add(_classKauf);
		_classButtonGroup.add(_classMiet);
		_classButtonGroup.add(_classAlle);

		_classKauf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateClassData();
			}
		});

		_classMiet.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateClassData();
			}
		});
		_classAlle.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateClassData();
			}
		});

	}

	private void UpdateClassData() {
		if (_classAlle.isSelected())
			_tmodel.SwitchToVertraege();
		else if (_classKauf.isSelected())
			_tmodel.SwitchToKaufvertrag();
		else if (_classMiet.isSelected())
			_tmodel.SwitchToMietvertrag();

		_table.createDefaultColumnsFromModel();
		// pack();
	}

	private void initButtons() {
		_anlegen = new JButton("Vertrag anlegen");
		_anlegen.setSize(100, 100);

		_refresh = new JButton("Refresh");

		_operationSelection.add(_anlegen);
		_operationSelection.add(_refresh);
	}

	private JPanel initTable() {
		JPanel tablepanel = new JPanel(new BorderLayout());
		_table = new JTable(_tmodel = new TableModelVertrag());
		/*
		 * _tmodel.AddImmobilie(new Wohnung(0, "aSD2", "123", "sss", "42",
		 * 12.5f, 4, 123, 7, true, false)); _tmodel.AddImmobilie(new Haus(0,
		 * "aSD2", "123", "sss", "42", 12.5f, 4, 123, true));
		 * _tmodel.AddImmobilie(new Wohnung(0, "aSD2", "123", "sss", "44",
		 * 12.5f, 4, 123, 7, true, false)); _tmodel.AddImmobilie(new Wohnung(0,
		 * "aaSD2", "1d23", "ssfs", "45", 12.5f, 4, 123, 7, true, false));
		 */

		JScrollPane scrollPane = new JScrollPane(_table);
		_table.setFillsViewportHeight(true);

		tablepanel.add(_table.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(scrollPane, BorderLayout.CENTER);
		return tablepanel;
	}

	public void AddVertragAddListener(ActionListener al) {
		_anlegen.addActionListener(al);
	}

	public void AddRefreshAddListener(ActionListener al) {
		_refresh.addActionListener(al);
	}

	public TableModelVertrag GetTableModel() {
		return _tmodel;
	}

	public Vertrag GetAktiveVertrag() {
		int row = _table.getSelectedRow();

		if (row < 0 || _tmodel.getRowCount() == 0)
			return null;

		return _tmodel.GetElement(row);
	}

}
