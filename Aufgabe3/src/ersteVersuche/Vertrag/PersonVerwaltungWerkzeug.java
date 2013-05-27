package ersteVersuche.Vertrag;

// TODO: Hier fehlt auch noch wass

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import ersteVersuche.Material.Person;
import ersteVersuche.Services.ImmoService;

public class PersonVerwaltungWerkzeug {

	private final PersonVerwaltungGUI _GUI;

	private final ImmoService _service;

	private Person _person;
	private final PersonNeuWerkzeug _personNeu;

	public PersonVerwaltungWerkzeug(ImmoService service) {
		_service = service;

		_GUI = new PersonVerwaltungGUI();
		_personNeu = new PersonNeuWerkzeug();
		for (Person m : LadePerson()) {
			_GUI.GetTableModel().AddPerson(m);
		}

		_GUI.AddPersonAddListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddPerson();
			}

		});
		_GUI.AddPersonDelListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DelPerson(_GUI.GetAktivePerson());
			}

		});

	}

	private void AddPerson() {
		Person m = _personNeu.ErstellePerson();

		if (m != null && AddPersonSQL(m)) {
			_GUI.GetTableModel().AddPerson(m);
		}
		_GUI.repaint();
	}

	private boolean AddPersonSQL(Person p) {
		_service.addPerson(p);
		return true;
	}

	private void DelPerson(Person m) {

		if (m != null && DelPersonSQL(m)) {
			_GUI.GetTableModel().DeletePerson(m);
		}
		_GUI.repaint();
	}

	private boolean DelPersonSQL(Person p) {
		_service.deletePerson(p);
		return true;
	}

	public void ZeigePersonenMenu() {
		_GUI.setVisible(true);
	}

	private List<Person> LadePerson() {

		List<Person> result = new ArrayList<Person>(_service.getAllPersons());

		return result;
	}

}
