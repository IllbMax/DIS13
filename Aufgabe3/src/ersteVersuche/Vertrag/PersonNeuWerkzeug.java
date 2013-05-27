package ersteVersuche.Vertrag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ersteVersuche.Material.Person;


public class PersonNeuWerkzeug {
	


	
		private final PersonNeuGUI _GUI;

		private Person _person = null;

		/**
		 * Konstruktor oder so
		 */
		public PersonNeuWerkzeug() {

			_GUI = new PersonNeuGUI(null);

			_GUI.AddOKListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					_person = new Person(-1, _GUI.GetVorname(), _GUI.GetNachname(), _GUI
							.GetAdresse());
					_GUI.setVisible(false);
				}
			});
		}

		/**
		 * Erstellt ein neues Maklerobjekt (nur das Objekt/ wird nicht in der DB
		 * eingetragen)
		 * 
		 * @return Makler oder null, bei Abbruch
		 */
		public Person ErstellePerson() {

			_GUI.setVisible(true);
			Person m = _person;
			_person = null;
			return m;
		}
	}



