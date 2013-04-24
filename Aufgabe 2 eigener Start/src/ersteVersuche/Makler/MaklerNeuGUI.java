package ersteVersuche.Makler;

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

public class MaklerNeuGUI extends JDialog {

	private static final long serialVersionUID = 42;
	private final Panel _dataPanel;

	private final JTextField _name, _adresse, _login, _passwort;
	private final JButton _okButton;

	public MaklerNeuGUI(JFrame frame) {
		super(frame, true);

		setTitle("Makler anlegen");
		// setSize(800, 600);

		// setLocation(280, 100);

		setLayout(new BorderLayout());
		setBackground(new Color(255, 255, 255));

		_dataPanel = new Panel(new GridLayout(5, 2));
		add(_dataPanel, BorderLayout.CENTER);

		_name = new JTextField(10);
		_adresse = new JTextField();
		_login = new JTextField();
		_passwort = new JTextField();

		_dataPanel.add(new JLabel("Name:"));
		_dataPanel.add(_name);
		_dataPanel.add(new JLabel("Adresse:"));
		_dataPanel.add(_adresse);
		_dataPanel.add(new JLabel("Login:"));
		_dataPanel.add(_login);
		_dataPanel.add(new JLabel("Passwort:"));
		_dataPanel.add(_passwort);

		_okButton = new JButton("OK");

		add(_okButton, BorderLayout.SOUTH);
		pack();

	}

	public void AddOKListener(ActionListener al) {
		_okButton.addActionListener(al);
	}

	public String GetName() {
		return _name.getText();
	}

	public String GetAdresse() {
		return _adresse.getText();
	}

	public String GetLogin() {
		return _login.getText();
	}

	public String GetPasswort() {
		return _passwort.getText();
	}
}
