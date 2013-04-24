package ersteVersuche.Immobilie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ersteVersuche.Material.Immobilie;

public class ImmobilieNeuWerkzeug {
	private final ImmobilieNeuGUI _GUI;

	private Immobilie _immobilie = null;

	/**
	 * Konstruktor oder so
	 */
	public ImmobilieNeuWerkzeug() {

		_GUI = new ImmobilieNeuGUI(null);

		_GUI.AddOKListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_immobilie = _GUI.getImmobilie();
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
	public Immobilie ErstelleImmobilie() {

		_GUI.setVisible(true);
		Immobilie m = _immobilie;
		_immobilie = null;
		return m;
	}
}
