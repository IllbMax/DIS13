package ersteVersuche.Material;

public class Person {

	private String _vorname;
	private String _nachname;
	private String _adresse;

	public Person(String vorname, String nachname, String adresse) {
		_vorname = vorname;
		_nachname = nachname;
		_adresse = adresse;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return _adresse;
	}

	/**
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(String adresse) {
		this._adresse = adresse;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return _nachname;
	}

	/**
	 * @param nachname
	 *            the nachname to set
	 */
	public void setNachname(String nachname) {
		this._nachname = nachname;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return _vorname;
	}

	/**
	 * @param vorname
	 *            the vorname to set
	 */
	public void setVorname(String vorname) {
		this._vorname = vorname;
	}

}
