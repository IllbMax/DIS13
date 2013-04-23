package ersteVersuche;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import ersteVersuche.Material.Wohnung;

public class ImmobilienmenuGUI extends JFrame {
	JFrame _frame;
	JList _list;
	JButton _anlegen;
	JButton _aendern;
	JButton _loeschen;
	JButton _zurueck;
	JPanel _panel;
	private final JTable _table;
	private TableModelImmobilie _tmodel;
	private TableModelImmobilieWohnung _tmodel2;

	public ImmobilienmenuGUI() {

		setLayout(new BorderLayout());
		_panel = new JPanel(new GridLayout(4, 1));

		JPanel tablepanel = new JPanel(new BorderLayout());
		_table = new JTable(_tmodel2 = new TableModelImmobilieWohnung());
		_tmodel2.AddWohnung(new Wohnung(0, "aSD2", "123", "sss", "42", 12.5f,
				4, 123, 7, true, false));
		JScrollPane scrollPane = new JScrollPane(_table);
		_table.setFillsViewportHeight(true);

		tablepanel.add(_table.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(scrollPane, BorderLayout.CENTER);

		add(tablepanel);

		_anlegen = new JButton("Immobilien anlegen");
		_anlegen.setSize(100, 100);
		_anlegen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		_aendern = new JButton("Immobilien ändern");
		_aendern.setSize(100, 100);
		_aendern.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});

		_loeschen = new JButton("Immobilien löschen");
		_loeschen.setSize(100, 100);
		_loeschen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		_zurueck = new JButton("Zurück");
		_zurueck.setSize(100, 100);
		_zurueck.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		_panel.add(_anlegen);
		_panel.add(_aendern);
		_panel.add(_loeschen);
		_panel.add(_zurueck);

		// add(_panel);
		pack();

	}

	public static void main(String[] args) {
		ImmobilienmenuGUI _gui = new ImmobilienmenuGUI();

		_gui.setVisible(true);
	}
}
