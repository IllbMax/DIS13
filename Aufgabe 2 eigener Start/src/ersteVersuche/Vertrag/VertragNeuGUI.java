package ersteVersuche.Vertrag;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import ersteVersuche.Material.Haus;
import ersteVersuche.Material.Immobilie;
import ersteVersuche.Material.Kaufvertrag;
import ersteVersuche.Material.Mietvertrag;
import ersteVersuche.Material.Person;
import ersteVersuche.Material.Vertrag;
import ersteVersuche.Material.Wohnung;

public class VertragNeuGUI extends JDialog {

	private static final long serialVersionUID = 42;

	private static final int TEXTFELD_BREITE = 10;

	private JPanel _dataPanelBase;
	private final JPanel _dataPanel;

	private JTextField _ort;
	private JFormattedTextField _datum;

	private final JButton _okButton;

	private ButtonGroup _classButtonGroup;
	private JRadioButton _classKaufvertrag;
	private JRadioButton _classMietvertrag;

	private final JPanel _dataPanelExtend;
	private JPanel _kaufExtend;
	private JPanel _mietExtend;

	private JFormattedTextField _kauf_anzahlratten;
	private JFormattedTextField _kauf_rattenzins;
	private JComboBox _haus;
	private JComboBox _person;

	private JFormattedTextField _mietbeginn;
	private JFormattedTextField _miet_dauer;
	private JFormattedTextField _miet_nebenkosten;
	private JComboBox _mieter;
	private JComboBox _wohnung;

	private final List<Immobilie> _immobilien;

	private final List<Person> _personen;

	public VertragNeuGUI(JFrame frame, List<Person> personen,
			List<Immobilie> immobilien) {
		super(frame, true);

		_immobilien = immobilien;
		_personen = personen;
		setTitle(" Vertrag anlegen");
		// setSize(800, 600);

		// setLocation(280, 100);

		setLayout(new BorderLayout());
		setBackground(new Color(255, 255, 255));

		initClassWahl();

		_dataPanel = new JPanel(new BorderLayout());
		add(_dataPanel, BorderLayout.CENTER);

		initBase();
		_dataPanel.add(_dataPanelBase, BorderLayout.NORTH);

		_dataPanelExtend = new JPanel(new BorderLayout());

		_dataPanel.add(_dataPanelExtend, BorderLayout.SOUTH);

		initMietvertrag();
		initKaufvertrag();

		_dataPanelExtend.add(_kaufExtend);

		UpdateClassData();

		_okButton = new JButton("OK");

		add(_okButton, BorderLayout.SOUTH);

		pack();
	}

