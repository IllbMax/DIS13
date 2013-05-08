package ersteVersuche.Material;

public class Makler {
	private int _id;
	private String _name;
	private String _adresse;
	private String _login;
	private String _passwort;

	public Makler() {
	}

	public Makler(String name, String adresse, String login, String passwort) {
		_name = name;
		_adresse = adresse;
		_login = login;
		_passwort = passwort;
	}

	public String getPasswort() {
		return _passwort;
	}

	public void setPasswort(String passwort) {
		this._passwort = passwort;
	}

	public String getLogin() {
		return _login;
	}

	public void setLogin(String login) {
		this._login = login;
	}

	public String getAdresse() {
		return _adresse;
	}

	public void setAdresse(String adresse) {
		this._adresse = adresse;
	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return _id;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(int iD) {
		_id = iD;
	}

	@Override
	public boolean equals(Object obj) {
		Makler m = (Makler) obj;
		if (m == null)
			return false;
		if (m == this)
			return true;

		return _id == m._id && _name.equals(m._name)
				&& _adresse.equals(m._adresse) && _login.equals(m._login)
				&& _passwort.equals(m._passwort);
	}

	@Override
	public int hashCode() {
		return _id;
	}

}
