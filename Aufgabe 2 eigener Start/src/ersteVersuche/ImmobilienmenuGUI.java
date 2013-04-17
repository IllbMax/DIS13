package ersteVersuche;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;


public class ImmobilienmenuGUI extends JFrame  
   {
	 JFrame _frame;
	 JList _list;
	 JButton _anlegen;
	 JButton _aendern;
	 JButton _loeschen;
	 JButton _zurueck;
	 JPanel _panel;
	 
	 public ImmobilienmenuGUI() 
	 {
	
     setLayout(new BorderLayout());
      _panel= new JPanel(new GridLayout(4,1));
     
  _anlegen = new JButton("Immobilien anlegen");
   _anlegen.setSize(100,100);
		_anlegen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}});
		
		_aendern = new JButton("Immobilien ändern");
		_aendern.setSize(100,100);
		_aendern.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}});
		_loeschen = new JButton("Immobilien loeschen");
		_loeschen.setSize(100,100);
		_loeschen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}});
		_zurueck = new JButton("Zurück");
		_zurueck.setSize(100,100);
		_zurueck.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
			}});
		_panel.add(_anlegen);
		_panel.add(_aendern);
		_panel.add(_loeschen);
		_panel.add(_zurueck);
		
        add(_panel);
        pack();
	
	 
		
	 }
	 public static void main(String[]args){
		 ImmobilienmenuGUI  _gui= new ImmobilienmenuGUI();
		
		 _gui.setVisible(true);
		  }
   }
	 