	private void initClassWahl() {
		JPanel selectClassPanel = new JPanel(new GridLayout(0, 2));

		_classButtonGroup = new ButtonGroup();
		_classKaufvertrag = new JRadioButton("Kaufvertrag");
		_classKaufvertrag.setSelected(true);
		_classMietvertrag = new JRadioButton("Mietvertrag");

		selectClassPanel.add(_classKaufvertrag);
		selectClassPanel.add(_classMietvertrag);

		_classButtonGroup.add(_classKaufvertrag);
		_classButtonGroup.add(_classMietvertrag);
		add(selectClassPanel, BorderLayout.NORTH);

		_classKaufvertrag.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateClassData();
			}
		});

		_classMietvertrag.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateClassData();
			}
		});
	}

	private void initBase() {
		_dataPanelBase = new JPanel(new GridLayout(5, 2));

		_ort = new JTextField(TEXTFELD_BREITE);
		_datum = new JFormattedTextField(DateFormat.getDateInstance());
		_datum.setColumns(TEXTFELD_BREITE);
		_datum.setValue(new Date(1970, 1, 1));

		_dataPanelBase.add(new JLabel("Datum:"));
		_dataPanelBase.add(_datum);
		_dataPanelBase.add(new JLabel("Ort:"));
		_dataPanelBase.add(_ort);
	}

	private void initMietvertrag() {
		_mietExtend = new JPanel(new GridLayout(0, 2));

		_mietbeginn = new JFormattedTextField(DateFormat.getDateInstance());
		_mietbeginn.setColumns(TEXTFELD_BREITE);
		_mietbeginn.setValue(new Date(1970, 1, 1));

		_miet_dauer = new JFormattedTextField(NumberFormat.getIntegerInstance());
		_miet_dauer.setColumns(TEXTFELD_BREITE);
		_miet_dauer.setValue(0);

		_miet_nebenkosten = new JFormattedTextField(
				NumberFormat.getNumberInstance());
		_miet_nebenkosten.setColumns(TEXTFELD_BREITE);
		_miet_nebenkosten.setValue(0.0f);

		_mieter = new JComboBox(_personen.toArray(new Person[_personen.size()]));

		List<Wohnung> wohnung = new ArrayList<Wohnung>();
		for (Immobilie i : _immobilien) {
			if (i instanceof Wohnung)
				wohnung.add((Wohnung) i);
		}

		_wohnung = new JComboBox(wohnung.toArray(new Wohnung[wohnung.size()]));

		_mietExtend.add(new JLabel("Mietbeginn:"));
		_mietExtend.add(_mietbeginn);
		_mietExtend.add(new JLabel("Mietdauer:"));
		_mietExtend.add(_miet_dauer);
		_mietExtend.add(new JLabel("Nebenkosten:"));
		_mietExtend.add(_miet_nebenkosten);
		_mietExtend.add(new JLabel("Mieter:"));
		_mietExtend.add(_mieter);
		_mietExtend.add(new JLabel("Wohung:"));
		_mietExtend.add(_wohnung);

	}

	private void initKaufvertrag() {
		_kaufExtend = new JPanel(new GridLayout(0, 2));

		_kauf_anzahlratten = new JFormattedTextField(
				NumberFormat.getIntegerInstance());
		_kauf_anzahlratten.setColumns(TEXTFELD_BREITE);
		_kauf_anzahlratten.setValue(0);
		_kauf_rattenzins = new JFormattedTextField(
				NumberFormat.getNumberInstance());
		_kauf_rattenzins.setColumns(TEXTFELD_BREITE);
		_kauf_rattenzins.setValue(0.0f);

		_person = new JComboBox(_personen.toArray(new Person[_personen.size()]));

		List<Haus> haus = new ArrayList<Haus>();
		for (Immobilie i : _immobilien) {
			if (i instanceof Haus)
				haus.add((Haus) i);
		}

		_haus = new JComboBox(haus.toArray(new Haus[haus.size()]));

		_kaufExtend.add(new JLabel("Anzahl Raten:"));
		_kaufExtend.add(_kauf_anzahlratten);
		_kaufExtend.add(new JLabel("Ratenzins:"));
		_kaufExtend.add(_kauf_rattenzins);
		_kaufExtend.add(new JLabel("Person:"));
		_kaufExtend.add(_person);
		_kaufExtend.add(new JLabel("Haus:"));
		_kaufExtend.add(_haus);

	}

	private void UpdateClassData() {
		_dataPanelExtend.remove(_mietExtend);
		_dataPanelExtend.remove(_kaufExtend);

		if (_classKaufvertrag.isSelected())
			_dataPanelExtend.add(_kaufExtend);
		else if (_classMietvertrag.isSelected())
			_dataPanelExtend.add(_mietExtend);
		pack();
		// repaint();
	}

	public void AddOKListener(ActionListener al) {
		_okButton.addActionListener(al);
	}

	private float getFloatFromFormatTextbox(JFormattedTextField text) {
		float f;
		Object o = text.getValue();
		if (o instanceof Long || o instanceof Integer)
			f = (Long) o;
		else if (o instanceof Double)
			f = ((Double) o).floatValue();
		else
			f = (Float) o;
		return f;
	}

	public Vertrag getVertrag() {

		if (_classKaufvertrag.isSelected()) {
			return new Kaufvertrag(-1, (Date) _datum.getValue(),
					_ort.getText(), (Person) _person.getSelectedItem(),
					(Haus) _haus.getSelectedItem(),
					(Integer) _kauf_anzahlratten.getValue(),
					(Float) _kauf_rattenzins.getValue());
		} else if (_classMietvertrag.isSelected()) {
			return new Mietvertrag(-1, (Date) _datum.getValue(),
					_ort.getText(), (Person) _mieter.getSelectedItem(),
					(Wohnung) _wohnung.getSelectedItem(),
					(Date) _mietbeginn.getValue(),
					(Integer) _miet_dauer.getValue(),
					(Float) _miet_nebenkosten.getValue());

		}

		return null;
	}

}
