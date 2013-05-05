package ersteVersuche.Vertrag;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PersonNeuGUI extends JDialog {

	private static final long serialVersionUID = 42;
	private final Panel _dataPanel;

	private final JTextField _vorname, _nachname, _adresse;
	private final JButton _okButton;

	public PersonNeuGUI(JFrame frame) {
		super(frame, true);

		setTitle("Person anlegen");
		// setSize(800, 600);

		// setLocation(280, 100);

		setLayout(new BorderLayout());
		setBackground(new Color(255, 255, 255));

		_dataPanel = new Panel(new GridLayout(5, 2));
		add(_dataPanel, BorderLayout.CENTER);

		_vorname = new JTextField(10);
		_nachname = new JTextField();
		_adresse = new JTextField();

		_dataPanel.add(new JLabel("Vorname:"));
		_dataPanel.add(_vorname);
		_dataPanel.add(new JLabel("Nachnname:"));
		_dataPanel.add(_nachname);
		_dataPanel.add(new JLabel("Adresse:"));
		_dataPanel.add(_adresse);
		

		_okButton = new JButton("OK");

		add(_okButton, BorderLayout.SOUTH);
		pack();

	}

	public void AddOKListener(ActionListener al) {
		_okButton.addActionListener(al);
	}

	public String GetVorname() {
		return _vorname.getText();
	}

	public String GetNachname() {
		return _nachname.getText();
	}

	public String GetAdresse() {
		return _adresse.getText();
	}

	
}
