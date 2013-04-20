package ersteVersuche;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import ersteVersuche.Material.Makler;

public class MaklerNeuWerkzeug {
	private final MaklerNeuGUI _GUI;

	private Makler _makler = null;

	/**
	 * Konstruktor oder so
	 */
	public MaklerNeuWerkzeug() {

		_GUI = new MaklerNeuGUI(null);

		_GUI.AddOKListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_makler = new Makler(_GUI.GetName(), _GUI.GetAdresse(), _GUI
						.GetLogin(), _GUI.GetPasswort());
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
	public Makler ErstelleMakler() {

		_GUI.setVisible(true);
		Makler m = _makler;
		_makler = null;
		return m;
	}
}
