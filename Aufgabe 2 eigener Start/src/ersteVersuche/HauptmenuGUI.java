package ersteVersuche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout.Constraints;


public class HauptmenuGUI extends Frame{
	

	private static final long serialVersionUID = 42;
	public Panel _centerpanel;
	private HauptmenuWerkzeug _Werkzeug;

	public HauptmenuGUI(HauptmenuWerkzeug werkzeug)
	{	
		_Werkzeug = werkzeug;
		setTitle("Immobilienverwaltungssoftware - Hauptmenu");
		setSize(800, 600);
		
		setLocation(280, 100);
		setLayout(new GridLayout());
		setBackground(new Color(255,255,255));
		
		//add(GeneriereTextPanel());
		
		
		add(GeneriereButtonPanel());
		
		setVisible(true);
		
		
		
	}
	
	private Panel GeneriereTextPanel() 
	{
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(3,1));
		panel.add(new JTextArea("Untermenu1"));
		panel.add(new JTextArea("Untermenu2"));
		panel.add(new JTextArea("Untermenu3"));
		return panel;
	}
	
	private Panel GeneriereButtonPanel() 
	{
		Panel panel = new Panel();
		panel.setLayout(new GridLayout(3,1));
		
		JTextField textfeld;
		Panel panel1 = new Panel(new GridBagLayout());
		panel1.add(new JTextArea("Passwort: "));
		textfeld = new JTextField();
		textfeld.setPreferredSize(new Dimension(300,30));
		panel1.add(textfeld);
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridy=2;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		panel1.add(new JButton("Login"), constraints);
		panel.add(panel1);
		
		Panel panel2 = new Panel(new GridBagLayout());
		panel2.add(new JTextArea("Login: "));
		textfeld = new JTextField();
		textfeld.setPreferredSize(new Dimension(300,30));
		panel2.add(textfeld);
		constraints = new GridBagConstraints();
		constraints.gridy=2;
		panel2.add(new JTextArea("Passwort: "),constraints);
		textfeld =new JTextField();
		textfeld.setPreferredSize(new Dimension(300,30));
		panel2.add(textfeld,constraints);
		constraints.gridy=3;
		constraints.gridwidth = GridBagConstraints.REMAINDER;
		panel2.add(new JButton("Login"), constraints);
		panel.add(panel2);
		
		
		Panel panel3 = new Panel();
		panel3.add(new JButton("Vertragsverwaltung"));
		panel.add(panel3);
		return panel;
	}
	
}
