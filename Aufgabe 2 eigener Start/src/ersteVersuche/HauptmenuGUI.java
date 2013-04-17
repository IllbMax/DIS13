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

	public HauptmenuGUI()
	{	
		setTitle("Immobilienverwaltungssoftware - Hauptmenu");
		setSize(800, 600);
		
		setLocation(280, 100);
		setLayout(new BorderLayout());
		setBackground(new Color(255,255,255));
		JButton Button = new JButton("Klick mich3");
		Button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//setLayout(new GridLayout());
				_centerpanel.add(new JButton("hehe"));
				setVisible(true);
			}
		});
		_centerpanel = new Panel();
		add(_centerpanel);
		add(Button, BorderLayout.LINE_START);
		Component Feld = new JTextField();
		add(Feld, BorderLayout.NORTH);
			
		/**add(new JTextArea("Login: "));
		add(new JTextField("Klick mich2"));**/
		
		setVisible(true);
		
		
		
	}
	
}
