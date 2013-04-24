package ersteVersuche.Vertrag;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import ersteVersuche.Material.Immobilie;
import ersteVersuche.Material.Person;
import ersteVersuche.Material.Vertrag;

public class VertragNeuWerkzeug {
	private final VertragNeuGUI _GUI;

	private Vertrag _vertrag = null;

	private final List<Person> _personen;
	private final List<Immobilie> _immoblilen;

	/**
	 * Konstruktor oder so
	 */
	public VertragNeuWerkzeug(List<Person> personen, List<Immobilie> immoblilen) {

		_personen = personen;
		_immoblilen = immoblilen;
		_GUI = new VertragNeuGUI(null, _personen, _immoblilen);

		_GUI.AddOKListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_vertrag = _GUI.getVertrag();
				_GUI.setVisible(false);
			}
		});
	}

	/**
	 * Erstellt ein neues (Subklasse eines) Immobilienobjekt (nur das Objekt/
	 * wird nicht in der DB eingetragen)
	 * 
	 * @return Immobilie oder null, bei Abbruch
	 */
	public Vertrag ErstelleVertrag() {

		_GUI.setVisible(true);
		Vertrag m = _vertrag;
		_vertrag = null;
		return m;
	}
}
