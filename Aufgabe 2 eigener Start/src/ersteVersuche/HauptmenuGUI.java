package ersteVersuche;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HauptmenuGUI extends JFrame {

	private static final long serialVersionUID = 42;
	public Panel _centerpanel;


	private final JTextField passwort1, passwort2, login;
	private final JButton maklerButton, immobilienButton, vertragsButton;
	
	public HauptmenuGUI()
	{	
		setTitle("Immobilienverwaltungssoftware - Hauptmenu");
		setSize(800, 600);

		setLocation(280, 100);
		setLayout(new GridLayout());
		setBackground(new Color(255, 255, 255));

		// add(GeneriereTextPanel());
		passwort1 = new JTextField();
		passwort1.setPreferredSize(new Dimension(300, 30));

		login = new JTextField();
		login.setPreferredSize(new Dimension(300, 30));

		passwort2 = new JTextField();
		passwort2.setPreferredSize(new Dimension(300, 30));

		maklerButton = new JButton("Login");
		immobilienButton = new JButton("Login");
		vertragsButton = new JButton("Vertragsverwaltung");

		add(GeneriereButtonPanel());

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pack();
		setVisible(true);

	}
	
	private Panel GeneriereButtonPanel() 
	{
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(3,1));		
		
		Panel panel1 = new Panel(new GridBagLayout());
		panel1.add(new JLabel("Passwort: "));
		panel1.add(passwort1);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy = 2;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		panel1.add(maklerButton, constraints);
		panel.add(panel1);

		Panel panel2 = new Panel(new GridBagLayout());
		panel2.add(new JLabel("Login: "));
		panel2.add(login);
		constraints = new GridBagConstraints();
		constraints.gridy = 2;
		panel2.add(new JLabel("Passwort: "), constraints);
		panel2.add(passwort2, constraints);
		constraints.gridy = 3;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		panel2.add(immobilienButton, constraints);
		panel.add(panel2);

		Panel panel3 = new Panel();
		panel3.add(vertragsButton);
		panel.add(panel3);
		return panel;
	}

	public void AddMaklerButtonListener(ActionListener al) {
		maklerButton.addActionListener(al);
	}

	public void AddImmobilienButtonListener(ActionListener al) {
		immobilienButton.addActionListener(al);
	}

	public void AddVertragsListener(ActionListener al) {
		vertragsButton.addActionListener(al);
	}

	public String GetPasswort1Text() {
		return passwort1.getText();

	}

	public String GetPasswort2Text() {
		return passwort2.getText();

	}

	public String GetLoginText() {
		return login.getText();

	}

}
