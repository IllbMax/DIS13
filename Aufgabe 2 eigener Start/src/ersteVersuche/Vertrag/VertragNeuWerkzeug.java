package ersteVersuche.Immobilie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ersteVersuche.Material.Vertrag;

public class VertragNeuWerkzeug {
	private final VertragNeuGUI _GUI;

	private Vertrag _vertrag = null;

	/**
	 * Konstruktor oder so
	 */
	public VertragNeuWerkzeug() {

		_GUI = new VertragNeuGUI(null);

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
		Vertrag m = _vetrag;
		_vertrag = null;
		return m;
	}
}
