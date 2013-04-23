package ersteVersuche;

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
				// _immobilie = new Makler(_GUI.GetName(), _GUI.GetAdresse(),
				// _GUI
				// .GetLogin(), _GUI.GetPasswort());
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
	public Immobilie ErstelleImmobilie() {

		_GUI.setVisible(true);
		Immobilie m = _immobilie;
		_immobilie = null;
		return m;
	}
}
