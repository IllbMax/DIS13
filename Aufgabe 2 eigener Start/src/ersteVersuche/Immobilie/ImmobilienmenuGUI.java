package ersteVersuche.Immobilie;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;

import ersteVersuche.Material.Haus;
import ersteVersuche.Material.Immobilie;
import ersteVersuche.Material.Wohnung;

public class ImmobilienmenuGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame _frame;
	JList _list;
	JButton _anlegen;
	JButton _loeschen;
	JButton _zurueck;
	JPanel _operationSelection;
	private JTable _table;
	private TableModelImmobilie _tmodel;

	JPanel _typSelection;
	private ButtonGroup _classButtonGroup;
	private JRadioButton _classHaus;
	private JRadioButton _classWohnung;
	private JRadioButton _classAlle;

	public ImmobilienmenuGUI() {

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

		// add(_panel);
		pack();

	}

	private void initTypWahl() {

		_classButtonGroup = new ButtonGroup();
		_classHaus = new JRadioButton("Häuser");
		_classHaus.setSelected(true);
		_classWohnung = new JRadioButton("Wohnungen");
		_classAlle = new JRadioButton("Alle Immobilien");

		_typSelection.add(_classAlle);
		_typSelection.add(_classHaus);
		_typSelection.add(_classWohnung);

		_classButtonGroup.add(_classHaus);
		_classButtonGroup.add(_classWohnung);
		_classButtonGroup.add(_classAlle);

		_classHaus.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateClassData();
			}
		});

		_classWohnung.addActionListener(new ActionListener() {

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
			_tmodel.SwitchToImmobilien();
		else if (_classHaus.isSelected())
			_tmodel.SwitchToHaus();
		else if (_classWohnung.isSelected())
			_tmodel.SwitchToWohnung();

		_table.createDefaultColumnsFromModel();
		// pack();
	}

	private void initButtons() {
		_anlegen = new JButton("Immobilien anlegen");
		_anlegen.setSize(100, 100);

		_loeschen = new JButton("Immobilien löschen");
		_loeschen.setSize(100, 100);

		_zurueck = new JButton("Zurück");
		_zurueck.setSize(100, 100);
		_zurueck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		_operationSelection.add(_anlegen);
		_operationSelection.add(_loeschen);
		_operationSelection.add(_zurueck);
	}

	private JPanel initTable() {
		JPanel tablepanel = new JPanel(new BorderLayout());
		_table = new JTable(_tmodel = new TableModelImmobilie());
		/*_tmodel.AddImmobilie(new Wohnung(0, "aSD2", "123", "sss", "42", 12.5f,
				4, 123, 7, true, false));
		_tmodel.AddImmobilie(new Haus(0, "aSD2", "123", "sss", "42", 12.5f, 4,
				123, true));
		_tmodel.AddImmobilie(new Wohnung(0, "aSD2", "123", "sss", "44", 12.5f,
				4, 123, 7, true, false));
		_tmodel.AddImmobilie(new Wohnung(0, "aaSD2", "1d23", "ssfs", "45",
				12.5f, 4, 123, 7, true, false));*/

		JScrollPane scrollPane = new JScrollPane(_table);
		_table.setFillsViewportHeight(true);

		tablepanel.add(_table.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(scrollPane, BorderLayout.CENTER);
		return tablepanel;
	}

	public void AddImmobilienAddListener(ActionListener al) {
		_anlegen.addActionListener(al);
	}

	public void AddImmobilienDelListener(ActionListener al) {
		_loeschen.addActionListener(al);
	}

	public void AddImmobilienUpdListener(TableModelListener tml) {
		_tmodel.addTableModelListener(tml);
	}

	public TableModelImmobilie GetTableModel() {
		return _tmodel;
	}

	public static void main(String[] args) {
		ImmobilienmenuGUI _gui = new ImmobilienmenuGUI();

		_gui.setVisible(true);
	}

	public Immobilie GetAktiveImmobilie() {
		int row = _table.getSelectedRow();

		if (row < 0 || _tmodel.getRowCount() == 0)
			return null;

		return _tmodel.GetElement(row);
	}
	
}
