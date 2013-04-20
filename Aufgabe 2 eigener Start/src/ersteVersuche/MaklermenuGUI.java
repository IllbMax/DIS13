package ersteVersuche;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

public class MaklermenuGUI extends JFrame {

	public MaklermenuGUI() {

		setTitle("Immobilienverwaltungssoftware - Hauptmenu");
		setSize(800, 600);

		setLocation(280, 100);
		setLayout(new BorderLayout());
		setBackground(new Color(255, 255, 255));

		JPanel tablepanel = new JPanel(new BorderLayout());

		final String[] columnNames = { "Name", "Adresse", "Login", "Passwort" };
		final Object[][] data = {
				{ "Kathy", "Smith", "Snowboarding", new Integer(5) },
				{ "John", "Doe", "Rowing", new Integer(3) },
				{ "Sue", "Black", "Knitting", new Integer(2) },
				{ "Jane", "White", "Speed reading", new Integer(20) },
				{ "Joe", "Brown", "Pool", new Integer(10) } };

		JTable table = new JTable(data, columnNames);

		// table.setModel(new TableModelTest<Integer>(data, columnNames));

		CellEditorListener ChangeNotification = new CellEditorListener() {
			@Override
			public void editingCanceled(ChangeEvent e) {
				System.out.println("The user canceled editing.");
			}

			@Override
			public void editingStopped(ChangeEvent e) {
				System.out.println("The user stopped editing successfully.");
			}
		};

		JScrollPane scrollPane = new JScrollPane(table);
		table.setFillsViewportHeight(true);
		tablepanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		tablepanel.add(table, BorderLayout.CENTER);

		add(tablepanel);

		JButton Button = new JButton("Klick mich3");
		Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// setLayout(new GridLayout());
				// _centerpanel.add(new JButton("hehe"));
				setVisible(true);
			}
		});
		// _centerpanel = new Panel();
		// add(_centerpanel);
		add(Button, BorderLayout.LINE_START);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		// new TableModelTest<String[]>(data, columnNames);
	}

	public static void main(String[] args) {
		new MaklermenuGUI();
	}
}